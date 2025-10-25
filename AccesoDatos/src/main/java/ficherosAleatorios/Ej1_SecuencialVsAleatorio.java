package main.java.ficherosAleatorios;

import java.io.*;

public class Ej1_SecuencialVsAleatorio {

    public static void main(String[] args) {

    String ruta = "ficherosAleatorios" + File.separator + "datos.bin";
    File fichero = new File(ruta);

    try(RandomAccessFile raf = new RandomAccessFile(fichero, "rw")){
        int contador=1;
        for (int i = 1; i < 10000000; i++) {
            raf.write(contador);

        }

    } catch (IOException e) {
        System.out.println(e.getMessage());
    }





    }
}
