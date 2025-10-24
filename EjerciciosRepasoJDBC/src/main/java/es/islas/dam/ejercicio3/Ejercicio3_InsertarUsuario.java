// Ejercicio3_InsertarUsuario.java
package es.islas.dam.ejercicio3;

import es.islas.dam.util.ConnectionFactory;


import java.sql.*;

public class Ejercicio3_InsertarUsuario {

    public static void main(String[] args) {
        String sql = "INSERT INTO usuarios(nombre, email) VALUES(?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();) {

            statement.execute(sql);

            // comprobar si se ha introducido correctamente el usuario

        } catch (SQLException sqle) {
            System.err.println("Error insertando usuario: " + sqle.getLocalizedMessage());
        }

    }
}
