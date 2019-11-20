package fr.da2i;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res ) {
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession(true);
        if(session.getAttribute("identificateur") == null){
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                e.getMessage();
            }
        }else{
            String nom = StringEscapeUtils.escapeHtml4(req.getParameter("nom"));

            //On initialise le Writer
            PrintWriter out = null;
            try {
                out = res.getWriter();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

            out.println("<!doctype html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">");
            out.println("<title> Bienvenue " + nom + "</title>");
            out.println("</head>");
            out.println("<body class=\"container\">");
            out.println("<h1 class=\"text-secondary text-center\">Bienvenue " + nom + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res ) {


        String urlBDD = getServletContext().getInitParameter("urlBDD");
        String loginBDD = getServletContext().getInitParameter("loginBDD");
        String mdpBDD = getServletContext().getInitParameter("mdpBDD");
        String driverBDD = getServletContext().getInitParameter("driverBDD");
        String contextPath = req.getContextPath();

        String login = StringEscapeUtils.escapeHtml4(req.getParameter("login"));
        System.out.println("login = " + login);
        String mdp = StringEscapeUtils.escapeHtml4(req.getParameter("mdp"));
        System.out.println("mdp = " + mdp);


        try{
            //On charge le driver
            Class.forName(driverBDD);
        }catch(ClassNotFoundException e){
            e.getMessage();
        }

        Connection con = null;
        try{
            //On ouvre la connexion
            con = DriverManager.getConnection(urlBDD,loginBDD,mdpBDD);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        //On constitue la requete select
        String query = "select * from personne where login=? and mdp=?;";

        PreparedStatement ps = null;
        try {
            if (con != null) {
                ps = con.prepareStatement(query);
                ps.setString(1,login);
                ps.setString(2,mdp);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ResultSet rs = null;
        try {
            if (ps != null) {
                rs = ps.executeQuery();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        String loginFromBDD = null;
        String mdpFromBDD = null;
        String nomFromBDD = null;
        try {
            while(rs != null && rs.next()){
                loginFromBDD = rs.getString("login");
                System.out.println("login bdd =" + loginFromBDD);

                mdpFromBDD = rs.getString("mdp");
                System.out.println("mdp bdd = " + mdpFromBDD);

                nomFromBDD = rs.getString("nom");
                System.out.println("nom bdd = " + nomFromBDD);


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(login != null && login.equals(loginFromBDD) && mdp != null && mdp.equals(mdpFromBDD)){
            try {
                HttpSession session = req.getSession();
                session.setAttribute("identificateur",login);
                res.sendRedirect(contextPath + "/Servlet?nom="+ nomFromBDD);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else{
            try {
                res.sendRedirect(contextPath + "/login.html");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try{
            //On ferme la connexion
            if (con != null) {
                con.close();
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}