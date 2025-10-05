import java.io.IOException;
import java.util.Scanner;

public class Ej10_CombinacionSalidas {
    /*
    Escribe un programa en Java que ejecute un comando externo mediante ProcessBuilder.
    Configura el objeto con la opción redirectErrorStream(true)
    para que la salida de error (stderr) y la salida estándar (stdout) se mezclen en un único flujo.
     */
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un comando, por favor:");
        String comando = sc.nextLine().trim();
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true);

        try{
            Process process = pb.start();
            int exit = process.waitFor();
            System.out.println("Proceso terminado, codigo: " + exit);

        } catch (InterruptedException ie){
            System.err.println("El hilo fue interrumpido.");
            Thread.currentThread().interrupt();

        } catch (IOException ioe){
            System.err.println("El comando no se pudo ejecutar.");
        }


    }
}
