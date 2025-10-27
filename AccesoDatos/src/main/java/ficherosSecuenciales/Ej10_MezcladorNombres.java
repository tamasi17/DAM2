package ficherosSecuenciales;

import java.io.*;

public class Ej10_MezcladorNombres {


    /*
    Escribe un programa que lea dos ficheros, uno con nombres (ficheros/nombres.txt) y otro
con apellidos (ficheros/apellidos.txt), y cree un tercer fichero mezclando sus líneas
alternativamente; si uno de los ficheros es más largo, agrega al final el resto de líneas del otro.
apellidos.txt
     */

    static void main() {

        File fNombres = new File("ficherosSecuenciales/nombres.txt");
        File fApellidos = new File("ficherosSecuenciales/apellidos.txt");
        File mezclados = new File("ficherosSecuenciales/nombresMezclados.txt");

        if (!fNombres.exists() || !fApellidos.exists()) return;

        try {
            if (mezclados.createNewFile()) {
                System.out.println("Doc nombresMezclados creado correctamente.");
            }
        } catch (IOException ioe) {
            System.err.println("El documento nombresMezclados no se pudo crear: " + ioe.getLocalizedMessage());
        }


        try (BufferedReader readerNombres = new BufferedReader(new FileReader(fNombres));
             BufferedReader readerApellidos = new BufferedReader(new FileReader(fApellidos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(mezclados))) {

            String nombre="";
            String apellido = "";

            while (true) {

                nombre = readerNombres.readLine();
                apellido = readerApellidos.readLine();

                // Para si ambos son null (end of file de ambos)
                if (nombre == null && apellido == null) {
                    break;
                }

                // Si una sola es null
                if (nombre == null) nombre = "";
                if (apellido == null) apellido = "";

                nombre = nombre.trim();
                apellido = apellido.trim();

                String nombreEntero = (nombre + " " + apellido).trim();

                if (!nombreEntero.isBlank()) {
                    bw.write(nombreEntero);
                    bw.newLine();
                }

            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("No se encontró el archivo");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al leer o escribir archivos");
        }
    }
}
