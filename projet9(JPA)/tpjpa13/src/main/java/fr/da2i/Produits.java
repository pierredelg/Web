package fr.da2i;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produits {

  @Id
  private int pno;
  private String libelle;
  private int prix;


  public int getPno() {
    return pno;
  }

  public void setPno(int pno) {
    this.pno = pno;
  }


  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }


  public int getPrix() {
    return prix;
  }

  public void setPrix(int prix) {
    this.prix = prix;
  }

}
