package ficherosSecuenciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ej6_ContarPalabras {

    /*
    Desarrolla un programa en Java que lea un fichero de texto y cuente cuántas palabras contiene en total.
    En este caso, se considerará que las palabras están separadas únicamente por uno o más espacios en blanco.

    El fichero se llamará palabras.txt y estará ubicado en la carpeta ficheros.

    Cada línea debe dividirse en palabras utilizando el método split(" +"),
    que separa el contenido por uno o más espacios consecutivos.

     */

    private static final String RUTA = "ficherosSecuenciales/palabras.txt";

    static void main() {

        File file = new File(RUTA);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Directorios creados");
            } else {
                System.out.println("No se pudieron crear los directorios");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println("Documento creado: " + file.getPath());
            }
        } catch (IOException ioe) {
            System.err.println("No se ha creado el documento: " + ioe.getLocalizedMessage());
        }

        int totalPalabras = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Trim para eliminar espacios iniciales y finales
                // Trim devuelve un string, actualizamos linea!!
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    // Dividir por uno o más espacios
                    String[] palabras = linea.split(" +");
                    totalPalabras += palabras.length;
                }
            }

            System.out.println("Total de palabras: " + totalPalabras);
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo");
        }


    }

}
