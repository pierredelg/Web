package fr.da2i;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Count")
public class Count extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res ) {

        //On récupere les variables du fichier web.xml
        String url = getServletContext().getInitParameter("url");
        String nom = getServletContext().getInitParameter("login");
        String mdp = getServletContext().getInitParameter("mdp");
        String driver = getServletContext().getInitParameter("driver");

        //On initialise le Writer
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        //On ajoute le type de contenu
        res.setContentType("text/html;charset=UTF-8");


        try {
            //On charge le driver
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }

        Connection con = null;

        try {
            //On ouvre la connexion
            con = DriverManager.getConnection(url, nom, mdp);
        } catch (SQLException e) {
            e.getMessage();
        }

        //On constitue la requete select

        String query = "select count(*) from personne;";

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
            System.out.println(rs.getRow());
        }catch(SQLException e){
            e.getMessage();
        }
        String nombre = null;
        try {
            if (rs != null) {
                nombre = rs.getString("count");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        System.out.println("nombre = " + nombre);
        out.print(nombre);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
