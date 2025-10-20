package ejerciciosUD1_Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej3_CapturaSalidaEstandar {

    /*
    Diseña un programa que ejecute el comando ping
    y capture todo lo que escribe en la consola.
    Utiliza el flujo getInputStream() del objeto Process
    y muestra cada línea recibida en la salida de tu programa Java, precedida por el texto STDOUT:.
     */

    /* getInputStream: Implementation note: It is a good idea for the returned input stream to be buffered.
     */
    static void main() {

        /// Se puede escribir en una sola linea ´ProcessBuilder processBuilder = new ProcessBuilder("ping", "google.com");´
        String[] comando = {"ping", "www.google.com", "-n", "5"};
        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        System.out.println("Programa: Ping a Google");


        try {
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String linea = br.readLine();
            while (linea != null) {
                if (linea.contains("Respuesta")) { // solo enseña pings con respuesta
                    System.out.println("STDOUT: " + linea);
                }
                linea = br.readLine();
            }

            int exitCode = process.waitFor();
            if (exitCode == 0){
                System.out.println("El proceso acabó correctamente (código: " + exitCode + ")");
            } else {
                System.err.println("El proceso terminó con errores (código: " + exitCode + ")");
            }

            br.close(); // necesario al no usar try with resources



        } catch (InterruptedException ie){
            System.err.println("Proceso interrumpido");
        } catch (IOException ioe) {
            System.err.println("Error de entrada");
        } catch (Exception e) {
            System.err.println("Ha habido un error");
            e.printStackTrace();
        }

    }
}
