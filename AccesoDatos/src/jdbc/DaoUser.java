package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUser {

    private Connection connection;

    public DaoUser(Connection connection) {
        this.connection = connection;
    }

    /*
         inserte un usuario nuevo en la tabla usuarios y devuelva el id generado automáticamente por MySQL.
         Para ello, deberás usar PreparedStatement.RETURN_GENERATED_KEYS junto con el método getGeneratedKeys()
         */
    public int insertar(String nombre, String email) throws SQLException {

        String sql = "INSERT INTO usuarios (nombre,email) VALUES(?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){


                ps.setString(1,nombre);
                ps.setString(2,email);
                if (ps.executeUpdate() == 0){
                    throw new SQLException("No se ha insertado el usuario "+ nombre);
                }

                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    rs.getInt(1);
                }


        } catch (SQLException sqle) {
            throw new SQLException(sqle);
        }
    }

    public ArrayList<User> findAll() {

    }

    findByPhone(int telefono){

    }

    update(int id){

    }
}
