package ejerciciosUD1_Procesos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class Ej15_RepasoRedireccionFichero {

    static final long TIMEOUT_MS = 3000;

    static void main() {

        ProcessBuilder pb1 = new ProcessBuilder("ipconfig");
        ProcessBuilder pb2 = new ProcessBuilder("ipconfig");
        pb1.redirectErrorStream(true);
        pb2.redirectErrorStream(true);
        pb2.redirectOutput(new File("ficheros/appendedFile.txt"));

        Process process1 = null;
        Process process2 = null;


        try{

            process1 = pb1.start();
            process2 = pb2.start();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(process1.getInputStream(), StandardCharsets.UTF_8));
                 BufferedWriter bw = new BufferedWriter(
                         new OutputStreamWriter(new FileOutputStream("ficheros/salidaManual.txt"), StandardCharsets.UTF_8))){

                String linea="";
                while ((linea = br.readLine()) != null){
                    bw.write(linea);
                    bw.newLine();
                }
            }


            try{
                boolean fin1 = process1.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS);
                boolean fin2 = process1.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                System.err.println("Process interrupted");
            }

        } catch (IOException ioe) {
            System.err.println("I/O error executing command");
        }


    }

}
