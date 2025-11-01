// Ejercicio3_InsertarUsuario.java
package conectores.ejercicio3;

import conectores.util.ConnectionFactory;
import conectores.model.Usuario;

import java.sql.*;

public class Ejercicio3_InsertarUsuario {

    public static void main(String[] args) {
        String sql = "INSERT INTO usuarios(nombre, email) VALUES(?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            String nombre="Mats";
            String email="mat.eidelman@example";

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Usuario insertado correctamente. Filas afectadas: " + rowsAffected);
            } else {
                System.out.println("No se inserto el usuario");
            }

        } catch (SQLException sqle) {
            System.err.println("Error insertando usuario: " + sqle.getLocalizedMessage());
        }

    }
}
