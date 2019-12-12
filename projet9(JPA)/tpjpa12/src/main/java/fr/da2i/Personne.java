package fr.da2i;

public class Personne {
    private int num;
    private String nom;
    private String prenom;
    private String sexe;
    private String tel;
    private String fonction;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    public String getHTML(){
        return "<td> nom = " + nom + " prenom = " + prenom + "  sexe = " + sexe + "  telephone = " + tel + " fonction = " + fonction + " </td>";
    }
}
