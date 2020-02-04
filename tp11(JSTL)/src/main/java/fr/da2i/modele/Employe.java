package fr.da2i.modele;

public class Employe implements Personne{
    private String nom;
    private int id;
    private Adresse adresse;
    public Employe() {}
    public String getNom() { return this.nom; }
    public void setNom(String nom) { this.nom = nom; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public String toString() {
        return "ID=" + id + ",Nom=" + nom + ",Adresse=" + adresse;
    }
}
