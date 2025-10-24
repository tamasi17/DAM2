package es.islas.dam.ejercicio2;

import es.islas.dam.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2_CrearTabla {

    public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n" +
                " id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                " nombre VARCHAR(100) N OT NULL,\n" +
                " email VARCHAR(100) NOT NULL UNIQUE)\n";

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();) {

            statement.execute(sql);

        } catch (SQLException sqle) {
            System.err.println("Error insertando usuario: " + sqle.getLocalizedMessage());
        }
    }
}
