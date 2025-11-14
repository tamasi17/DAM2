package jdbc;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementa un programa en Java que gestione una relación uno a muchos (1-N) entre dos
 * tablas de una base de datos MySQL utilizando JDBC.
 * El propósito es mostrar cómo se relacionan los datos entre tablas y por qué, al insertar
 * registros vinculados, resulta necesario recuperar la clave generada automáticamente por la
 * base de datos para mantener la coherencia entre las inserciones.
 * Es necesario crear un sistema para registrar usuarios y sus teléfonos.
 * Cada usuario puede tener varios teléfonos, pero cada teléfono pertenece a un único usuario.
 * Por tanto, estamos trabajando con una relación 1-N (uno a muchos) entre las tablas
 * usuarios y telefonos.
 */
public class MainSimulacro {

    static void main() {

        try(Connection c = ConnectionFact.getConnectionDM()){

            try {
                DbSetup.crearTablaUsuarios();
                DbSetup.crearTablaTelefonos();
            } catch (SQLException sqle) {
                System.err.println("Problema generando las tablas usuarios y telefonos");
            }

            DaoUser daoUser = new DaoUser(c);





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
