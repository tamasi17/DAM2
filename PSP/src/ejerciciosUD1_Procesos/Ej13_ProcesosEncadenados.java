package ejerciciosUD1_Procesos;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Ej13_ProcesosEncadenados {

    private static final long timeoutMS = 3000;

    static void main() {

        ProcessBuilder pb1 = new ProcessBuilder("cmd", "/c", "echo Java && echo Procesos && echo Builder");
        ProcessBuilder pb2 = new ProcessBuilder("sort");

        pb1.redirectErrorStream(true);
        pb2.redirectErrorStream(true);

        Process process1 = null;
        Process process2 = null;

        try {
            process1 = pb1.start();
            process2 = pb2.start();
        } catch (IOException ioe) {
            System.err.println("I/O error: " + ioe.getLocalizedMessage());
        }


        try (InputStream in = process1.getInputStream(); OutputStream out = process2.getOutputStream()) {

            in.transferTo(out);

        } catch (IOException ioe) {
            System.err.println("I/O error in transfer from p1 to p2: " + ioe.getLocalizedMessage());
        }

        try {
            boolean fin = process1.waitFor(timeoutMS, TimeUnit.MILLISECONDS);
            if (!fin) {
                process1.destroy();
            }

        } catch (InterruptedException ie) {
            System.err.println("Process 1 interrupted: " + ie.getLocalizedMessage());
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process2.getInputStream()))) {
            String linea = "";
            while ((linea = br.readLine()) != null) {
                System.out.println("Final: " + linea);
            }

        } catch (IOException ioe) {
            System.err.println("I/O error reading p2 output: " + ioe.getLocalizedMessage());
        }


    }


}
