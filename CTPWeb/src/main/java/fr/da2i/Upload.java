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
@MultipartConfig(maxFileSize = 10*1024*1024,maxRequestSize = 20*1024*1024,fileSizeThreshold = 5*1024*1024)
public class Upload extends HttpServlet {
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
            String nomDossier = (String) session.getAttribute("nomDossier");

            String cheminDossier = Creation.NOM_DOSSIER_UTILISATEURS + File.separator + nomDossier;

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
                    if(!fileName.isEmpty()) {
                        //On écrit le fichier dans le dossier de l'utilisateur
                        part.write(uploadFilePath + File.separator + fileName);
                    }
                }
            }catch (IOException | ServletException e){
                session.setAttribute("erreur",e.getMessage());
            }
            String erreur = (String) session.getAttribute("erreur");
            if(erreur != null && !erreur.isEmpty()) {
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

    //Méthode permettant de retourner le nom du fichier
    private String getFileName(Part part) {
        //On récupere tout ce qui se trouve dans le content-disposition
        String contentDisp = part.getHeader("content-disposition");
        //On sépare chaque contenu dans un tableau
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            //On recherche le parametre filename
            if (token.trim().startsWith("filename")) {
                //On renvoie le nom du fichier sans les guillemets
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
