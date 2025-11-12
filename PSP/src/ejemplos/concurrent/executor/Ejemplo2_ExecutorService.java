package ejemplos.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ejemplo2_ExecutorService {

    public static void main(String[] args) {
        // Pool de 3 hilos que se reutilizan
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            int num = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() +
                                   " procesando tarea " + num);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown(); // no acepta más tareas
        try {
            if (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Forzando cierre...");
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
