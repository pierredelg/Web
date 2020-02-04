package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOCommandes {

    private EntityManager em;

    public DAOCommandes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    public boolean create(Commandes entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return true;
    }

    public boolean update(Commandes commandes){
        em.getTransaction().begin();
        Commandes commandeResult = em.merge(commandes);
        em.getTransaction().commit();
        return commandeResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public Commandes find(int id) {
        em.getTransaction().begin();
        Commandes commandes = em.find(Commandes.class,id);
        em.getTransaction().commit();
        return commandes;
    }

    public List<Commandes> findAll(){
        em.getTransaction().begin();
        List<Commandes> resultList = em.createNamedQuery("Commandes.findAll", Commandes.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }
    public List<Commandes> findByFno(Integer fno){
        em.getTransaction().begin();
        List<Commandes> resultList = em.createNamedQuery("Commandes.findByFno", Commandes.class).setParameter("fno",fno).getResultList();
        em.getTransaction().commit();
        return resultList;

    }


}
