package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Servlet permettant de supprimer un fichier du dossier de l'utilisateur.
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        /*Redirection sur la page de login*/
        String contextPath = req.getContextPath();
        try {
            res.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
            }
        } else {
            //On récupere le nom de l'utilisateur en session
            String nomDossier = (String) session.getAttribute("nomDossier");

            //On récupere le nom du fichier à supprimer
            String filename = req.getParameter("fichierDelete");

            //On récupère le chemin du context
            String applicationPath = req.getServletContext().getRealPath("");

            //On construit le chemin vers le fichier à supprimer
            String fullPath = applicationPath + File.separator + "users" + File.separator + nomDossier + File.separator + filename;

            File file = new File(fullPath);

            //On ajoute les droits d'écriture si on ne les as pas deja
            if(!file.canWrite()) {
               file.setWritable(true);
            }

            //On supprime le fichier
            boolean delete = file.delete();
            if(!delete) {
                session.setAttribute("erreur","Le fichier n'a pas pu être supprimé");
                try {
                    res.sendRedirect(contextPath + "/Error.jsp");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }else {
                try {
                    res.sendRedirect(contextPath + "/File.jsp");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

