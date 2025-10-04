import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Ej4_CapturaErrores {

    /*
    Escribe un programa en Java que intente ejecutar un comando inexistente
    (por ejemplo, comando_que_no_existe).
    Captura lo que escriba en su salida de error (stderr) mediante getErrorStream()
    y muéstralo en pantalla con el prefijo ERROR:.
     */

    static void main() {

        String comando = "deleteSystem32plz";
        ProcessBuilder processBuilder = new ProcessBuilder(comando);
        processBuilder.inheritIO();
        System.out.println("Programa: Delete system32");

        try{
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            process.waitFor(5, TimeUnit.SECONDS);
            System.err.println("ERROR: " + br.readLine());
            br.close();
        } catch (InterruptedException ie){
            System.err.println("Proceso interrumpido");
            //ie.printStackTrace();
        } catch (IOException ioe){
            System.err.println("Error al ejecutar el comando: " + comando);
            //ioe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ha habido un error");
            //e.printStackTrace();
        }
    }
}
