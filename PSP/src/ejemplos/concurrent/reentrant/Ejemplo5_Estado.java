package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo5_Estado {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread hilo = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Lock adquirido: " + lock.isLocked());
                System.out.println("¿Yo tengo el lock?: " + lock.isHeldByCurrentThread());
            } finally {
                lock.unlock();
            }
        });

        hilo.start();
    }
}
