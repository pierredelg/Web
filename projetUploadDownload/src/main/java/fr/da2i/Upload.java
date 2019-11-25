package fr.da2i;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

/**
 * Servlet permettant l'upload d'un ou de plusieurs fichiers sélectionnés.
 */
@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
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
            String nomUtilisateur = (String) session.getAttribute("nomUser");

            String cheminDossier = Creation.NOM_DOSSIER_FICHIER + File.separator + nomUtilisateur;

            // On récupere le chemin de la servlet
            String applicationPath = req.getServletContext().getRealPath("");

            // On construit le chemin complet du dossier de l'utilisateur
            String uploadFilePath = applicationPath + File.separator + cheminDossier;

            try {
                String fileName;
                //On récupere chaques partie de la requete
                for (Part part : req.getParts()) {
                    //On récupere le nom du fichier
                    fileName = getFileName(part);
                    //On écrit le fichier dans le dossier de l'utilisateur
                    part.write(uploadFilePath + File.separator + fileName);
                }
            }catch (IOException | ServletException e){
                System.out.println(e.getMessage());
            }

            try {
                res.sendRedirect(contextPath + "/File.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Méthode permettant de retourner le nom du fichier
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
