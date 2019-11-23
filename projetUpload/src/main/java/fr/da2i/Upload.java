package fr.da2i;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("identificateur : " + identificateur);

        /* Vérification afin de savoir si l'utilisateur s'est enregistré*/
        if (identificateur == null) {
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                e.getMessage();
            }
        } else {

            /*          UPLOAD          */
            List<Part> fileParts = null;
            try {
                fileParts = req.getParts().stream().filter(part -> "fichierUpload".equals(part.getName())).collect(Collectors.toList());
                // Retrieves <input type="file" name="file" multiple="true">
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }

            InputStream inputStream = null;
            try {
                if (fileParts != null) {
                    for (Part filePart : fileParts) {
                        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                        inputStream = filePart.getInputStream();
                        // ... (do your job here)
                        //TODO enregistrer un fichier dans un dossier
                        inputStream.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
