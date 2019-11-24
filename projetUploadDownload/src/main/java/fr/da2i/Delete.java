package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
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
        }
        else {
            String nomUtilisateur = (String) session.getAttribute("nomUser");

            String applicationPath = req.getServletContext().getRealPath("");
            String filename = req.getParameter("fichierDelete");

            String fullPath = applicationPath + File.separator + nomUtilisateur + File.separator + filename;
            System.out.println("fullPath = "+ fullPath);

            File file = new File(fullPath);
            boolean ecriture = file.setWritable(true);
            System.out.println("ecriture = " + ecriture);
                try
                {
                    Files.deleteIfExists(Paths.get(fullPath));
                }
                catch(NoSuchFileException e)
                {
                    System.out.println("No such file/directory exists");
                }
                catch(DirectoryNotEmptyException e)
                {
                    System.out.println("Directory is not empty.");
                }
                catch(IOException e)
                {
                    System.out.println("Invalid permissions.");
                }

                System.out.println("Deletion successful.");

            try {
                res.sendRedirect(contextPath + "/File.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

