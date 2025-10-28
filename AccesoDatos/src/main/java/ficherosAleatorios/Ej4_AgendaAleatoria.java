package ficherosAleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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

    static void main() {

        String nombre;
        long telefono;

        File file = new File("ficheros/agenda.dat");


        //      FALTAN OFFSETS DE CADA ENTRADA DEL REGISTRO, NO PUEDE LEER !!!!!



        try {
            if (file.createNewFile()) {
                System.out.println("Agenda creada correctamente");
            }
        } catch (IOException ioe) {
            System.err.println("No se pudo crear la agenda: " + ioe.getLocalizedMessage());
        }

        try (RandomAccessFile raf = new RandomAccessFile(file,"rw")){

            writeContact(raf, "Peter", 484876523);
            writeContact(raf, "Mary Jane", 877636524);
            writeContact(raf, "Miles", 632636119);
            System.out.println("Contactos añadidos correctamente");

            readAllContacts(raf);


        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: "+ fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error when handling rf file: "+ ioe.getLocalizedMessage());
        }


    }

    private static void readAllContacts(RandomAccessFile raf) throws IOException {
        long telefono;
        long pos = 0;
        raf.seek(pos);
        long registro = 52;

        while (pos< raf.length()) {
            id = raf.readInt();

            char[] nombreChars = new char[20];
            for (int i = 0; i < 20; i++) {
                nombreChars[i]= raf.readChar();
            }

            telefono = raf.readLong();

            System.out.println(id +": "+ nombreChars.toString() + ", "+ telefono);
            pos+=registro;
        }
    }

    private static void writeContact(RandomAccessFile raf, String nombre, long telefono) {

        try {
            //Writing at the end of the file
            raf.seek(raf.length());

            // int: 4 bytes
            id++;
            raf.writeInt(id);

            // char[]: 40 bytes
            StringBuilder sb = new StringBuilder(nombre);
            if (sb.length() < 20) sb.append(' ');
            if (sb.length() > 20) sb.setLength(20);

            raf.writeChars(sb.toString());

            // long: 8 bytes
            raf.writeLong(telefono);

            // Total registro: 52 bytes

            System.out.println(id + " - Contact added: " + nombre);
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error when accessing raf: " + ioe.getLocalizedMessage());
        }
    }

}
