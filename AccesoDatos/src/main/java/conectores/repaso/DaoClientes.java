package conectores.repaso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoClientes implements Dao<Cliente> {

    private final String QUERY_GET = "SELECT * FROM clientes WHERE idCliente = ?";
    private final String QUERY_INSERT = "INSERT INTO clientes (nombre, fechaNac, altura) VALUES (?, ?, ?) ";
    private final String QUERY_INSERT_MIGRA = "INSERT INTO migrada (nombre, fechaNac, altura, migrado) VALUES (?, ?, ?, ?) ";
    private final String QUERY_FIND_ALL = "SELECT * FROM clientes";

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


        try (Connection connection = HikariFact.getConnectionHikari()) { // try connection

            // COMENZAMOS TRANSACCION, lo que este dentro del siguiente try estará incluido
            System.out.println("Empieza transaccion insertMany");
            connection.setAutoCommit(false);

            // try prepared statement
            try (PreparedStatement ps = connection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS)) {

                // Recorremos clientes añadiendo al batch
                for (Cliente cliente : list) {
                    ps.setString(1, cliente.getNombre());
                    ps.setDate(2, cliente.getFechaNac());
                    ps.setDouble(3, cliente.getAltura());

                    ps.addBatch();
                }

                // Ejecutamos batch (executeBatch!!!) y confirmamos filas afectadas
                int[] rows = ps.executeBatch();
                if (rows.length != list.size()) {
                    throw new SQLException("Inserted rows mismatch");
                }

                // Recuperamos generated keys, atentos si no hay info para alguna fila!
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    for (Cliente cliente : list) {
                        if (!keys.next()) {
                            throw new SQLException("Missing generated key for a row");
                        }
                        cliente.setIdCliente(keys.getInt(1));
                    }
                }

                // Commiteamos el executeBatch
                connection.commit();
                return true;

            } catch (SQLException sqle) { // si algo salio mal, rollback de la transacion

                try {
                    connection.rollback();
                } catch (SQLException badRollback) {
                    System.out.println(badRollback.getLocalizedMessage());
                } // rollback tambien puede fallar!

                System.err.println("El statement salio rana " + sqle.getLocalizedMessage());

            } finally {
                connection.setAutoCommit(true); // HikariCP normalmente se ocupa de esto
                System.out.println("Fin transaccion atomica insertMany");
            }


        } catch (SQLException sqle) {
            System.err.println("Se conectó regu el hikari " + sqle.getLocalizedMessage());
        }
        return false;

    }


    public boolean insertManyMigra(List<Cliente> migrados) {

        try(Connection connection = HikariFact.getConnectionHikari()){
            connection.setAutoCommit(false);

            try(PreparedStatement ps = connection.prepareStatement(QUERY_INSERT_MIGRA, Statement.RETURN_GENERATED_KEYS)){

                for (Cliente migrado : migrados) {
                    ps.setString(1, migrado.getNombre()+"_migra");
                    ps.setDate(2, migrado.getFechaNac());
                    ps.setDouble(3, migrado.getAltura());
                    ps.setBoolean(4, migrado.isMigrado());
                    ps.addBatch();
                }

                int rows[] = ps.executeBatch();
                if (rows.length != migrados.size()){
                    throw new SQLException("Inserted rows mismatch when migrating");
                }

                try(ResultSet rs = ps.getGeneratedKeys()){
                    for (Cliente migrado : migrados) {
                       if(!rs.next()){
                           throw new SQLException("Missing generated key: "+ migrado.getNombre() + " "+ migrado.getAltura());
                       }
                       migrado.setIdCliente(rs.getInt(1));
                    }
                }


                connection.commit();
                return true;

            } catch (SQLException sqle){
                try {
                    connection.rollback();
                } catch (SQLException badRollback){
                    System.out.println(badRollback.getLocalizedMessage());
                }
                System.err.println("Something wrong with the prepared statement when migrating "+ sqle.getLocalizedMessage());

                return false;
            } finally {
                System.out.println("Conexion de migracion finalizada");
                connection.setAutoCommit(true);
            }

        } catch (SQLException sqle) {
            System.err.println("Connection went wrong when migrating "+ sqle.getLocalizedMessage());
            return false;
        }

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

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = HikariFact.getConnectionHikari();
             Statement st = connection.createStatement()) {

            try (ResultSet rs = st.executeQuery(QUERY_FIND_ALL)) {

                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("idCliente"));
                    c.setNombre(rs.getString("nombre"));
                    c.setFechaNac(rs.getDate("fechaNac"));
                    c.setAltura(rs.getDouble("altura"));
                    clientes.add(c);
                }

            } catch (SQLException sqle) {
                System.err.println("Something wrong with the result set when querying all clientes");
            }


        } catch (SQLException sqle) {
            System.err.println("Error recuperando todos los clientes: " + sqle.getLocalizedMessage());
        }

        return clientes;
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
