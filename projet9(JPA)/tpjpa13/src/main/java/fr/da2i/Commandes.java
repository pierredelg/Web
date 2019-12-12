package fr.da2i;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Commandes {

  @Id
  private int cno;
  private int pno;
  private int fno;
  private int quantite;


  public int getCno() {
    return cno;
  }

  public void setCno(int cno) {
    this.cno = cno;
  }


  public int getPno() {
    return pno;
  }

  public void setPno(int pno) {
    this.pno = pno;
  }


  public int getFno() {
    return fno;
  }

  public void setFno(int fno) {
    this.fno = fno;
  }


  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantite) {
    this.quantite = quantite;
  }

}
