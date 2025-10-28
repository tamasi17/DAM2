package ficherosAleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ej3_PasarAMayusculas {

    /*
    Implemente un programa en Java utilizando la clase RandomAccessFile, modifique el fichero
original theraven.txt en el directorio ficheros, pasando de minúsculas a mayúsculas los
caracteres e ->E que encuentre en el texto.
     */

    static void main() {

        File file = new File("ficheros/theraven.txt");

        if (!file.exists()) {
            System.err.println("No se encuentra el fichero: " + file.getAbsolutePath());
            return;
        }

        int contadorCambios = 0;

        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){

            // Recorremos byte a byte todo el fichero
            for (long pos = 0; pos < raf.length(); pos++) {
                // raf.readChar() salta excepcion, lee dos bytes, read lee uno a uno
                int caracter = raf.read();

                // Read char avanza una posicion, volvemos a pos:
                if (caracter == 'e'){
                    raf.seek(pos);
                    raf.write('E');
                    contadorCambios++;
                }
            }

            System.out.println("Archivo procesado: " + file.getName());
            System.out.println("Total de 'e' convertidas a 'E': " + contadorCambios);

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: "+ fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error when accesing the file: "+ ioe.getLocalizedMessage());
        }

    }
}
