package com.dam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

        EntityManager em = emf.createEntityManager();

        Estudiante estudiante1 = new Estudiante("Zibre", "Quiñon","zibre@zib.com", "2DAM");
        Estudiante estudiante2 = new Estudiante("Anne", "Atochero","atochero@ane.com", "1DAM");

        try{


        em.getTransaction().begin();

        em.persist(estudiante1);
        em.persist(estudiante2);

        em.getTransaction().commit();

        System.out.println("Estudiante 1: "+ estudiante1.getId());
        System.out.println("Estudiante 2: "+ estudiante1.getId());

        } catch (Exception e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
            JPAUtil.shutdown();
        }

    }

}
