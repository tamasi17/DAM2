package ejerciciosUD2_Hilos;

import java.util.Random;

public class Ej5_ConcurrenteBasica extends Thread {

    /*
    Lanza tres hilos distintos, cada uno debe imprimir una letra diferente (A, B y C) diez veces.
    Cada hilo debe usar una pausa distinta (70, 90 y 110 ms).
    Observa cómo se intercalan las salidas en consola
     */

    int waitingTime;

    public Ej5_ConcurrenteBasica(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {

            Random random = new Random();
            int rand = random.nextInt(3);

            char letra = (char) ('A' + rand);

            String espacio = "";
            if (rand == 1) {
                espacio = "-";
            } else if (rand == 2) {
                espacio = "---";
            }

            System.out.println(espacio + letra);

            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static void main() {

        Thread thread1 = new Ej5_ConcurrenteBasica(70);
        thread1.start();
        Thread thread2 = new Ej5_ConcurrenteBasica(90);
        thread2.start();
        Thread thread3 = new Ej5_ConcurrenteBasica(100);
        thread3.start();


    }
}
