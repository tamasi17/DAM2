package ejemplos.concurrent.executor;

import java.util.concurrent.*;

public class Ejemplo_CompletableFuture {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> futuro = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " calculando...");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            return 42;
        });

        // Se ejecuta cuando termina, sin bloquear el hilo principal
        futuro.thenAccept(resultado ->
            System.out.println("Resultado recibido: " + resultado)
        );

        System.out.println("El hilo principal sigue trabajando...");
        Thread.sleep(1500);
    }
}
