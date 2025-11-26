package conectores.repaso;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSetup {

    private static final String QUERY_CREATE_CLIENTES = """
                CREATE TABLE IF NOT EXISTS clientes (
                idCliente INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                fechaNac DATE DEFAULT (CURRENT_DATE),
                altura DOUBLE
                )
                """;

    private static final String QUERY_CREATE_MIGRADA = """
                CREATE TABLE IF NOT EXISTS migrada (
                idCliente INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL,
                fechaNac DATE DEFAULT (CURRENT_DATE),
                altura DOUBLE,
                migrado BOOLEAN DEFAULT TRUE
                )
                """;

    private static final String QUERY_DROP_CLIENTES = "DROP TABLE clientes";
    private static final String QUERY_DROP_MIGRADA = "DROP TABLE migrada";

    public static void createTableClientes(){

        try (Connection connection = HikariFact.getConnectionHikari()){

            try (Statement st = connection.createStatement()){
                st.execute(QUERY_CREATE_CLIENTES);
            }

        } catch (SQLException e) {
            System.err.println("Mu mal creada, la tabla clientes");
        }
    }

    public static void createTableMigrada(){

        try (Connection connection = HikariFact.getConnectionHikari()){

            try (Statement st = connection.createStatement()){
                st.execute(QUERY_CREATE_MIGRADA);
            }

        } catch (SQLException e) {
            System.err.println("Mu mal creada, la tabla migrada");
        }
    }

    public static void dropTableClientes(){

        try(Connection connection = HikariFact.getConnectionHikari()){

            try ( Statement st = connection.createStatement()){

                st.execute(QUERY_DROP_CLIENTES);

            }

        } catch (SQLException sqle) {
            System.err.println("Salio mal lo de borrar clientes "+ sqle.getLocalizedMessage());
        }

    }

    public static void dropTableMigrada(){

        try(Connection connection = HikariFact.getConnectionHikari()){

            try ( Statement st = connection.createStatement()){

                st.execute(QUERY_DROP_MIGRADA);

            }

        } catch (SQLException sqle) {
            System.err.println("Salio mal lo de borrar migrada "+ sqle.getLocalizedMessage());
        }

    }

}
