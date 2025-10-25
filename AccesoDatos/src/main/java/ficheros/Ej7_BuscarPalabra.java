package main.java.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ej7_BuscarPalabra {
    /**
     * Implementa un programa en Java que reciba por línea de comandos el nombre de un fichero de texto y una palabra.
     * El programa debe leer el fichero y comprobar si esa palabra aparece en él.
     * Debe mostrar un mensaje indicando si la palabra se ha encontrado o no.
     * java BuscarPalabra main.java.ficheros/documento.txt gato
     */
    static void main() {

        Scanner sc = new Scanner(System.in);

        File fichero = comprobarFichero(sc);
        String palabraBuscada = comprobarPalabra(sc);
        buscaPalabra(fichero, palabraBuscada);
        // DEVUELVE LA LINEA EQUIVOCADA

        sc.close();

    }

    private static String comprobarPalabra(Scanner sc) {
        System.out.println("Qué palabra buscamos?");
        String palabraBuscada = sc.next();
        return palabraBuscada;
    }

    private static File comprobarFichero(Scanner sc){

        System.out.println("En qué fichero buscamos la palabra?");
        String nombreFichero = sc.nextLine().trim();
        File fichero = new File("main.java.ficheros\\" + nombreFichero);

        while (!fichero.exists()) {
            System.out.println("No se ha encontrado el fichero. Escribe otra vez su nombre: ");
            nombreFichero = sc.nextLine();
            fichero = new File("/DAM2/main.java.ficheros/" + nombreFichero);
        }
        return fichero;
    }

    private static void buscaPalabra(File fichero, String palabraBuscada) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            System.out.println("Buscando " + palabraBuscada + "...");
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null && !encontrado) {
                if (linea.contains(palabraBuscada)) {
                    encontrado = true;
                    System.out.println("Se ha encontrado la palabra en el siguiente verso:\n" + linea);
                }
            }

            if ((linea = br.readLine()) != null && !encontrado) {
                System.out.println("No se ha encontrado la palabra " + palabraBuscada);
            }

        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
    }
}
