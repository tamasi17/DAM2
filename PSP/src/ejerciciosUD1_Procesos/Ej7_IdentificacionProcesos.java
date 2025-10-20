package ejerciciosUD1_Procesos;

import java.io.IOException;

public class Ej7_IdentificacionProcesos {

    /*
    Haz un programa que lance un proceso y obtenga su identificador (PID) con el método pid().
    Muestra el PID en consola.
    Indica al alumno cómo comprobar en el SO si coincide con el que aparece
    en el Administrador de tareas (Windows) o en el comando ps (Linux).
     */
    static void main() {

        ProcessBuilder processBuilder = new ProcessBuilder("notepad");
        try{
        Process process = processBuilder.start();
        System.out.println("Puedes confirmar el PID de este proceso abriendo la Terminal y buscando:\n"
        + "ps -Id " + process.pid());

        } catch (IOException e) {
            System.err.println("Error de E/S al ejecutar o comunicar el proceso");
        }


    }
}
