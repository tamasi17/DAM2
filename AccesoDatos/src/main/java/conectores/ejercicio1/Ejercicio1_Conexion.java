package conectores.ejercicio1;

import conectores.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Ejercicio1_Conexion {

    public static void main(String[] args) {

        {
            try (Connection connection = ConnectionFactory.getConnection();) {

                // Es necesario crear la base de datos primero:
                // En workbench: CREATE DATABASE usuarios;
                System.out.println(">> Conexion establecida");

            } catch (SQLException sqle) {
                System.err.println("Error al conectarse a la base de datos: " + sqle.getSQLState());
                System.err.println("Mensaje: "+ sqle.getMessage());
                System.err.println("Database-specific error code: "+ sqle.getErrorCode());
            }
        }

    }
}
