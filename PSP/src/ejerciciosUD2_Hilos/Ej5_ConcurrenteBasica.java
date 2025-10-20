package ejerciciosUD2_Hilos;

import java.util.Random;

public class Ej5_ConcurrenteBasica {

    /*
    Lanza tres hilos distintos, cada uno debe imprimir una letra diferente (A, B y C) diez veces.
    Cada hilo debe usar una pausa distinta (70, 90 y 110 ms).
    Observa cómo se intercalan las salidas en consola
     */

    static void main() {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();

                char letra = 'A' + (char )random.nextInt(2);

                System.out.println("");
            }
        })


    }
}
