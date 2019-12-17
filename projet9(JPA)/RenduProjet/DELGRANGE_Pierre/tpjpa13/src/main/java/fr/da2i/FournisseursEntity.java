package fr.da2i;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "fournisseurs", schema = "public", catalog = "pierre")
@NamedQueries({
        @NamedQuery(name = "Fournisseurs.findById",query = "select f from Fournisseurs f where f.fno = :fno"),
        @NamedQuery(name = "Fournisseurs.findAll",query = "select f from Fournisseurs f")
})
public class FournisseursEntity {
    private int fno;
    private String nom;
    private String ville;
    private Collection<CommandesEntity> commandesByFno;

    @Id
    @Column(name = "fno", nullable = false)
    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 50)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "ville", nullable = true, length = 50)
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FournisseursEntity that = (FournisseursEntity) o;

        if (fno != that.fno) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (ville != null ? !ville.equals(that.ville) : that.ville != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fno;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "fournisseursByFno")
    public Collection<CommandesEntity> getCommandesByFno() {
        return commandesByFno;
    }

    public void setCommandesByFno(Collection<CommandesEntity> commandesByFno) {
        this.commandesByFno = commandesByFno;
    }
}
