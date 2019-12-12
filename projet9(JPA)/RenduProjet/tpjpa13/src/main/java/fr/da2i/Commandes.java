package fr.da2i;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Commandes.findById",query = "select c from Commandes c where c.cno = :cno"),
        @NamedQuery(name = "Commandes.findAll",query = "select c from Commandes c"),
        @NamedQuery(name = "Commandes.findByFno",query = "select c from Commandes c where c.fno = :fno")
})
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

  public static String getHeader(){
    return "<tr class=\"w-100 text-center\" scope=\"row\"><th>CNO</th>" + "<th>PNO</th>" + "<th>FNO</th>" + "<th>QUANTITE</th></tr>";
  }

  public String getHTML(){
    return " <tr class=\"text-center\" scope=\"row\"><td>"+ cno +"</td>" + "<td>"+ pno +"</td>" + "<td>"+ fno +"</td>" + "<td>"+ quantite +"</td></tr>";
  }

}
