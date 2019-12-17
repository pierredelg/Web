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

    public boolean create(FournisseursEntity entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return false;
    }

    public boolean update(FournisseursEntity fournisseurs){
        em.getTransaction().begin();
        FournisseursEntity fournisseursResult = em.merge(fournisseurs);
        em.getTransaction().commit();
        return fournisseursResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public FournisseursEntity find(int id) {
        em.getTransaction().begin();
        FournisseursEntity fournisseurs = em.find(FournisseursEntity.class,id);
        em.getTransaction().commit();
        return fournisseurs;
    }

    public List<FournisseursEntity> findAll(){
        em.getTransaction().begin();
        List<FournisseursEntity> resultList = em.createNamedQuery("Fournisseurs.findAll", FournisseursEntity.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

}
