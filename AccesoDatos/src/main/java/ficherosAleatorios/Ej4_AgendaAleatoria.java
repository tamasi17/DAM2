package ficherosAleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ej4_AgendaAleatoria {

    /*
    Implemente un programa AgendaAleatoria.java que gestione un fichero agenda.dat con registros de tamaño fijo.
    Cada registro almacenará:
        • int id (4 bytes)
        • char[20] nombre
    (40 bytes, codificado como 20 caracteres fijos en UTF-16 BE → 2 bytes por char)
        • long telefono (8 bytes)
    En total: 52 bytes por registro.
     */

    static int id = 0;
    final static int NOMBRE_LEN = 20;
    final static int OFFSET_ID = 0;
    final static int OFFSET_NOMBRE = OFFSET_ID + 4;
    final static int OFFSET_TELEFONO = OFFSET_NOMBRE + (NOMBRE_LEN * 2);
    final static int BYTES_REGISTRO = OFFSET_TELEFONO + 8;

    private static class Contacto {
        int id;
        String nombre;
        long telefono;

        public Contacto(int id, String nombre, long telefono) {
            this.id = id;
            this.nombre = nombre;
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return id + ": " + nombre + ", " + telefono;
        }
    }


    static void main() {

        try (Scanner sc = new Scanner(System.in)) {


        }

        File file = new File("ficheros/agenda.dat");
        try {
            if (file.createNewFile()) {
                System.out.println("Agenda creada correctamente");
            }
        } catch (IOException ioe) {
            System.err.println("No se pudo crear la agenda: " + ioe.getLocalizedMessage());
        }


        //      FALTAN OFFSETS DE CADA ENTRADA DEL REGISTRO, NO PUEDE LEER !!!!!


        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

            writeContact(raf, "Peter", 484876523);
            writeContact(raf, "Mary Jane", 877636524);
            writeContact(raf, "Miles", 632636119);
            System.out.println("Contactos añadidos correctamente");

            readAllContacts(raf);


        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error when handling rf file: " + ioe.getLocalizedMessage());
        }


    }

    private static Contacto readContact(RandomAccessFile raf, int indice) throws IOException {
        long total = raf.length() / BYTES_REGISTRO;
        if (!indiceValido(total, indice)) return null;

        raf.seek(posicionRegistro(indice) + OFFSET_ID);
        int id = raf.readInt();

        StringBuilder nombre = new StringBuilder(NOMBRE_LEN);
        for (int i = 0; i < NOMBRE_LEN; i++) {
            char c = raf.readChar();
            if (c != 0) nombre.append(c);
        }

        long telefono = raf.readLong();

        return new Contacto(id, nombre.toString().trim(), telefono);
    }
}

private static boolean indiceValido(long total, int indice) {
    return indice >= 0 && indice < total;
}

private static long posicionRegistro(int indice) {
    return (long) indice * BYTES_REGISTRO;
}

private static void writeContact(RandomAccessFile raf, int indice, Contacto c) throws IOException {

    try {
        //Writing at the chosen record
        long pos = (long) indice * BYTES_REGISTRO;
        raf.seek(pos + OFFSET_ID);


        // int: 4 bytes
        raf.writeInt(c.id);

        // char[]: 40 bytes
        StringBuilder sb = new StringBuilder(c.nombre != null ? c.nombre : "");
        while (sb.length() < NOMBRE_LEN) sb.append(' ');
        if (sb.length() > NOMBRE_LEN) sb.setLength(NOMBRE_LEN);
        for (int i = 0; i < NOMBRE_LEN; i++) {
            raf.writeChar(sb.charAt(i));
        }

        // long: 8 bytes
        raf.writeLong(c.telefono);

        // Total registro: 52 bytes

        System.out.println(id + " - Contact added: " + c.nombre);
    } catch (FileNotFoundException fnfe) {
        System.err.println("File not found: " + fnfe.getLocalizedMessage());
    } catch (IOException ioe) {
        System.err.println("I/O error when accessing raf: " + ioe.getLocalizedMessage());
    }
}

}
