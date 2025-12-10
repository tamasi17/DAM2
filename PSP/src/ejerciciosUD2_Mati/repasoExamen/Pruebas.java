package ejerciciosUD2_Mati.repasoExamen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Pruebas {


    static void main() {


        ReentrantLock lock1 = new ReentrantLock(true);
        ReentrantLock lock2 = new ReentrantLock();

        Runnable tarea = new Runnable() {
            @Override
            public void run() {

                try {
                    if (lock1.tryLock(3, TimeUnit.SECONDS)) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("Tengo el lock: " + Thread.currentThread().getName());
                        } finally {
                            lock1.unlock();
                            System.out.println("Libero el lock: " + Thread.currentThread().getName());
                        }
                    } else {
                        System.out.println("Esperé demasiado, aborto: " + Thread.currentThread().getName());
                    }
                } catch (InterruptedException ie) {
                    System.err.println(ie.getLocalizedMessage());
                }
            }


        };

        try (
                ExecutorService executorService = Executors.newFixedThreadPool(5);) {

            for (int i = 0; i < 5; i++) {
                executorService.submit(tarea);
            }


            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.SECONDS);

        } catch (
                InterruptedException re) {
            System.out.

                    println("Hilo interrumpido mientras espera terminacion " + re.getLocalizedMessage());
            throw new

                    RuntimeException(re);
        }


    }
}
