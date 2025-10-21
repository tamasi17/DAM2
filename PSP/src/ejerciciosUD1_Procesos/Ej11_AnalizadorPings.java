package ejerciciosUD1_Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Ej11_AnalizadorPings {
    /*
    Tu programa debe capturar la salida estándar (stdout) del proceso línea a línea.
    Cada vez que una línea indique que el paquete ha sido recibido correctamente
    (por ejemplo, contiene ttl o bytes), suma un acierto.
    Si la línea refleja un fallo
    (por ejemplo, contiene tiempo de espera agotado, timeout o host unreachable), suma un fallo.
    Al finalizar el proceso, muestra en pantalla un resumen sobre respuestas recibidas y fallos:
     */
    static void main() {

        // Detectamos SO y preparamos comando
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        ProcessBuilder pb = os.contains("win")
                ? new ProcessBuilder("ping", "google.com", "-n", "5")   // Windows
                : new ProcessBuilder("ping", "-c", "5", "google.com");  // Linux/Mac

        pb.redirectErrorStream(true);

        try {
            Process process = pb.start();

            int aciertos = 0;
            int fallos = 0;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    if (linea.contains("Respuesta")) {
                        aciertos++;
                    } else if (linea.contains("solicitud") | linea.contains("Error"))
                        fallos++; // La solicitud de ping no pudo encontrar...
                }
            }

            // Esperamos a que el proceso termine y reportamos
            int exitCode;
            try {
                exitCode = process.waitFor();
            } catch (InterruptedException ie) {
                System.err.println("Interrumpido mientras se esperaba al proceso 'ping'.");
                Thread.currentThread().interrupt();              // restaurar la marca de interrupción
                if (process.isAlive()) process.destroyForcibly(); // limpieza
                return;
            }


            System.out.println("Aciertos: " + aciertos + "\nFallos: " + fallos);

        } catch (IOException e) {
            System.err.println("Error de E/S");
        }

    }

}
