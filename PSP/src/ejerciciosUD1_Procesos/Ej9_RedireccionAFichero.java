package ejerciciosUD1_Procesos;

import java.io.File;
import java.io.IOException;

public class Ej9_RedireccionAFichero {

    /*
    Desarrolla un programa en Java que ejecute un comando que genere bastante salida,
    como ipconfig (Windows) o ls -l (Linux),
    y redirige su salida estándar directamente a un fichero de texto usando redirectOutput(File).
    Comprueba que el fichero contiene la información esperada.
     */
    static void main() {

        try{
            ProcessBuilder processBuilder = new ProcessBuilder("ipconfig");
            File fichero = new File("ficheros/ej9_RedireccionAFichero.txt");
            processBuilder.redirectOutput(fichero);
            Process process = processBuilder.start();

        } catch (IOException ioe){
            System.err.println("Error de E/S, comando no existe o la comunicacion con el proceso ha fallado");
        }

    }

}
