import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ej6_ControlEjecucion {

    /*
    Crea un programa que ejecute un comando que tarde varios segundos en finalizar, como ping con varios paquetes o timeout.
    Mientras el proceso esté vivo (isAlive()), haz que tu programa muestre cada segundo el mensaje:
    “El proceso sigue en ejecución….”
    Al finalizar, muestra el código de salida. Para esperar un segundo utiliza:   Thread.sleep(1000);
     */
    static void main() {

        // ping

        ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com","-n","10");

        processBuilder.redirectErrorStream(true);

        try{
            Process process = processBuilder.start();
            int i = 1;
            while (process.isAlive()){
                System.out.println( i + ": El proceso sigue en ejecución...");
                Thread.sleep(1000);
                i++;
            }

            boolean fin = process.waitFor(30, TimeUnit.SECONDS);
            if (!fin){
                System.err.println("El proceso no se ha cerrado, forzamos cierre.");
                process.destroyForcibly();
            } else{
                int exitCode = process.exitValue();
                System.out.println("Proceso terminado con codigo: " + exitCode);
            }

        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            Thread.currentThread().interrupt(); // marcamos interrupcion
        } catch (IOException e) {
            System.err.println("Error de E/S al ejecutar o comunicar el proceso");
        }

    }
}
