package com.dam.app;

import com.dam.config.JPAUtil;
import com.dam.dao.EstudianteJPA;
import com.dam.model.Asignatura;
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
        Estudiante estudiante2 = new Estudiante("Ane", "Atochero", "atochero@ane.com", "1DAM");

        try {

            em.getTransaction().begin();  // Empezamos transaccion

            // em.persist(estudiante1); // sin dao
            daoJpa.insert(estudiante1);
            daoJpa.insert(estudiante2);


            // Creamos credenciales
            Credential gitHub = new Credential("GitHub", "ZibreGithub");
            Credential notion = new Credential("Notion", "ZibreNotion");
            Credential notion2 = new Credential("Notion", "AneNotion");

            // Asignamos credenciales
            estudiante1.addCredential(gitHub);
            estudiante1.addCredential(notion);
            estudiante2.addCredential(notion2);

            // Creamos expedientes
            Expediente exp1 = new Expediente(false, 8.9);
            Expediente exp2 = new Expediente(false, 7.3);

            // Asignamos expedientes
            estudiante1.setExpediente(exp1);
            estudiante2.setExpediente(exp2);

            // Creamos asignaturas
            Asignatura psp = new Asignatura("PSP",2,7);
            Asignatura add = new Asignatura("Acceso a Datos",2,8);

            // Asignamos asignaturas
            estudiante1.matricularse(psp);
            estudiante1.matricularse(add);
            estudiante2.matricularse(psp);

            // COMMIT !!!
            em.getTransaction().commit();


            System.out.println("Estudiante: " + estudiante1.getId() + " \n " + estudiante1.getCredenciales()
                    + "\n"+ estudiante1.getExpediente() + "\n"+ estudiante1.getAsignaturas().toString());
            System.out.println("Estudiante: " + estudiante2.getId() + " \n " + estudiante2.getCredenciales()
                    + "\n"+ estudiante2.getExpediente() + "\n"+ estudiante2.getAsignaturas().toString());

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
