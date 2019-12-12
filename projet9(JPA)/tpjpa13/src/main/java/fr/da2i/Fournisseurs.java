package fr.da2i;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fournisseurs {

  @Id
  private int fno;
  private String nom;
  private String ville;


  public int getFno() {
    return fno;
  }

  public void setFno(int fno) {
    this.fno = fno;
  }


  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }


  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

}
