package conectores.repaso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepasoConectores {


    static void main() {

        try (Connection connection = HikariFact.getConnectionHikari()) {

            DaoClientes daoClientes = new DaoClientes();

            DbSetup.dropTableClientes();
            DbSetup.dropTableMigrada();

            DbSetup.createTableClientes();
            DbSetup.createTableMigrada();

            Cliente pepe = new Cliente("Pep", Date.valueOf(LocalDate.now()), 1.77);
            System.out.println((daoClientes.insertOne(pepe)? "Hemos insertao a Pepe" : "No hemos insertao al Pepe"));


            List<Cliente> lista = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                lista.add(new Cliente("nombre"+i, Date.valueOf(LocalDate.now()), 180+i));
            }
            System.out.println((daoClientes.insertMany(lista)? "Hemos insertao a mazo people" : "No hemos insertao a naide"));

            List<Cliente> originales = daoClientes.findAll();
            List<Cliente> migrados = new ArrayList<>();
            for (Cliente original : originales) {
                original.setMigrado(true);
                migrados.add(original);
            }
            System.out.println((daoClientes.insertManyMigra(migrados)? "Hemos migrao a mazo people" : "No hemos migrao a naide"));







        } catch (SQLException e) {
            System.err.println("Mala conexion bro");
        }


    }


}
