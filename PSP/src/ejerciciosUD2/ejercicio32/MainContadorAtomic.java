package ejerciciosUD2.ejercicio32;

import java.util.concurrent.atomic.AtomicInteger;

public class MainContadorAtomic {

    public static void main(String[] args) {

        final int HILOS = 5;
        AtomicInteger contador = new AtomicInteger(0);

        Thread[] threads = new Thread[HILOS];
        for (int i = 0; i < HILOS; i++) {
            threads[i] = new Thread(new ContadorAtomic(contador), "Hilo-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Valor final del contador: " + contador.get());
        System.out.println("Valor esperado: 5000");
    }
}
