package conectores.repaso;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSetup {

    public static void createTableClientes(){


        String sql = """
                CREATE TABLE IF NOT EXISTS clientes (
                idCliente INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                fechaNac DATE DEFAULT (CURRENT_DATE),
                altura DOUBLE
                )
                """;

        try (Connection connection = HikariFact.getConnectionHikari()){

            try (Statement st = connection.createStatement()){
                st.execute(sql);
            }


        } catch (SQLException e) {
            System.err.println("Mu mal creada");
        }


    }

}
