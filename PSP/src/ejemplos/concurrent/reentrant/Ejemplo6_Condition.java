package ejemplos.concurrent.reentrant;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo6_Condition {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition noVacio = lock.newCondition();
    private static final Queue<Integer> cola = new LinkedList<>();

    public static void main(String[] args) {

        Thread productor = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                lock.lock();
                try {
                    cola.add(i);
                    System.out.println("Productor añade " + i);
                    noVacio.signal(); // despierta al consumidor
                } finally {
                    lock.unlock();
                }
                dormir(500);
            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    while (cola.isEmpty()) {
                        noVacio.await(); // espera hasta que haya algo
                    }
                    int valor = cola.remove();
                    System.out.println("Consumidor toma " + valor);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    lock.unlock();
                }
            }
        });

        productor.start();
        consumidor.start();
    }

    private static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ie) {
            System.out.println("Interrupted exception");
        }
    }
}
