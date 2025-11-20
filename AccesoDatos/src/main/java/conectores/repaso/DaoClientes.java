package conectores.repaso;

import java.sql.*;
import java.util.List;

public class DaoClientes implements Dao<Cliente> {

    private final String QUERY_GET = "SELECT * FROM clientes WHERE idCliente = ?";
    private final String QUERY_INSERT = "INSERT INTO clientes (nombre, fechaNac, altura) VALUES (?, ?, ?) ";

    @Override
    public Cliente get(int id) {

        Cliente cliente = null;

        try (Connection connection = HikariFact.getConnectionHikari()) {


            try (PreparedStatement ps = connection.prepareStatement(QUERY_GET)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return extractClient(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }


        return cliente;
    }

    public Cliente extractClient(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setFechaNac(rs.getDate("fechaNac"));
        cliente.setAltura(rs.getDouble("altura"));
        return cliente;
    }

    @Override
    public boolean insertOne(Cliente entity) {

        try (Connection connection = HikariFact.getConnectionHikari();
             PreparedStatement ps = connection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getNombre());
            ps.setDate(2, entity.getFechaNac());
            ps.setDouble(3, entity.getAltura());

            int rows = ps.executeUpdate();

            if (rows <= 0) {
                throw new SQLException("No rows affected");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setIdCliente(keys.getInt(1));
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Mu mal insertao uno");
            return false;
        }

    }

    @Override
    public boolean insertMany(List<Cliente> list) {

        Connection connection = null;

        try(connection = HikariFact.getConnectionHikari()){

            connection.setAutoCommit(false);
            System.out.println("Empieza transaccion insertMany");

            try(PreparedStatement ps = connection.prepareStatement(QUERY_INSERT)){

                for (Cliente cliente : list) {

                ps.setString(1,);
                }


            } catch (SQLException sqle){
                System.err.println("El statement salio rana "+ sqle.getLocalizedMessage());
            }





        } catch (SQLException sqlee) {
            System.err.println("Se conectó regu el hikari");
        } finally {
            System.out.println("Fin transaccion atomica insertMany");
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean insertMany(List<Cliente> list, Connection connection) {
        return false;
    }

    @Override
    public boolean exists(int id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<Cliente> findAll() {
        return List.of();
    }

    @Override
    public List<Cliente> findByAttr(Cliente filtro) {
        return List.of();
    }

    @Override
    public int countRows() {
        return 0;
    }


}
