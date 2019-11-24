package fr.da2i;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Authen")
public class Authen extends HttpServlet {

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
            String query = "select * from personne where login=? and mdp=?;";

            PreparedStatement ps = null;
            try {
                if (con != null) {
                    ps = con.prepareStatement(query);
                    ps.setString(1, login);
                    ps.setString(2, mdp);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            ResultSet rs = null;
            try {
                if (ps != null) {
                    rs = ps.executeQuery();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            String loginFromBDD = null;
            String mdpFromBDD = null;
            String nomFromBDD = null;
            try {
                while (rs != null && rs.next()) {
                    loginFromBDD = rs.getString("login");
                    mdpFromBDD = rs.getString("mdp");
                    nomFromBDD = rs.getString("nom");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            HttpSession session = req.getSession();
            if (login != null && login.equals(loginFromBDD) && mdp != null && mdp.equals(mdpFromBDD)) {
                try {
                    session.setAttribute("nomUser",nomFromBDD);
                    session.setAttribute("identificateur", "File.jsp");
                    res.sendRedirect(contextPath + "/File.jsp");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    res.sendRedirect(contextPath + "/login.html");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                //On ferme la connexion
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}