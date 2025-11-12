package ejemplos.concurrent.executor;

import java.util.concurrent.*;

public class Ejemplo4_ScheduledExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable tarea = () -> System.out.println(Thread.currentThread().getName() +
                                                  " ejecuta tarea programada");

        System.out.println("Programando ejecución dentro de 3 segundos...");
        scheduler.schedule(tarea, 3, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
