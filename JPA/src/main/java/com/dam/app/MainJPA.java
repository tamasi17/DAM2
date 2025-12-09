package com.dam.app;

import com.dam.config.JPAUtil;
import com.dam.dao.EstudianteJPA;
import com.dam.model.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

        EntityManager em = emf.createEntityManager();

        EstudianteJPA daoJpa = new EstudianteJPA(em);

        Estudiante estudiante1 = new Estudiante("Zibre", "Quiñon","zibre@zib.com", "2DAM");
        Estudiante estudiante2 = new Estudiante("Anne", "Atochero","atochero@ane.com", "1DAM");

        try{


        em.getTransaction().begin();

        // em.persist(estudiante1); // sin dao
        daoJpa.insert(estudiante1);
        daoJpa.insert(estudiante2);


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
