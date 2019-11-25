package fr.da2i;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet permettant de créer un nouvel utilisateur.
 * Ainsi que lui creer son dossier personnel sur le serveur.
 */
@WebServlet("/Creation")
public class Creation extends HttpServlet {
    public static String NOM_DOSSIER_FICHIER = "users";
    public void doGet(HttpServletRequest req, HttpServletResponse res ) {

        /*Redirection sur la page de login*/
        String contextPath = req.getContextPath();
        try {
            res.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res ) {
        String contextPath = req.getServletContext().getContextPath();
        String identificateur = StringEscapeUtils.escapeHtml4(req.getParameter("identificateur"));

        /* Vérification afin de savoir si l'utilisateur s'est enregistré*/
        if (identificateur == null) {
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                e.getMessage();
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
                System.out.println(e.getMessage());
            }

            Connection con = null;
            try {
                //On ouvre la connexion
                con = DriverManager.getConnection(urlBDD, loginBDD, mdpBDD);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
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

                System.out.println(rs);
                //Si l'utilisateur est inconnu la requete ne donnera aucun resultat
                if(rs == null || ! rs.next()) {

                    //On constitue la requete insert
                    String queryInsert = "insert into personne values (?,?,?);";

                    PreparedStatement ps2 = null;
                    if (con != null) {
                        ps2 = con.prepareStatement(queryInsert);
                        ps2.setString(1, nom);
                        ps2.setString(2, login);
                        ps2.setString(3, mdp);
                    }

                    if (ps2 != null) {
                        ps2.executeUpdate();
                    }

                    try {
                        //On crée le dossier de l'utilisateur dans le dossier users
                        Runtime.getRuntime().exec("mkdir "+ nom, null, new
                                File(getServletContext().getRealPath("/"+ NOM_DOSSIER_FICHIER))).waitFor();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        res.sendRedirect(contextPath + "/login.html");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    try {
                        res.sendRedirect(contextPath + "/erreur.html");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
