package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOCommandes implements ClientDAO<Commandes>{

    private EntityManager em;

    public DAOCommandes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    @Override
    public boolean create(Commandes entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Commandes commandes){
        em.getTransaction().begin();
        Commandes commandeResult = em.merge(commandes);
        em.getTransaction().commit();
        return commandeResult != null;
    }

    @Override
    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    @Override
    public Commandes find(int id) {
        em.getTransaction().begin();
        List<Commandes> resultList = em.createNamedQuery("Commandes.findById", Commandes.class).setParameter("cno", id).getResultList();
        em.getTransaction().commit();
        return resultList.get(0);
    }

    @Override
    public List<Commandes> findAll(){
        em.getTransaction().begin();
        List<Commandes> resultList = em.createNamedQuery("Commandes.findAll", Commandes.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }


}
