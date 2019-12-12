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

    public boolean create(Produits entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return true;
    }

    public boolean update(Produits produits){
        em.getTransaction().begin();
        Produits produitsResult = em.merge(produits);
        em.getTransaction().commit();
        return produitsResult != null;
    }

    public boolean delete(int id) {
        em.getTransaction().begin();
        em.remove(find(id));
        em.getTransaction().commit();
        return false;
    }

    public Produits find(int id) {
        em.getTransaction().begin();
        Produits produits = em.find(Produits.class,id);
        em.getTransaction().commit();
        return produits;
    }

    public List<Produits> findAll(){
        em.getTransaction().begin();
        List<Produits> resultList = em.createNamedQuery("Produits.findAll", Produits.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }


}
