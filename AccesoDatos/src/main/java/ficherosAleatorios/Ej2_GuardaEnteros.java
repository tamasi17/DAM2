package ficherosAleatorios;

import java.io.*;
import java.util.Scanner;

public class Ej2_GuardaEnteros {

    /*
    Implemente un programa (GuardaEnteros) utilizando la clase RandomAccessFile
    que requiera del usuario un numero entero, este debe añadirse a un fichero enteros.dat
    en la posición que también debe ser indicada por el usuario.

        ▪ Solo deben añadir enteros en una posición existente o en la última posición del fichero.
        Muestre el contenido del fichero antes y después de ser modificado.
        ▪ Debe controlarse con la excepción correspondiente que efectivamente el usuario
        ha añadido un numero entero por teclado.
     */


    static void main() {

        File file = new File("ficheros" + File.separator + "enteros.dat");
        try {
            if (file.createNewFile()) System.out.println("Fichero creado.");
        } catch (IOException ioe) {
            System.err.println("Fichero no se pudo crear: "+ ioe.getLocalizedMessage());
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Dame un numero entero:");
        int numElegido = sc.nextInt();
        System.out.println("Y su posicion (si no existe, al final del fichero):");
        int posicion = sc.nextInt();

        long registro = (long) Integer.BYTES * (posicion-1);

        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){

            long cantidadEnteros = raf.length() / Integer.BYTES;

            System.out.println("Fichero original:");
            mostrarFichero(raf);

            raf.seek(registro);
            raf.writeInt(numElegido);

            System.out.println("Fichero modificado:");
            mostrarFichero(raf);

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found to write: "+ fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error while writing: "+ ioe.getLocalizedMessage());
        }


        sc.close();
    }

    private static void mostrarFichero(RandomAccessFile raf) {
        try{
            int indice = 1;
            raf.seek(0);

            while (true){
                int valorLeido = raf.readInt();
                System.out.println("Numero: "+valorLeido+ ", posicion: "+ indice);
                indice++;
            }
        } catch (EOFException eofe){
            System.out.println(">> Fichero finalizado.");
        } catch (IOException ioe){
            System.err.println("I/O error while reading the file: "+ ioe.getLocalizedMessage());
        }
    }
}
