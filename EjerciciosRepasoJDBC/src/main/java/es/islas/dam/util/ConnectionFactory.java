package es.islas.dam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/usuarios?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "tupassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
