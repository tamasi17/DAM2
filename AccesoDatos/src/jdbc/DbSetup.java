package jdbc;

import java.sql.*;

public class DbSetup {


    public static void crearTablaUsuarios() throws SQLException {
        String tabla1 = "CREATE TABLE IF NOT EXISTS usuarios (" +
                " id INT AUTO_INCREMENT PRIMARY KEY," +
                " nombre VARCHAR(100) NOT NULL," +
                " email VARCHAR(150) NOT NULL UNIQUE)";


        try (Connection c = ConnectionFact.getConnectionDM()){

            Statement st = c.createStatement();
            st.execute(tabla1);

            System.out.println("Tabla usuarios creada correctamente");

        }

    }

    public static void crearTablaTelefonos() throws SQLException {

        String tabla2 = "CREATE TABLE IF NOT EXISTS telefonos (" +
                " id INT AUTO_INCREMENT PRIMARY KEY," +
                " usuario_id INT NOT NULL," +
                " numero VARCHAR(30) NOT NULL," +
                " tipo VARCHAR(30) NOT NULL," +
                " FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE)";

        try (Connection c = ConnectionFact.getConnectionDM()){

            Statement st = c.createStatement();
            st.execute(tabla2);

            System.out.println("Tabla telefonos creada correctamente");

            DatabaseMetaData databaseMetaData = c.getMetaData();
           // ResultSet rs = databaseMetaData.getTables()

        }
    }


}
