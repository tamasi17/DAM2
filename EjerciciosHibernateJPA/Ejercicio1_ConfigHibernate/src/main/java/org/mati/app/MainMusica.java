package org.mati.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.mati.config.JpaUtil;

public class MainMusica {

    /* Crear y persistir un usuario:
    Abrir un EntityManager.
    Iniciar una transacción.
    Crear un objeto Usuario.
    Persistirlo.
    Hacer commit.
     */

    EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager();


    /*
    Consultar al usuario por id
    Usar em.find(Usuario.class, id).
3. Actualizar un atributo del usuario
    Modificar el email.
    Confirmar los cambios con commit.
4. Eliminar el usuario
    Llamar a em.remove().
    Hacer commit.
5. Cerrar correctamente los recursos
    Cerrar el EntityManager.
    Cerrar el EntityManagerFactory mediante JpaUtil.close().

     */


}
