package fr.da2i;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPersonne {

    private Connection connection;

    public DAOPersonne() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://psqlserv/da2i","delgranp","moi");
    }

    public DAOPersonne(Connection connection) {
        this.connection = connection;
    }

    public Personne find(String name){
        String query = "select * from annuaire where nom = ?";
        Personne personne = null;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                personne = new Personne();
                personne.setNum(rs.getInt("num"));
                personne.setNom(rs.getString("nom"));
                personne.setPrenom(rs.getString("prenom"));
                personne.setSexe(rs.getString("sexe"));
                personne.setTel(rs.getString("tel"));
                personne.setFonction(rs.getString("fonction"));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return personne;
    }

    public List<Personne> findAll(){
        List<Personne> liste = new ArrayList<>();
        String query = "select * from annuaire;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Personne personne = new Personne();
                personne.setNum(rs.getInt("num"));
                personne.setNom(rs.getString("nom"));
                personne.setPrenom(rs.getString("prenom"));
                personne.setSexe(rs.getString("sexe"));
                personne.setTel(rs.getString("tel"));
                personne.setFonction(rs.getString("fonction"));
                liste.add(personne);
            }
        }catch (Exception e){
            e.getMessage();
        }

        return liste;
    }

    public static void main(String[] args) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv/da2i","delgranp","moi");
        DAOPersonne daoPersonne = new DAOPersonne(con);
        Personne personne = daoPersonne.find("Delgrange");
        System.out.println("Personne = " + personne.getHTML());
    }
}
