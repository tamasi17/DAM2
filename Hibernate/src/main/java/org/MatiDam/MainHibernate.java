package org.MatiDam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainHibernate {
    static void main() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try(Session session = sessionFactory.openSession()){

            session.beginTransaction();

            Estudiante estudiante = new Estudiante("Cebri", "1DAM", "cebri@cebri.com");
            Estudiante est2 = new Estudiante("Mati", "2DAM", "mats@mats.com");

            session.persist(estudiante);
            session.persist(est2);

            session.getTransaction().commit();

            System.out.println("\nIDs: "+ estudiante.getId() +", " + est2.getId());

        } finally {

            HibernateUtil.shutdown();
        }









    }
}

