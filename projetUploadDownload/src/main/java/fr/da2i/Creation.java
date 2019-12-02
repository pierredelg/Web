package fr.da2i;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet permettant de créer un nouvel utilisateur.
 * Ainsi que lui créer son dossier personnel sur le serveur.
 */
@WebServlet("/Creation")
public class Creation extends HttpServlet {
    public static String NOM_DOSSIER_UTILISATEURS = "users";
    public void doGet(HttpServletRequest req, HttpServletResponse res ) {

        /*Redirection sur la page de login*/
        String contextPath = req.getContextPath();
        try {
            res.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res ) {

        HttpSession session = req.getSession();

        String contextPath = req.getServletContext().getContextPath();
        String identificateur = StringEscapeUtils.escapeHtml4(req.getParameter("identificateur"));

        /* Vérification afin de savoir si l'utilisateur s'est enregistré*/
        if (identificateur == null) {
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            String urlBDD = getServletContext().getInitParameter("urlBDD");
            String loginBDD = getServletContext().getInitParameter("loginBDD");
            String mdpBDD = getServletContext().getInitParameter("mdpBDD");
            String driverBDD = getServletContext().getInitParameter("driverBDD");

            String nom = StringEscapeUtils.escapeHtml4(req.getParameter("nom"));
            String login = StringEscapeUtils.escapeHtml4(req.getParameter("login"));
            String mdp = StringEscapeUtils.escapeHtml4(req.getParameter("mdp"));

            try {
                //On charge le driver
                Class.forName(driverBDD);
            } catch (ClassNotFoundException e) {
                session.setAttribute("erreur",e.getMessage());
            }

            Connection con = null;
            try {
                //On ouvre la connexion
                con = DriverManager.getConnection(urlBDD, loginBDD, mdpBDD);
            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }

            //On constitue la requete select
            String querySelect = "select * from personne where login=? and mdp=?;";

            PreparedStatement ps1 = null;
            try {
                if (con != null) {
                    ps1 = con.prepareStatement(querySelect);
                    ps1.setString(1, login);
                    ps1.setString(2, mdp);
                }

                ResultSet rs = null;

                if (ps1 != null) {
                    rs = ps1.executeQuery();
                }

                //Si l'utilisateur est inconnu la requete ne donnera aucun resultat
                if(rs == null || ! rs.next()) {

                    String queryId = "select max(id) from personne;";

                    PreparedStatement ps2 = null;

                    if(con != null){
                        ps2 = con.prepareStatement(queryId);
                    }

                    ResultSet rs2 = null;
                    if (ps2 != null) {
                        rs2 = ps2.executeQuery();
                    }
                    int id = 0;
                    if(rs2 != null && rs2.next()){
                        id = rs2.getInt("max");
                        id++;
                    }

                    //On constitue la requete insert
                    String queryInsert = "insert into personne values (?,?,?,?);";

                    PreparedStatement ps3 = null;
                    if (con != null) {
                        ps3 = con.prepareStatement(queryInsert);
                        ps3.setInt(1, id);
                        ps3.setString(2, nom);
                        ps3.setString(3, login);
                        ps3.setString(4, mdp);
                    }

                    if (ps3 != null) {
                        ps3.executeUpdate();
                    }

                    try {
                        String nomDossier = id + nom;
                        //On crée le dossier de l'utilisateur dans le dossier users
                        Runtime.getRuntime().exec("mkdir "+ nomDossier, null, new
                                File(getServletContext().getRealPath("/"+ NOM_DOSSIER_UTILISATEURS))).waitFor();
                        session.setAttribute("nomDossier",nomDossier);
                    } catch (InterruptedException | IOException e) {
                        session.setAttribute("erreur",e.getMessage());
                    }

                    String erreur = (String) session.getAttribute("erreur");
                    if(erreur == null || erreur.isEmpty()) {
                        try {
                            res.sendRedirect(contextPath + "/login.html");
                        } catch (IOException e) {
                            session.setAttribute("erreur",e.getMessage());
                        }
                    }
                }else{
                    session.setAttribute("erreur","Cet utilisateur existe dejà, vous pouvez vous connectez sur la page d'acceuil");
                }
            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }
            String erreur = (String) session.getAttribute("erreur");
            if(erreur != null && !erreur.isEmpty()) {
                try {
                    res.sendRedirect(contextPath + "/Error.jsp");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
