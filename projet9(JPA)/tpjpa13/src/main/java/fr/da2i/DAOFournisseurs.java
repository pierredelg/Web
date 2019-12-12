package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOFournisseurs {

    private EntityManager em;

    public DAOFournisseurs() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    public List<Fournisseurs> findById(int id){
        em.getTransaction().begin();
        List<Fournisseurs> resultList = em.createNamedQuery("Fournisseurs.findByName", Fournisseurs.class).setParameter("fno", id).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

    public List<Fournisseurs> findAll(){
        em.getTransaction().begin();
        List<Fournisseurs> resultList = em.createNamedQuery("Fournisseurs.findAll", Fournisseurs.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

    public void remove(Fournisseurs fournisseurs){
        em.getTransaction().begin();
        em.remove(fournisseurs);
        em.getTransaction().commit();
    }

    public Fournisseurs update(Fournisseurs fournisseurs){
        em.getTransaction().begin();
        Fournisseurs fournisseursResult = em.merge(fournisseurs);
        em.getTransaction().commit();
        return fournisseursResult;
    }

    public void add(Fournisseurs fournisseurs){
        em.getTransaction().begin();
        em.persist(fournisseurs);
        em.getTransaction().commit();
    }
}
