package org.MatiDam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainHibernate {
    static void main() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try(Session session = sessionFactory.openSession()){

            session.beginTransaction();

            Estudiante estudiante = new Estudiante("Cebri", "1º DAM", "cebri@cebri.com");

            session.persist(estudiante);

            session.getTransaction().commit();

            System.out.println("Estudiante id: "+ estudiante.getId());

        } finally {

            HibernateUtil.shutdown();
        }









    }
}

