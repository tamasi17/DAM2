package ejerciciosUD1_Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Ej12_EjecutorComandos {
    /*
    Escribe un programa en Java que lea desde teclado un comando y lo ejecute usando la clase ProcessBuilder.
    El programa debe mostrar tanto la salida estándar (stdout)
    como la salida de error (stderr) directamente en la consola de Java.
     */
    static void main() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un comando, por favor. Si quieres terminar escribe \"salir\".");
        String comando = sc.nextLine().trim();
        while (!comando.equals("salir")) {

            // comando.split(" ") para dividir con espacios los comandos
            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            pb.redirectErrorStream(true);

            Process process = null;

            try {
                process = pb.start();
                try (BufferedReader br = new BufferedReader
                        (new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }

                    boolean terminado = process.waitFor(3, TimeUnit.SECONDS);

                    if (!terminado) {
                        System.out.println("Han pasado mas de 3 segundos, matamos el proceso.");
                        process.destroyForcibly();
                        process.waitFor();
                    } else {
                        int exit = process.exitValue();
                        System.out.println("El proceso ha terminado con codigo: " + exit);
                        process.destroy();
                    }

                }

            } catch (InterruptedException ie) {
                System.err.println("El proceso fue interrumpido");
                Thread.currentThread().interrupt();

            } catch (IOException ioe) {
                System.err.println("Error de E/S");
            }
            System.out.println("Escribe otro comando o \"salir\":");
            comando = sc.nextLine();
        } // se ha tecleado "salir"

        System.out.println("Gracias por usar esta consola.");
    }
}
