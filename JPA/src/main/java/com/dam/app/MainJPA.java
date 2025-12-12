package com.dam.app;

import com.dam.config.JPAUtil;
import com.dam.dao.EstudianteJPA;
import com.dam.model.Credential;
import com.dam.model.Estudiante;
import com.dam.model.Expediente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

        EntityManager em = emf.createEntityManager();

        EstudianteJPA daoJpa = new EstudianteJPA(em);

        Estudiante estudiante1 = new Estudiante("Zibre", "Quiñon", "zibre@zib.com", "2DAM");
        Estudiante estudiante2 = new Estudiante("Anne", "Atochero", "atochero@ane.com", "1DAM");

        try {

            em.getTransaction().begin();  // Empezamos transaccion

            // em.persist(estudiante1); // sin dao
            daoJpa.insert(estudiante1);
            daoJpa.insert(estudiante2);


            // Creamos credenciales
            Credential gitHub = new Credential("GitHub", "ZibreGithub");
            Credential notion = new Credential("Notion", "ZibreNotion");

            // Asignamos credenciales
            estudiante1.addCredential(gitHub);
            estudiante1.addCredential(notion);

            // Creamos expedientes
            Expediente exp1 = new Expediente(false, 8.9);
            Expediente exp2 = new Expediente(false, 7.3);

            // Asignamos expedientes
            estudiante1.setExpediente(exp1);
            estudiante2.setExpediente(exp2);


            // COMMIT !!!
            em.getTransaction().commit();


            System.out.println("Estudiante 1: " + estudiante1.getId() + " - " + estudiante1.getCredenciales());
            System.out.println("Estudiante 2: " + estudiante1.getId() + " - " + estudiante2.getCredenciales());

        } catch (Exception e) {
            e.printStackTrace();

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            JPAUtil.shutdown();
        }

    }

}
