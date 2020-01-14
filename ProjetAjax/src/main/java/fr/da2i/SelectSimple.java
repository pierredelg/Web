package fr.da2i;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Select")
public class SelectSimple extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res ) {

        //On récupere les variables du fichier web.xml
        String url = getServletContext().getInitParameter("url");
        String nom = getServletContext().getInitParameter("login");
        String mdp = getServletContext().getInitParameter("mdp");
        String driver = getServletContext().getInitParameter("driver");
        String contextPath = req.getContextPath();

        //On initialise le Writer
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        //On ajoute le type de contenu
        res.setContentType("text/html;charset=UTF-8");

        //Session
        HttpSession session = req.getSession(true);


        try{
            //On charge le driver
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            session.setAttribute("erreur",e.getMessage());
        }

        Connection con = null;

        try{
            //On ouvre la connexion
            con = DriverManager.getConnection(url,nom,mdp);
        }catch(SQLException e){
            session.setAttribute("erreur",e.getMessage());
        }

        String table = "personne";
        //On constitue la requete select
        String query = "select count(*) from "+ table +";";
        int nombreColonne = 0;
        ResultSetMetaData metaData = null;
        ResultSet rs = null;

        try {

            //On exécute la requete
            PreparedStatement ps = null;
            if (con != null) {
                ps = con.prepareStatement(query);
            }

            //On récupere le résultat
            if (ps != null) {
                rs = ps.executeQuery();
            }

            //On récupere le résultat des métadatas
            if (rs != null) {
                metaData = rs.getMetaData();
            }

            //On récupere le nombre de colonne de la table
            if (metaData != null) {
                nombreColonne = metaData.getColumnCount();
                session.setAttribute("nombreColonne", nombreColonne);
            }
        }catch(SQLException e){
            session.setAttribute("erreur",e.getMessage());
        }

        if(out != null) {
            /*HEAD HTML*/
            out.println("<!doctype html>");
            out.println("<html lang=\"fr\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">");
            out.println("<title>Informations sur la table " + table.toUpperCase() + "</title>");
            out.println("</head>");
            out.println("<body class=\"container\">");

            out.println("<h1 class=\"text-secondary text-center\">Affichage de la table " + table.toUpperCase() + " :</h1>");
            /*AFFICHAGE REQUETE*/
            out.println("<div class=\"alert alert-warning\">Dernière requête exécutée : " + query + "</div>");

            /*TABLEAU*/
            out.println("<table class=\"table table-hover table-bordered table-striped card card-cascade narrower\">");

            //TITRE DES COLONNES DU TABLEAU
            out.println("<tr class=\"thead-dark d-flex w-100\" scope=\"row\">");

            for (int i = 1; i <= nombreColonne; i++) {
                String nomColonne = null;
                try {
                    nomColonne = metaData.getColumnName(i);
                } catch (SQLException e) {
                    session.setAttribute("erreur",e.getMessage());
                }
                if (nomColonne != null) {
                    out.println("<th class=\"text-center align-middle w-100\">" + nomColonne.toUpperCase() + "</th>");
                }
            }
            try {
                while (rs != null && rs.next()) {
                    out.println("<tr class=\"d-flex\" scope=\"row\">");
                    for (int i = 1; i <= nombreColonne; i++) {
                        Object valeur = rs.getObject(i);
                        out.println("<td class=\"text-center align-middle w-100\" scope=\"col\">" + valeur + "</td>");
                    }
                    out.println("</tr>");
                }
            }catch (SQLException e){
                session.setAttribute("erreur",e.getMessage());

            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            if(session.getAttribute("erreur") != null){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("Error.jsp") ;
                try {
                    requestDispatcher.forward(req,res);
                } catch (ServletException | IOException e) {
                    e.getMessage();
                }
            }
        }

        }

}
