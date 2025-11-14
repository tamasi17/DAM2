package jdbc;

import jdbc.simulacro.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUser implements Dao<User> {

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


            // **VALIDACIONES** antes del try: email es un valor unico, existe en la bbdd?

                ps.setString(1,nombre);
                ps.setString(2,email);
                if (ps.executeUpdate() == 0){
                    throw new SQLException("No se ha insertado el usuario "+ nombre);
                }

                int id = 0;
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    id = rs.getInt(1);
                }

                return id;

        } catch (SQLException sqle) {
            throw new SQLException(sqle);
        }
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void insert(User entity) {

    }

    @Override
    public void insert(List<User> entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean exists(int id) {
        return false;
    }

////    public ArrayList<User> findAll() {
////
////    }
//
//    @Override
//    public List<User> findByAttributes(User filtro) {
//        return List.of();
//    }
//
////    findByPhone(int telefono){
////
////    }
////
////    update(int id){
////
////    }
}
