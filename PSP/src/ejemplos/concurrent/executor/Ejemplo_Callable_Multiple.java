package ejemplos.concurrent.executor;

import java.util.concurrent.*;
import java.util.*;

public class Ejemplo_Callable_Multiple {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> tareas = List.of(
            () -> { Thread.sleep(500); return 1; },
            () -> { Thread.sleep(1000); return 2; },
            () -> { Thread.sleep(1500); return 3; }
        );

        List<Future<Integer>> resultados = pool.invokeAll(tareas);

        for (Future<Integer> future : resultados) {
            System.out.println("Resultado = " + future.get());
        }

        pool.shutdown();
    }
}
