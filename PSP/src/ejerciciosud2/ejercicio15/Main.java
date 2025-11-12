package ejerciciosud2.ejercicio15;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        CestaConBufferCircular<Integer> buffer = new CestaConBufferCircular<>(3);

        Runnable productor = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    buffer.put(i);
                    System.out.println("[PRODUCTOR] → " + i + " (tamaño=" + buffer.size() + ")");
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Runnable consumidor = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Integer x = buffer.take();
                    System.out.println("      [CONSUMIDOR] ← " + x + " (tamaño=" + buffer.size() + ")");
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Thread productor1 = new Thread(productor, "Productor");
        Thread consumidor1 = new Thread(consumidor, "Consumidor");

        productor1.start();
        consumidor1.start();
    }
}
