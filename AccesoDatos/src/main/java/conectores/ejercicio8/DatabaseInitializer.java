package conectores.ejercicio8;

import conectores.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String SQL_CREATE = """
        CREATE TABLE IF NOT EXISTS usuarios (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            email VARCHAR(100) NOT NULL UNIQUE
        )
        """;


    public static void crearTabla() {

    }


    public static void main(String[] args) {
        crearTabla();
    }
}
