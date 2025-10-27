package main.java.ficherosAleatorios;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Ej1_SecuencialVsAleatorio {

    public static void main(String[] args) {

        String ruta = "ficheros" + File.separator + "datos.bin";
        File fichero = new File(ruta);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))) {
            for (int i = 1; i < 10000000; i++) {
                dos.write(i);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Instant before = Instant.now();

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            boolean encontrado = false;
            while (!encontrado) {
                int num = dis.readInt();
                if (num == 5000000)
                    System.out.println("Number found sequentially: "+ num);
                    encontrado = true;
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error reading sequentially");
        }

        Instant after = Instant.now();

        Duration duracion = Duration.between(before, after);
        System.out.println("Sequentially took: " + duracion.getNano());


        before = Instant.now();

        long pos = Integer.BYTES*5000000;
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")){

            if (pos<raf.length()){
                raf.seek(pos);
                int num = raf.readInt();
                System.out.println("Random access found: "+ num);
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("Raf file not found for reading: "+ fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error trying to read from raf: "+ ioe.getLocalizedMessage());
        }

        after = Instant.now();

        duracion = Duration.between(before, after);
        System.out.println("Random access duration: " + duracion.getNano());

    }
}
