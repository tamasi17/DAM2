// Ejercicio4_ListarUsuarios.java
package es.islas.dam.ejercicio4;

import es.islas.dam.util.ConnectionFactory;
import es.islas.dam.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio4_ListarUsuarios {

    public static void main(String[] args) {

        String sql = "SELECT id, nombre, email FROM usuarios";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            // recuperamos resultado (resultSet)

            // crear una lista de usuarios

        } catch (SQLException sqle) {
            System.err.println("Error listando usuarios: "+ sqle.getLocalizedMessage());
        }


    }
}
