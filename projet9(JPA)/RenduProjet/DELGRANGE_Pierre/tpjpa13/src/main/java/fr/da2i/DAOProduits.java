package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOProduits{

    private EntityManager em;

    public DAOProduits() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    public boolean create(ProduitsEntity entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return true;
    }

    public boolean update(ProduitsEntity produits){
        em.getTransaction().begin();
        ProduitsEntity produitsResult = em.merge(produits);
        em.getTransaction().commit();
        return produitsResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public ProduitsEntity find(int id) {
        em.getTransaction().begin();
        ProduitsEntity produits = em.find(ProduitsEntity.class,id);
        em.getTransaction().commit();
        return produits;
    }

    public List<ProduitsEntity> findAll(){
        em.getTransaction().begin();
        List<ProduitsEntity> resultList = em.createNamedQuery("Produits.findAll", ProduitsEntity.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }


}
