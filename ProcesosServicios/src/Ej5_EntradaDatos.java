import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Ej5_EntradaDatos {
        /*
        Desarrolla un programa que ejecute el comando sort.
        Desde Java, envía varias palabras al proceso usando un BufferedWriter conectado a getOutputStream().
        Después, lee y muestra la salida ordenada usando un BufferedReader.
        Asegúrate de cerrar el flujo de entrada para que el proceso sepa que no recibirá más datos.
         */

    static void main(String[] args) {

        String proceso = "sort";
        ProcessBuilder processBuilder = new ProcessBuilder(proceso);

        /// Mezcla stdout y stderr para evitar bloqueos por no leer el error
        processBuilder.redirectErrorStream(true);

        processBuilder.inheritIO();

        ///  Lista de palabras

        ArrayList<String> lista = new ArrayList<>(Arrays.asList("Pope", "Palace", "Cebri", "Mats"));

        try(BufferedWriter bw = new BufferedWriter()){

        }

        /// try con resources a partir de aqui

        ///  BufferedWriter conectado a getOutputStream()

        ///  BufferedReader


    }
}
