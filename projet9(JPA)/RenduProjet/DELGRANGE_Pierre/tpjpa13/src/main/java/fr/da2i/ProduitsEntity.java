package fr.da2i;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "produits", schema = "public", catalog = "pierre")
@NamedQueries({
        @NamedQuery(name = "Produits.findById",query = "select p from Produits p where p.pno = :pno"),
        @NamedQuery(name = "Produits.findAll",query = "select p from Produits p")
})
public class ProduitsEntity {
    private int pno;
    private String libelle;
    private Integer prix;
    private Collection<CommandesEntity> commandesByPno;

    @Id
    @Column(name = "pno", nullable = false)
    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    @Basic
    @Column(name = "libelle", nullable = true, length = 100)
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Basic
    @Column(name = "prix", nullable = true)
    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduitsEntity that = (ProduitsEntity) o;

        if (pno != that.pno) return false;
        if (libelle != null ? !libelle.equals(that.libelle) : that.libelle != null) return false;
        if (prix != null ? !prix.equals(that.prix) : that.prix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pno;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "produitsByPno")
    public Collection<CommandesEntity> getCommandesByPno() {
        return commandesByPno;
    }

    public void setCommandesByPno(Collection<CommandesEntity> commandesByPno) {
        this.commandesByPno = commandesByPno;
    }
}
