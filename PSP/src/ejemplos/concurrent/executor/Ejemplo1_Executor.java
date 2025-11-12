package ejemplos.concurrent.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Ejemplo1_Executor {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();

        // Ejecutamos varias tareas con el mismo hilo reutilizado
        for (int i = 1; i <= 3; i++) {
            int num = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() +
                                   " ejecutando tarea " + num);
            });
        }
    }
}
