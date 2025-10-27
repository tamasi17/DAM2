package ficherosSecuenciales;

import java.io.*;

/**
 * Implemente un programa en Java que permita escribir contenido en un fichero de texto y
 * posteriormente leer dicho contenido, aplicando un manejo adecuado de main.java.excepciones.
 */

public class Ej4_EscribiendoQuijote {

    static void main() {


    File fichero = new File("ficherosSecuenciales/Quijote.txt");

    crearFichero(fichero);

    escribirQuijote(fichero);

    leerQuijote(fichero);

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

    private static void escribirQuijote(File fichero) {
        System.out.println("Escribiendo el Quijote en fichero: " + fichero.getName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write("En un lugar de la mancha");
            bw.newLine();
            bw.write("de cuyo nombre no quiero acordarme");
            bw.newLine();
            bw.write("no ha mucho tiempo que vivía un hidalgo");
            bw.newLine();
            bw.write("de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.");
        } catch (FileNotFoundException fnfe){
            System.err.println("No se encuentra el archivo donde escribir.");
        } catch (IOException ioe) {
            System.err.println("Error de E/S" + ioe.getLocalizedMessage());
            System.exit(1);
        }
        System.out.println("Texto escrito correctamente en " + fichero.getPath());
    }

    private static void leerQuijote(File fichero) {
        System.out.println("Leyendo Quijote desde fichero: " + fichero.getName());
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo" + e.getLocalizedMessage());
            System.exit(1);
            // cero si el proceso funciona correctamente. A partir de ahi, nosotros marcamos el exitCode.
        }
    }

}
