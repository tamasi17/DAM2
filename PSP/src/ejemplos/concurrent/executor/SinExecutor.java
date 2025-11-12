package ejemplos.concurrent.executor;

public class SinExecutor {

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread hilo = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " ejecutando tarea");
            });
            hilo.start();
        }
    }
}
