package org.mati.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory = buildManagerFactory();

    private static EntityManagerFactory buildManagerFactory() {

        try {
            return Persistence.createEntityManagerFactory("musicaPersistencia");
        } catch (Exception e) {
            System.err.println("Error al crear Entity Manager" + e.getLocalizedMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }

    }

    public static EntityManagerFactory getEntityManagerFactory() { return entityManagerFactory; }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }


}
