package ejerciciosUD1_Procesos;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Ej13_EncadenadosSolucion {


    // Tiempo máximo de espera por cada proceso
    private static final long TIMEOUT_MS = 5000;

    public static void main(String[] args) {

        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);

        // process1: genera tres líneas
        ProcessBuilder processBuilder1 = os.contains("win")
                ? new ProcessBuilder("cmd", "/c", "echo Java && echo Procesos && echo Builder")
                : new ProcessBuilder("bash", "-lc", "echo Java; echo Procesos; echo Builder");

        // process2: sort recibe por stdin y emite ordenado por stdout
        ProcessBuilder processBuilder2 = new ProcessBuilder("sort");

        // Unificamos stderr en stdout para no tener que leer dos streams y evitar deadlocks
        processBuilder1.redirectErrorStream(true);
        processBuilder2.redirectErrorStream(true);

        Process process1;
        Process process2;

        try {
            process1 = processBuilder1.start();
            process2 = processBuilder2.start();
        } catch (IOException ioe) {
            System.err.println("No se pudieron iniciar los procesos: " + ioe.getMessage());
            return;
        }

        // Encadenar: salida de process1 -> entrada de process2
        try (InputStream in = process1.getInputStream();
             OutputStream out = process2.getOutputStream()) {
            in.transferTo(out);      // copia todo lo que produzca process1
            //Equivalente a:
            /*byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }*/
        } catch (IOException ioe) {
            System.err.println("Error encadenando procesos: " + ioe.getMessage());
            destroyQuiet(process1);
            destroyQuiet(process2);
            return;
        }

        // Elegimos charset de lectura:
        // - sort en linux suele ser UTF-8
        // - en Windows conviene el charset por defecto de la consola
        Charset cs = os.contains("win") ? Charset.defaultCharset() : StandardCharsets.UTF_8;

        // Leer salida final de sort
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream(), cs))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Final: " + linea);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo la salida de sort: " + e.getMessage());
            destroyQuiet(process1);
            destroyQuiet(process2);
            return;
        }

        // Esperar con timeout y limpiar si se quedan colgados
        awaitOrKill(process1, "generador");
        awaitOrKill(process2, "sort");
    }

    private static boolean awaitOrKill(Process process, String nombre) {
        try {
            boolean fin = process.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (!fin) {
                System.err.println("Timeout esperando a " + nombre + ". Se fuerza el cierre.");
                process.destroy();
                if (!process.waitFor(500, TimeUnit.MILLISECONDS)) process.destroyForcibly();
            } else if (process.exitValue() != 0) {
                System.err.println(nombre + " terminó con código: " + process.exitValue());
            }
            return true;
        } catch (InterruptedException ie) {
            System.err.println("Interrumpido esperando a " + nombre + ".");
            Thread.currentThread().interrupt();
            destroyQuiet(process);
            return false;
        }
    }

    private static void destroyQuiet(Process process) {
        if (process != null && process.isAlive()) {
            try {
                process.destroy();
            } catch (Exception e) {
            }
            try {
                if (!process.waitFor(200, TimeUnit.MILLISECONDS)) process.destroyForcibly();
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                try {
                    process.destroyForcibly();
                } catch (Exception e2) {
                }
            }
        }
    }
}




