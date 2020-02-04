package fr.da2i;

import javax.persistence.*;

@Entity
@Table(name = "commandes", schema = "public", catalog = "pierre")
@NamedQueries({
        @NamedQuery(name = "Commandes.findById",query = "select c from Commandes c where c.cno = :cno"),
        @NamedQuery(name = "Commandes.findAll",query = "select c from Commandes c"),
        @NamedQuery(name = "Commandes.findByFno",query = "select c from Commandes c where c.fno = :fno")
})
public class CommandesEntity {
    private int cno;
    private Integer pno;
    private Integer fno;
    private Integer quantite;
    private ProduitsEntity produitsByPno;
    private FournisseursEntity fournisseursByFno;

    @Id
    @Column(name = "cno", nullable = false)
    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "pno", nullable = true)
    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    @Basic
    @Column(name = "fno", nullable = true)
    public Integer getFno() {
        return fno;
    }

    public void setFno(Integer fno) {
        this.fno = fno;
    }

    @Basic
    @Column(name = "quantite", nullable = true)
    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandesEntity that = (CommandesEntity) o;

        if (cno != that.cno) return false;
        if (pno != null ? !pno.equals(that.pno) : that.pno != null) return false;
        if (fno != null ? !fno.equals(that.fno) : that.fno != null) return false;
        if (quantite != null ? !quantite.equals(that.quantite) : that.quantite != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cno;
        result = 31 * result + (pno != null ? pno.hashCode() : 0);
        result = 31 * result + (fno != null ? fno.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pno", referencedColumnName = "pno")
    public ProduitsEntity getProduitsByPno() {
        return produitsByPno;
    }

    public void setProduitsByPno(ProduitsEntity produitsByPno) {
        this.produitsByPno = produitsByPno;
    }

    @ManyToOne
    @JoinColumn(name = "fno", referencedColumnName = "fno")
    public FournisseursEntity getFournisseursByFno() {
        return fournisseursByFno;
    }

    public void setFournisseursByFno(FournisseursEntity fournisseursByFno) {
        this.fournisseursByFno = fournisseursByFno;
    }
}
