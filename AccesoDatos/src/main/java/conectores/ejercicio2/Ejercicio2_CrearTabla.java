package conectores.ejercicio2;

import conectores.util.ConnectionFactory;
import conectores.model.Usuario;

import java.sql.*;

public class Ejercicio2_CrearTabla {

    public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n" +
                " id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                " nombre VARCHAR(100) NOT NULL,\n" +
                " email VARCHAR(100) NOT NULL UNIQUE)\n";

        try (Connection connection = ConnectionFactory.getConnection();

             // Para el statement necesitas la conexion
             Statement statement = connection.createStatement();) {
            // Para ejecutar la query de sql necesitas el statement
            statement.execute(sql);

            System.out.println(">> Tabla creada correctamente");


            // Para confirmar si existe: DatabaseMetaData y ResultSet
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables("usuarios", null, "usuarios", null);
            if (tables.next()){
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("La tabla existe. Nombre: "+ tableName);
            } else {
                System.out.println("La tabla no existe.");
            }


        } catch (SQLException sqle) {
            System.err.println("Error creando tabla usuarios: " + sqle.getLocalizedMessage());
        }
    }
}
