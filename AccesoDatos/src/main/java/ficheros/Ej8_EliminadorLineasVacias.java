package ficheros;

import java.io.*;

/**
 * Escribe un programa que lea un fichero de texto y genere otro fichero
 * en el que se omitan todas las líneas que estén vacías o contengan sólo espacios.
 * Líneas vacías eliminadas en main.java.ficheros/comentarios_sinlineas.txt
 */
public class Ej8_EliminadorLineasVacias {

    static void main() {

        String sinLineasVacias = leerComentarios();

        File comentariosSinLineas = crearFichero();

        escribirComentarios(comentariosSinLineas, sinLineasVacias);

        leerFichero(comentariosSinLineas);

    }

    private static void escribirComentarios(File comentariosSinLineas, String sinLineasVacias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(comentariosSinLineas))) {

            bw.write(sinLineasVacias);

        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
    }

    private static File crearFichero() {
        File comentariosSinLineas = new File("ficheros/comentariosSinLineas.txt");

        try {
            if (comentariosSinLineas.createNewFile()){
                System.out.println("Creando nuevo documento sin lineas vacias...");
            }
        } catch (IOException e) {
            System.err.println("Error de E/S");
        }
        return comentariosSinLineas;
    }

    private static String leerComentarios() {
        File comentarios = new File("ficheros/comentarios.txt");
        String sinLineasVacias = "";

        try (BufferedReader br = new BufferedReader(new FileReader(comentarios))) {
            String linea;
            while (((linea = br.readLine()) != null)) {
                if (!linea.isBlank()) {
                    sinLineasVacias += linea + "\n";
                }
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found");
        } catch (IOException ioe) {
            System.err.println("Error de E/S");
        }
        return sinLineasVacias;
    }


    private static void leerFichero(File fichero) {
        System.out.println("Leyendo desde fichero: " + fichero.getName());
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
