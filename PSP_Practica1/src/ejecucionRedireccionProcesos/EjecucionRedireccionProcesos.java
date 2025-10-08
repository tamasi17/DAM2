package ejecucionRedireccionProcesos;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class EjecucionRedireccionProcesos {

 /*
    Utilizando la clase ProcessBuilder ejecute el siguiente fichero.bat
    utilizando los mecanismos de redirección que proporciona la case
    redirecciona la salida de estándar y la salida de error a los ficheros salida y error (.txt)
 */

    public static void main(String[] args) {

        File fichero = new File("fichero.bat");
        File salida = new File("salida.txt");
        File error = new File("error.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {

            System.out.println("Generando fichero .bat ...");

            fichero.createNewFile(); // creates file only if it does not exist yet

            // Writes fichero.bat
            bw.write("mkdir nuevo\n");
            bw.write("cd nuevo\n");
            bw.write("echo fichero creado > mifichero.txt\n");
            bw.write("dir\n");
            bw.write("CDDD\n");
            bw.write("echo fin comandos\n");

        } catch (IOException ioe) {
            System.err.println("Error en la creacion del fichero");
        }


        ProcessBuilder pb = new ProcessBuilder("fichero.bat");
        Process process = null;

        try {
            process = pb.start();
            salida.createNewFile();
            String textoSalida;

            // Leemos stdout del proceso
            try (BufferedReader bis = new BufferedReader
                    (new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {

                System.out.println("Leyendo salida del proceso...");
                textoSalida = bis.readAllAsString();
            }

            // Escribimos en el fichero salida.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {
                System.out.println("Escribiendo en salida.txt.");
                bw.write(textoSalida);
            }

            error.createNewFile();
            String textoError;

            // Leemos stderr del proceso
            try (BufferedReader bis = new BufferedReader
                    (new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
                System.out.println("Leyendo errores del proceso...");
                textoError = bis.readAllAsString();
            }

            // Escribimos en el fichero error.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(error))) {
                System.out.println("Escribiendo en error.txt.");
                bw.write(textoError);
            }

        } catch (IOException ioe) {
            System.err.println("Error en E/S en la ejecucion o comunicacion con el proceso");
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error");
        }
    }

}
