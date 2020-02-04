package fr.da2i;

import fr.da2i.modele.Adresse;
import fr.da2i.modele.Employe;
import fr.da2i.modele.Personne;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res ) {

        Personne personne = new Employe();
        personne.setNom("Paul");
        req.setAttribute("personne", personne);

        Employe emp = new Employe();
        Adresse add = new Adresse();
        add.setAdresse("Lille");
        emp.setAdresse(add);
        emp.setId(1);
        emp.setNom("Pierre");
        HttpSession session = req.getSession();
        session.setAttribute("employe", emp);

        //On ne met pas de blanc point-virgule ou virgule dans un cookie
        res.addCookie(new Cookie("User.Cookie","Chez_Utilisateur"));

        getServletContext().setAttribute("User.Cookie","Dans Le Contexte");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Home.jsp");
        try {
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
