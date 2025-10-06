package ejerciciosUnidad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

        ProcessBuilder pb = new ProcessBuilder("ping", "google.com", "-n", "10");
        pb.redirectErrorStream(true);
       try{
        Process process=pb.start();
        int aciertos=0;
        int fallos=0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))){
            String linea;
            while ((linea = br.readLine()) != null){
                System.out.println(linea);
                if (linea.contains("Respuesta")) {
                    aciertos++;
                } else if(linea.contains("solicitud") | linea.contains("Error")) fallos++; // La solicitud de ping no pudo encontrar...
            }
       }

           System.out.println("Aciertos: " + aciertos + "\nFallos: " + fallos);

        } catch (IOException e) {
            System.err.println("Error de E/S");
        }

    }

}
