package conectores.repaso;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HikariFact {

    static final String URL = "jdbc:mysql://localhost:3307/repaso?useSSL=false&serverTimezone=UTC" +
            "&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "root";

    static HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.setMaximumPoolSize(3);
        ds = new HikariDataSource(config);

    }

    static Connection getConnectionDM() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    static Connection getConnectionHikari() throws SQLException {
        return ds.getConnection();
    }

}
