package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/Download")
public class Download extends HttpServlet {

    private static final int TAILLE_BUFFER = 2048;

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

            String nomUser = (String) session.getAttribute("nomUser");
            String fichierDownload = req.getParameter("fichierDownload");
            res.setContentType("text/plain");
            res.setHeader("Content-disposition", "attachment; filename=" + fichierDownload);
            InputStream in = req.getServletContext().getResourceAsStream("./users/" + nomUser + "/" + fichierDownload);
            OutputStream outputStream = null;
            try {
                outputStream = res.getOutputStream();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            byte[] buffer = new byte[TAILLE_BUFFER];

            int numBytesRead;
            try {
                while (in != null && (numBytesRead = in.read(buffer)) > 0) {
                    if (outputStream != null) {
                        outputStream.write(buffer, 0, numBytesRead);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try {
                if (in != null) {
                    in.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
