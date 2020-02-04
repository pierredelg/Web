package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        EntityManager em = emf.createEntityManager();

        Annuaire annuaire = new Annuaire();
        annuaire.setNum(1);
        annuaire.setNom("Delgrange");
        annuaire.setPrenom("pierre");
        annuaire.setSexe("M");
        annuaire.setTel("012135135");
        annuaire.setFonction("etudiant");
        em.getTransaction().begin();
        em.persist(annuaire);
        em.getTransaction().commit();

        Annuaire annuaire2 = new Annuaire();
        annuaire2.setNum(2);
        annuaire2.setNom("Mathieu");
        annuaire2.setPrenom("philippe");
        annuaire2.setSexe("M");
        annuaire2.setTel("0111111111");
        annuaire2.setFonction("professeur");
        em.getTransaction().begin();
        em.persist(annuaire2);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Annuaire find = em.find(Annuaire.class,2);
        em.getTransaction().commit();
        System.out.println("FIND 2 : \n" + find + "\n");

        annuaire2.setTel("0222222222");
        em.getTransaction().begin();
        Annuaire update = em.merge(annuaire2);
        em.getTransaction().commit();
        System.out.println("Update : \n" + update + "\n");

        em.getTransaction().begin();
        TypedQuery<Annuaire> query = em.createNamedQuery("Annuaire.findAll", Annuaire.class);
        em.getTransaction().commit();

        List<Annuaire> resultList = query.getResultList();
        System.out.println("Debut de liste : ");
        for (Annuaire annuaire1: resultList){
            System.out.println(annuaire1);
        }
        System.out.println("Fin de la liste.");

        em.getTransaction().begin();
        em.remove(update);
        em.getTransaction().commit();
        System.out.println("\nSuppression de l'update\n");

        em.getTransaction().begin();
        TypedQuery<Annuaire> query2 = em.createNamedQuery("Annuaire.findAll", Annuaire.class);
        em.getTransaction().commit();

        List<Annuaire> resultList2 = query2.getResultList();
        System.out.println("Debut de liste : ");
        for (Annuaire annuaire3: resultList2){
            System.out.println(annuaire3);
        }
        System.out.println("Fin de la liste.");

    }
}
