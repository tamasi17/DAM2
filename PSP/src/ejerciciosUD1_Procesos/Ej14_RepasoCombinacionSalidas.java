package ejerciciosUD1_Procesos;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Ej14_RepasoCombinacionSalidas {

    static final long TIMEOUT_MS = 3000;

    static void main() {

        ProcessBuilder pbErrorStream = new ProcessBuilder("comando erroneo");
        pbErrorStream.redirectErrorStream(true);

        ProcessBuilder pbInherited = new ProcessBuilder("ping", "google.com");
        pbInherited.inheritIO();

        // PB.Redirect.appendTo(File) no permite modificar el charset !!!!
        ProcessBuilder pbRedirectAppend = new ProcessBuilder("ping", "g");
        pbRedirectAppend.redirectErrorStream(true);
        pbRedirectAppend.redirectOutput(
                ProcessBuilder.Redirect.appendTo(new File("ficheros/appendedFile.txt")));

        Process processError = null;
        Process processInherited = null;
        Process processAppended = null;

        try {
            processAppended = pbRedirectAppend.start();
            processInherited = pbInherited.start();

        } catch (IOException ioe) {
            System.err.println("I/O error executing the inherited and appended processes.");
        }

        try {
            processError = pbErrorStream.start();
        } catch (IOException ioe) {
            System.err.println("I/O error running the Error Process");
        }

        try {
            boolean finInherited = processInherited.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            boolean finAppended = processAppended.waitFor(TIMEOUT_MS, TimeUnit.MILLISECONDS);

            if (!finInherited) processInherited.destroy();
            if (!finAppended) processInherited.destroy();

        } catch (InterruptedException ie) {
            System.err.println("Process interrupted" + ie.getLocalizedMessage());
        }


    }

}
