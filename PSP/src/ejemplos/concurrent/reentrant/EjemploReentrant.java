package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class EjemploReentrant {

    private final ReentrantLock lock = new ReentrantLock();

    public void metodoA() {
        lock.lock(); // intenta adquirir el bloqueo
        try {
            System.out.println("Ejecutando A");
            metodoB(); // también requiere el mismo lock
        } finally {
            lock.unlock(); // libera siempre el bloqueo
        }
    }

    public void metodoB() {

        lock.lock(); // el mismo hilo puede reentrar sin bloquearse

        try {
            System.out.println("Ejecutando B");
        } finally {
            lock.unlock();
        }
    }
}
