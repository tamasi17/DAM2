package ejerciciosUD2.ejercicio31;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Ejercicio31 {

    public static void main(String[] args) {

        final int NUMERO_TAREAS = 12;
        final int POOL_SIZE = 3;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(POOL_SIZE);

        for (int i = 1; i <= NUMERO_TAREAS; i++) {

            final int id = i;
            final int delay = ThreadLocalRandom.current().nextInt(1, 6); // 1–5 segundos

            scheduler.schedule(() -> {
                String hilo = Thread.currentThread().getName();
                System.out.println("[" + hilo + "] Inicio tarea " + id + " (retraso programado = " + delay + " s)");
                try {
                    Thread.sleep(500); // simula trabajo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("[" + hilo + "] Tarea " + id + " interrumpida.");
                    return;
                }
                System.out.println("[" + hilo + "] Fin tarea " + id);
            }, delay, TimeUnit.SECONDS);

        }

        scheduler.shutdown();

        try {

            boolean terminado = scheduler.awaitTermination(5, TimeUnit.SECONDS);
            if (terminado) {
                System.out.println("Todas las tareas programadas han finalizado.");
            } else {
                System.err.println("Tiempo de espera agotado. Algunas tareas no han terminado.");
                scheduler.shutdownNow();
            }

        } catch (InterruptedException e) {
            System.err.println("Main interrumpido durante la espera.");
            Thread.currentThread().interrupt();
            scheduler.shutdownNow();
        }
    }
}
