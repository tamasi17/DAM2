package conectores.repaso;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class RepasoConectores {


    static void main() {

        try (Connection connection = HikariFact.getConnectionHikari()) {

            DaoClientes daoClientes = new DaoClientes();

            DbSetup.createTableClientes();

            Cliente pepe = new Cliente("Pep", Date.valueOf(LocalDate.now()), 1.77);
            System.out.println((daoClientes.insertOne(pepe)? "Hemos insertao a Pepe" : "No hemos insertao al Pepe"));



        } catch (SQLException e) {
            System.err.println("Mala conexion bro");
        }


    }


}
