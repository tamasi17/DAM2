package ejerciciosud2.ejercicio24;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cesta {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacidad = 5;

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition noLleno = lock.newCondition();
    private final Condition noVacio = lock.newCondition();

    // producir varios elementos
    public void producir(List<Integer> items) throws InterruptedException {
        lock.lock();
        try {
            for (int item : items) {
                while (buffer.size() == capacidad) {
                    noLleno.await();
                }
                buffer.addLast(item);
                System.out.println("Productor produce → " + item);
                noVacio.signalAll(); // hay algo disponible
            }
        } finally {
            lock.unlock();
        }
    }

    // consumir un elemento
    public int consumir() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                noVacio.await();
            }
            int item = buffer.removeFirst();
            System.out.println("Consumidor consume ← " + item);
            noLleno.signalAll(); // hay hueco libre
            return item;
        } finally {
            lock.unlock();
        }
    }
}
