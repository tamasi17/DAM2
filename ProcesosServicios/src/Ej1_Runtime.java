import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Ej1_Runtime {

    static void main(String[] args) {

        /// Elegimos un comando.
        String comando="notepad";

        /// Un proceso **nunca se instancia con new**.
        Process process;

        /// Para obtener un proceso usamos `process=Runtime.getRuntime().exec(comando);`.
        /// Tambien se puede usar **ProcessBuilder.start()**.

        /// Obtenemos proceso, esperamos hasta que haya terminado, terminamos (gently) el proceso.
        try {

            process = Runtime.getRuntime().exec(comando);

            process.waitFor(3, TimeUnit.SECONDS);

            process.destroy();

        } catch (IOException ieo) {
            System.err.println("Error al lanzar el comando: " + comando);
            ieo.printStackTrace();
        } catch (InterruptedException ie) {
            System.err.println("Proceso interrumpido");
            ie.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
