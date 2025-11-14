package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFact {

    static final String URL = "jdbc:mysql://localhost:3307/simulacro?useSSL=false&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "root";

    static {
        // DataSource
    }

    public static Connection getConnectionDM() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
