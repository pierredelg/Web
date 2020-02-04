package fr.da2i;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOPersonne {

    private EntityManager em;

    public DAOPersonne() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpjpa");
        this.em = emf.createEntityManager();
    }

    public List<Personne> findByName(String name){
        em.getTransaction().begin();
        List<Personne> resultList = em.createNamedQuery("Personne.findByName", Personne.class).setParameter("nom", name).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

    public List<Personne> findAll(){
        em.getTransaction().begin();
        List<Personne> resultList = em.createNamedQuery("Personne.findAll", Personne.class).getResultList();
        em.getTransaction().commit();
        return resultList;

    }

    public void remove(Personne personne){
        em.getTransaction().begin();
        em.remove(personne);
        em.getTransaction().commit();
    }

    public Personne update(Personne personne){
        em.getTransaction().begin();
        Personne personneResult = em.merge(personne);
        em.getTransaction().commit();
        return personneResult;
    }

    public void add(Personne personne){
        em.getTransaction().begin();
        em.persist(personne);
        em.getTransaction().commit();
    }

    public static void main(String[] args){
        DAOPersonne daoPersonne = new DAOPersonne();
        Personne personne1 = new Personne();
        personne1.setNum(1);
        personne1.setNom("Delgrange");
        personne1.setPrenom("Pierre");
        personne1.setSexe("M");
        personne1.setTel("11111111111");
        personne1.setFonction("étudiant");

        Personne personne2 = new Personne();
        personne2.setNum(2);
        personne2.setPrenom("Philippe");
        personne2.setNom("Mathieu");
        personne2.setSexe("M");
        personne2.setTel("2222222222");
        personne2.setFonction("professeur");

        daoPersonne.add(personne1);
        System.out.println("\nAjout de " + personne1 + "\n");

        daoPersonne.add(personne2);
        System.out.println("Ajout de " + personne2 + "\n");

        System.out.println("Modification de " + personne2);
        personne2.setTel("3333333333");
        personne2 = daoPersonne.update(personne2);
        System.out.println("\nRésultat de la modification : " + personne2);
        System.out.println("\nFIND By Name : nom = " + personne2.getNom());
        List<Personne> personneList = daoPersonne.findByName(personne2.getNom());
        for (Personne personne3 : personneList){
            System.out.println(personne3);
        }
        daoPersonne.remove(personne2);
        System.out.println("\nSuppression de "+ personne2 + "\n");
        System.out.println("FIND ALL : ");
        List<Personne> personneAll = daoPersonne.findAll();
        for (Personne personne4 : personneAll){
            System.out.println(personne4);
        }
        System.out.println();
    }
}
