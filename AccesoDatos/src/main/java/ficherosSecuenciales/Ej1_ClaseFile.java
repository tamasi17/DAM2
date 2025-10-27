package ficherosSecuenciales;

import java.io.File;
import java.io.IOException;

public class Ej1_ClaseFile {

    /*
    Se trabajará con el FicheroEjemplo.txt dentro de una carpeta llamada main.java.ficheros.
    Verificar si el fichero ya existe:
        - Si no existe, deberá crearlo.
        - En caso de error al crear el fichero, deberá mostrar un mensaje de error adecuado.
    Mostrar información básica sobre el fichero:
        - Nombre del fichero.
        - Ruta relativa y absoluta.
    Comprobar si la ruta corresponde a un fichero o a un directorio.
    Mostrar el tamaño del fichero en bytes y la fecha de su última modificación.
    Mostrar los permisos actuales del fichero (lectura, escritura y ejecución).
     */
    static void main() {

        File fichero = new File("ficherosSecuenciales/FicheroEjemplo.txt");

        crearFichero(fichero);
        getInfo(fichero);

    }



    private static void getInfo(File fichero) {
        System.out.println("Nombre: " + fichero.getName()
                + "\nRuta relativa del fichero: " + fichero.getPath()
                + "\nRuta absoluta del fichero: " + fichero.getAbsolutePath());

        System.out.println(fichero.isFile() ? "Es un fichero" : "Es un directorio");

        System.out.println("Tamaño: " + fichero.length());

        System.out.print(fichero.canRead() ? "r" : "-");
        System.out.print(fichero.canWrite() ? "w" : "-");
        System.out.print(fichero.canExecute() ? "x" : "-");
    }

    private static void crearFichero(File fichero) {

        File dirPadre = fichero.getParentFile();

        if (dirPadre != null && !dirPadre.exists()){
            if (dirPadre.mkdirs()){
                System.out.println("Directorios creados correctamente");
            } else {
                System.out.println("No se pudieron crear los directorios padre.");
            }
        }

        try {

            if (fichero.createNewFile()) {
                System.out.println("Archivo creado correctamente");
            } else {
                System.out.println("El archivo ya existía");
            }
        } catch (IOException ioe) {
            System.out.println("Error al crear el fichero.");
            System.err.println(ioe.getLocalizedMessage());
        }
    }
}
