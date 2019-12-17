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

    public boolean create(CommandesEntity entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return true;
    }

    public boolean update(CommandesEntity commandes){
        em.getTransaction().begin();
        CommandesEntity commandeResult = em.merge(commandes);
        em.getTransaction().commit();
        return commandeResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public CommandesEntity find(int id) {
        em.getTransaction().begin();
        CommandesEntity commandes = em.find(CommandesEntity.class,id);
        em.getTransaction().commit();
        return commandes;
    }

    public List<CommandesEntity> findAll(){
        em.getTransaction().begin();
        List<CommandesEntity> resultList = em.createNamedQuery("Commandes.findAll", CommandesEntity.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }
    public List<CommandesEntity> findByFno(Integer fno){
        em.getTransaction().begin();
        List<CommandesEntity> resultList = em.createNamedQuery("Commandes.findByFno", CommandesEntity.class).setParameter("fno",fno).getResultList();
        em.getTransaction().commit();
        return resultList;

    }


}
