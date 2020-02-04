package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOFournisseurs{

    private EntityManager em;

    public DAOFournisseurs() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    public boolean create(Fournisseurs entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return false;
    }

    public boolean update(Fournisseurs fournisseurs){
        em.getTransaction().begin();
        Fournisseurs fournisseursResult = em.merge(fournisseurs);
        em.getTransaction().commit();
        return fournisseursResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public Fournisseurs find(int id) {
        em.getTransaction().begin();
        Fournisseurs fournisseurs = em.find(Fournisseurs.class,id);
        em.getTransaction().commit();
        return fournisseurs;
    }

    public List<Fournisseurs> findAll(){
        em.getTransaction().begin();
        List<Fournisseurs> resultList = em.createNamedQuery("Fournisseurs.findAll", Fournisseurs.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

}
