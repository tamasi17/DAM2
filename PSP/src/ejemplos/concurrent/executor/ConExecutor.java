package ejemplos.concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConExecutor {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            int tarea = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() +
                                   " ejecutando tarea " + tarea);
            });
        }
    }
}
