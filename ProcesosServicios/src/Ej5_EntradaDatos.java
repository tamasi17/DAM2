import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class Ej5_EntradaDatos {
        /*
        Desarrolla un programa que ejecute el comando sort.
        Desde Java, envía varias palabras al proceso usando un BufferedWriter conectado a getOutputStream().
        Después, lee y muestra la salida ordenada usando un BufferedReader.
        Asegúrate de cerrar el flujo de entrada para que el proceso sepa que no recibirá más datos.
         */

    static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder("sort");

        /// Cuando lea process.getInputStream() mezcla stdout y stderr
        /// Esto evita bloqueos por no leer el getErrorStream()
        processBuilder.redirectErrorStream(true);


        try {
            Process process = processBuilder.start();

            /// Escribimos en la **ENTRADA** del proceso
            /// (lo que escribamos aqui es la entrada (stdin) del sort).
            /// OutputStreamWriter y BufferedWriter ayudan a escribir texto mas facilmente
            /// **IMPORTANTE**: cerrar el writer para señalar EOF; si no, sort esperará más datos.
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {

                bw.write("Pope\n");
                bw.write("Palace\n");
                bw.write("Cebri\n");
                bw.write("Mats\n");
                bw.flush(); //fuerza a mandar datos inmediatamente, close() tambien hace flush().
            } /// Writer se cierra aquí

            /// Leemos la **SALIDA** del proceso (stdout), lo que el sort ha escrito
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String linea;
                System.out.println("Lista: ");
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            /// Espera con timeout por si queda corriendo el proceso
            boolean fin = process.waitFor(5, TimeUnit.SECONDS);
            if (!fin) {
                System.err.println("Timeout esperando a sort. Se fuerza el cierre");
                process.destroyForcibly();
            } else {
                int exitCode = process.exitValue();
                System.out.println("El proceso ha terminado con codigo: " + exitCode);
            }

        } catch (IOException ioe) { // comando no encontrado o fallos al escribir/leer
            System.err.println("Error de E/S al ejecutar o comunicar con el proceso: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // volvemos a marcar la interrupción
            System.err.println("La espera del proceso fue interrumpida: " + ie.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
