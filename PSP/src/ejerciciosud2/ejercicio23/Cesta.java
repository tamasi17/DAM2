package ejerciciosud2.ejercicio23;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cesta {

    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacidad = 5;

    // Lock justo (fair) para evitar starvation entre productor/consumidor.
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition noLleno = lock.newCondition();
    private final Condition noVacio = lock.newCondition();

    public void producir(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacidad) {
                noLleno.await();       // esperar a que haya hueco
            }
            buffer.addLast(item);
            System.out.println("Productor produce → " + item);
            // tras producir, avisamos a los consumidores
            noVacio.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int consumir() throws InterruptedException {

        lock.lock();

        try {
            while (buffer.isEmpty()) {
                noVacio.await();        // esperar a que haya algo
            }
            int item = buffer.removeFirst();
            System.out.println("Consumidor consume ← " + item);
            // tras consumir, avisamos a los productores
            noLleno.signalAll();
            return item;

        } finally {
            lock.unlock();
        }
    }
}
