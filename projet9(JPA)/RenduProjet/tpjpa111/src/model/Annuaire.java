package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Annuaire.findAll", query="select a from Annuaire a")
public class Annuaire {

  @Id
  private int num;
  private String fonction;
  private String nom;
  private String prenom;
  private String sexe;
  private String tel;


  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }


  public String getFonction() {
    return fonction;
  }

  public void setFonction(String fonction) {
    this.fonction = fonction;
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

  @Override
  public String toString() {
    return "Annuaire{" +
            "num=" + num +
            ", fonction='" + fonction + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", sexe='" + sexe + '\'' +
            ", tel='" + tel + '\'' +
            '}';
  }
}
