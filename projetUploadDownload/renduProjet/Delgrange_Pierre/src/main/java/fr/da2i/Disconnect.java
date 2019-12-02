package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet permettant de déconnecter un utilisateur.
 */
@WebServlet("/Disconnect")
public class Disconnect extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        //On arrete la session
        req.getSession().invalidate();

        try {
            res.sendRedirect("login.html");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res ) {
        /*Redirection sur la page de login*/
        String contextPath = req.getContextPath();
        try {
            res.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
