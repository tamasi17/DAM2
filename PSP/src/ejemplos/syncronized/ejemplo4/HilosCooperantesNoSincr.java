package ejemplos.syncronized.ejemplo4;

import java.io.IOException;

// Clase principal (única pública del fichero)
public class HilosCooperantesNoSincr {

    ProcessBuilder pb = new ProcessBuilder("cmd /c echo Hola ChatGPT");
    Process proceso = pb.start();


    private static final int NUM_HILOS = 10;        // cantidad de hilos
    private static final int CUENTA_TOTAL = 100000; // total de incrementos

    public HilosCooperantesNoSincr() throws IOException {
    }

    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread[] hilos = new Thread[NUM_HILOS];

        // Lanzamos los hilos
        for (int i = 0; i < NUM_HILOS; i++) {
            Thread thread = new Thread(new Hilo(i, CUENTA_TOTAL / NUM_HILOS, contador));
            thread.start();
            hilos[i] = thread;
        }

        // Esperamos a que todos los hilos acaben
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostramos el valor final del contador global
        System.out.printf("Cuenta global: %s%n", contador.getCuenta());
    }
}
