package ejerciciosUD1_Procesos;

import java.io.IOException;

public class Ej2_ProcessBuilder {

    static void main() {

        String[] proceso = {"ping","google.com","-n","5"}; // TERMINA DE ESCRIBIR PING

        ///  Interfaz para crear y configurar procesos
        ProcessBuilder processBuilder = new ProcessBuilder(proceso);

        ///  INHERIT
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();

            /// `waitFor()` devuelve 0 si el proceso se ejecuta correctamente.
            int codRet = process.waitFor();

            System.out.println("La ejecucion de " + proceso
            + " devuelve:\n" + codRet
            + " " + (codRet==0 ? "Ejecución correcta":"ERROR")
            );

            System.out.println("Codigo de salida: " + process.exitValue());

        } catch (IOException ioe) {
            System.err.println("Error durante la ejecucion del proceso");
            System.exit(2);
        } catch (InterruptedException ie) {
            System.err.println("Proceso interrumpido");
            System.exit(3);
        }


    }
}
