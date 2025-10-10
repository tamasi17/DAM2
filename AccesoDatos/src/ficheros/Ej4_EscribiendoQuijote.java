package ficheros;

import java.io.File;
import java.io.IOException;

/**
 * Implemente un programa en Java que permita escribir contenido en un fichero de texto y
 * posteriormente leer dicho contenido, aplicando un manejo adecuado de excepciones.
 */

public class Ej4_EscribiendoQuijote {

    static void main() {


    File fichero = new File("/DAM2/ficheros/Quijote.txt");

    crearFichero(fichero);

    escribirQuijote(fichero);

    leerQuijote(fichero);

    /*
    En un lugar de la mancha
    de cuyo nombre no quiero acordarme,
    no ha mucho tiempo que vivía un hidalgo
    de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.
     */


    // escritura fichero

    // lectura fichero

    // manejo excepciones

    // estructura con escribirTexto() y leerTexto()

    }

    private static void crearFichero(File fichero) {
        System.out.println("Creando fichero...");
        fichero.getParentFile().mkdirs();

        try {
            fichero.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error al crear el fichero.");
            System.err.println(ioe.getLocalizedMessage());
        }
    }

}
