package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        /*Redirection sur la page de login*/
        String contextPath = req.getContextPath();
        try {
            res.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {

        String contextPath = req.getServletContext().getContextPath();
        HttpSession session = req.getSession();
        String identificateur = (String) session.getAttribute("identificateur");

        /* Vérification afin de savoir si l'utilisateur s'est enregistré*/
        if (identificateur == null) {
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            //On récupere le nom de l'utilisateur en session
            String nomUtilisateur = (String) session.getAttribute("nomUser");

            //On récupere le nom du fichier à supprimer
            String filename = req.getParameter("fichierDelete");

            //On récupère le chemin du context
            String applicationPath = req.getServletContext().getRealPath("");

            //On construit le chemin vers le fichier à supprimer
            String fullPath = applicationPath + File.separator + "users" + File.separator + nomUtilisateur + File.separator + filename;

            File file = new File(fullPath);

            //On ajoute les droits d'écriture si on ne les as pas deja
            if(!file.canWrite()) {
               file.setWritable(true);
            }

            //On supprime le fichier
            boolean delete = file.delete();

            try {
                res.sendRedirect(contextPath + "/File.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

