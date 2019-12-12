package fr.da2i;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Fournisseurs.findById",query = "select f from Fournisseurs f where f.fno = :fno"),
        @NamedQuery(name = "Fournisseurs.findAll",query = "select f from Fournisseurs f")
})
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
