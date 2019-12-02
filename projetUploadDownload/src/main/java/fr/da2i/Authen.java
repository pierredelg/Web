package fr.da2i;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet permettant de vérifier si l'utilisateur s'est déja enregistré
 * (s'il est présent dans la base de données)
 */
@WebServlet("/Authen")
public class Authen extends HttpServlet {

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

        /* Vérification afin de savoir si l'utilisateur est passé par la page de login*/
        if (identificateur == null) {
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                session.setAttribute("erreur",e.getMessage());
            }
        } else {

            String urlBDD = getServletContext().getInitParameter("urlBDD");
            String loginBDD = getServletContext().getInitParameter("loginBDD");
            String mdpBDD = getServletContext().getInitParameter("mdpBDD");
            String driverBDD = getServletContext().getInitParameter("driverBDD");

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
            String query = "select * from personne where login=? and mdp=?;";

            PreparedStatement ps = null;
            try {
                if (con != null) {
                    ps = con.prepareStatement(query);
                    ps.setString(1, login);
                    ps.setString(2, mdp);
                }
            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }

            ResultSet rs = null;
            try {
                if (ps != null) {
                    rs = ps.executeQuery();
                }
            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }
            String loginFromBDD = null;
            String mdpFromBDD = null;
            String nomFromBDD = null;
            int idFromBDD = 0;
            try {
                //On récupere le nom, le login et le mot de passe dans la base de données
                while (rs != null && rs.next()) {
                    idFromBDD = rs.getInt("id");
                    nomFromBDD = rs.getString("nom");
                    loginFromBDD = rs.getString("login");
                    mdpFromBDD = rs.getString("mdp");
                }
            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }
            try {
                //On ferme la connexion
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                session.setAttribute("erreur",e.getMessage());
            }

            String erreur = (String) session.getAttribute("erreur");
            if(erreur == null || erreur.isEmpty()) {
                //On compare les champs afin de savoir s'il sont correct
                if (login != null && login.equals(loginFromBDD) && mdp != null && mdp.equals(mdpFromBDD)) {
                    try {
                        session.setAttribute("nomUser",nomFromBDD);
                        session.setAttribute("nomDossier",idFromBDD+nomFromBDD);
                        session.setAttribute("identificateur", "File.jsp");
                        res.sendRedirect(contextPath + "/File.jsp");
                    } catch (IOException e) {
                        session.setAttribute("erreur",e.getMessage());
                    }
                } else {
                   session.setAttribute("erreur","Utilisateur est inconnu, créez un compte afin de vous connecter.");
                }
            }
            erreur = (String) session.getAttribute("erreur");
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