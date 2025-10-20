package ejerciciosUD1_Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Ej8_KillProcess {

    static void main() {

        ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com", "-n", "30");
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            // Esperamos 5 segundos a que el proceso termine.
            boolean terminado = process.waitFor(5, TimeUnit.SECONDS);

            // Si no ha terminado, lo matamos
            if (!terminado){
                System.out.println("Han pasado 5 segundos.\nMatamos proceso.");
                process.destroyForcibly();
                process.waitFor();

            } else{ // si ha terminado, mostramos su stdout y su código por pantalla.
                try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                    String linea;
                    while ((linea=br.readLine()) != null){
                        System.out.println(linea);
                    }
                    int exit = process.exitValue();
                    System.out.println("Proceso terminado con codigo: " + exit);
                }
            }
        } catch (InterruptedException ie) {
            System.err.println("El proceso ha sido interrumpido");
            Thread.currentThread().interrupt(); //flag
        } catch (
                IOException ioe) {
            System.err.println("Error de E/S en la ejecucion o comunicacion del proceso");
            ioe.printStackTrace();
        }

    }
}
