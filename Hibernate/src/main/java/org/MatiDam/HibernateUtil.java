package org.MatiDam;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(registry).buildMetadata();

            return metadata.buildSessionFactory();


        } catch (Exception e) {
            System.err.println("Error building session: " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError();
        }

    }



public static SessionFactory getSessionFactory() {
    return sessionFactory;
}

public static void shutdown() {
    if (sessionFactory!=null){
        sessionFactory.close();
    }
}

}
