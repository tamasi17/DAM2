package ejerciciosUD2.ejercicio14;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProductorConsumidorBQ {

    private static final int CAPACIDAD = 3;

    public static void main(String[] args) {
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(CAPACIDAD);

       //procutor introduce diez número
        Runnable productor = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    // put() BLOQUEA si la cola está llena hasta que haya hueco.
                    buffer.put(i);
                    System.out.println("[PRODUCTOR] intento " + i + "  (tamaño=" + buffer.size() + "/" + CAPACIDAD + ")");
                    TimeUnit.MILLISECONDS.sleep(250); // simula producir
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // 3) Consumidor: saca 10 números.
        Runnable consumidor = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    // take() BLOQUEA si la cola está vacía hasta que haya algo.
                    int x = buffer.take();
                    System.out.println("   [CONSUMIDOR] intento " + x + "  (tamaño=" + buffer.size() + "/" + CAPACIDAD + ")");
                    TimeUnit.MILLISECONDS.sleep(400); // simula consumir
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(productor, "Productor").start();
        new Thread(consumidor, "Consumidor").start();
    }
}
