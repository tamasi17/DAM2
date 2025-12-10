package ejerciciosUD2.ejercicio30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio30 {

    public static void main(String[] args) {

        final int POOL_SIZE = 3;
        final int N_TAREAS = 10;

        // Pool fijo de 3 hilos
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

        // Enviar 10 tareas Runnable numeradas (1..10)
        for (int i = 1; i <= N_TAREAS; i++) {
            final int id = i; // capturar índice
            pool.execute(() -> {
                String hilo = Thread.currentThread().getName();
                System.out.println("[" + hilo + "] Inicio tarea " + id);
                try {
                    // pausa aleatoria entre 300 y 1000 ms
                    int pausa = ThreadLocalRandom.current().nextInt(300, 1001);
                    Thread.sleep(pausa);

                } catch (InterruptedException e) {
                    // Restablecer el flag y salir ordenadamente
                    Thread.currentThread().interrupt();
                    System.err.println("[" + hilo + "] Tarea " + id + " interrumpida.");
                    return;
                }
                System.out.println("[" + hilo + "] Fin tarea " + id);
            });
        }

        // No se aceptan más trabajos
        pool.shutdown();

        // Esperar finalización hasta 5 segundos
        try {
            boolean ok = pool.awaitTermination(5, TimeUnit.SECONDS);
            if (ok) {
                System.out.println("Todas las tareas han finalizado correctamente.");
            } else {
                System.err.println("Tiempo de espera agotado. Algunas tareas no han terminado.");
                pool.shutdownNow(); // intento de cierre forzoso
            }
        } catch (InterruptedException e) {
            // Interrupción durante la espera: informar, restaurar flag y forzar cierre
            System.err.println("Main interrumpido durante awaitTermination.");
            Thread.currentThread().interrupt();
            pool.shutdownNow();
        }
    }
}
