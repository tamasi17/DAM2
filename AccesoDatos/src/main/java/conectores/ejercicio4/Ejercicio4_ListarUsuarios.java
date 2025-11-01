// Ejercicio4_ListarUsuarios.java
package conectores.ejercicio4;

import conectores.util.ConnectionFactory;
import conectores.model.Usuario;

import java.sql.*;
import java.util.*;

public class Ejercicio4_ListarUsuarios {

    public static void main(String[] args) {

        String sql = "SELECT id, nombre, email FROM usuarios";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<Usuario> usuarioArrayList = new ArrayList<>();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");

                Usuario usuario = new Usuario(id, nombre, email);
                usuarioArrayList.add(usuario);
            }

            usuarioArrayList.forEach(System.out::println);

        } catch (SQLException sqle) {
            System.err.println("Error listando usuarios: "+ sqle.getLocalizedMessage());
        }


    }
}
