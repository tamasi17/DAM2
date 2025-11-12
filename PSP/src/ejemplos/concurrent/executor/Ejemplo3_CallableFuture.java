package ejemplos.concurrent.executor;

import java.util.concurrent.*;

public class Ejemplo3_CallableFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable<Integer> tarea = () -> {
            System.out.println(Thread.currentThread().getName() + " calculando...");
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> resultado = pool.submit(tarea);

        System.out.println("Esperando resultado...");
        System.out.println("Resultado = " + resultado.get()); // bloquea hasta que termina

        pool.shutdown();
    }
}

/*
    Métodos principales de la interfaz Future<T> (java.util.concurrent):

    - boolean cancel(boolean mayInterruptIfRunning)
        Intenta cancelar la ejecución de la tarea.
        Si mayInterruptIfRunning = true y la tarea ya ha comenzado,
        se interrumpe el hilo si es posible.
        Devuelve true si se ha cancelado correctamente.

    - boolean isCancelled()
        Devuelve true si la tarea fue cancelada antes de completarse.

    - boolean isDone()
        Devuelve true si la tarea ya ha terminado, se haya completado correctamente,
        cancelado o lanzado una excepción.

    - T get()
        Espera (bloquea) hasta que la tarea haya terminado y devuelve el resultado.
        Puede lanzar InterruptedException o ExecutionException.

    - T get(long timeout, TimeUnit unit)
        Igual que get(), pero espera como máximo el tiempo indicado.
        Si no termina a tiempo, lanza TimeoutException.

    Notas importantes:
    ------------------
    • El método get() bloquea el hilo actual hasta que la tarea termine.
    • Si se necesita trabajo asíncrono o encadenar resultados, se recomienda usar CompletableFuture.
    • El resultado solo puede obtenerse una vez que isDone() == true.
    • Tras llamar a shutdown() en el ExecutorService, las tareas enviadas siguen ejecutándose
      y sus Future siguen siendo válidos hasta completarse.
*/
