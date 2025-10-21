package ejerciciosUD1_Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Ej10_CombinacionSalidas {
    /*
    Escribe un programa en Java que ejecute un comando externo mediante ProcessBuilder.
    Configura el objeto con la opción redirectErrorStream(true)
    para que la salida de error (stderr) y la salida estándar (stdout) se mezclen en un único flujo.
     */
    static void main() {
        ProcessBuilder processBuilder = new ProcessBuilder("ping", "dominio_incorrecto");
        // Mezcla stdout + stderr para leerlo todo desde un único stream
        processBuilder.redirectErrorStream(true);

        Process proceso = null;
        try {
            proceso = processBuilder.start();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream(), StandardCharsets.UTF_8))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // Espera con timeout para evitar bloqueos si el proceso se queda colgado
            boolean terminado = proceso.waitFor(15, TimeUnit.SECONDS);

            if (!terminado) {

                //envía una señal  (SIGTERM en Linux, CTRL-BREAK en Windows). El proceso puede ignorarla
                proceso.destroy();

                // Si no termina, forzamos
                if (proceso.isAlive()) {
                    proceso.destroyForcibly();
                }
                System.err.println("El proceso 'ping' no terminó en el tiempo esperado (timeout).");

            } else {
                int exit = proceso.exitValue();
                if (exit != 0) {
                    System.err.println("El proceso terminó con código de salida " + exit +
                            " (posible host inválido o error de red).");
                }
            }

        } catch (IOException ioe) {
            // Lanzado por processBuilder.start() o problemas de E/S leyendo la salida
            System.err.println("Error de E/S al iniciar o leer el proceso: " + ioe.getMessage());
        } catch (SecurityException se) {
            // Lanzado si un SecurityManager restringe la creación/lectura del proceso
            System.err.println("Permisos insuficientes para crear/ejecutar el proceso: " + se.getMessage());
        } catch (InterruptedException ie) {
            // Si se interrumpe el hilo mientras espera al proceso
            Thread.currentThread().interrupt(); // restablece el estado de interrupción
            System.err.println("La espera del proceso fue interrumpida: " + ie.getMessage());
            if (proceso != null && proceso.isAlive()) {
                proceso.destroyForcibly();
            }
        }


    }
}
