package com.dam.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory entityManagerFactory = buildManagerFactory();

    private static EntityManagerFactory buildManagerFactory() {
        try {

            return Persistence.createEntityManagerFactory("jpamysql");
        } catch (Exception e) {
            System.err.println("Error al crear EntityManager" + e.getLocalizedMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }


}
