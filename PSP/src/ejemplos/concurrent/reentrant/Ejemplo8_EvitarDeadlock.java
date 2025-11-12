package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo8_EvitarDeadlock {

    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> ejecutar("Hilo-1", lockA, lockB)).start();
        new Thread(() -> ejecutar("Hilo-2", lockB, lockA)).start();
    }

    private static void ejecutar(String nombre, ReentrantLock primero, ReentrantLock segundo) {
        while (true) {
            if (primero.tryLock()) {
                try {
                    Thread.sleep(50);
                    if (segundo.tryLock()) {
                        try {
                            System.out.println(nombre + " ejecuta sección crítica");
                            break;
                        } finally {
                            segundo.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    primero.unlock();
                }
            }
            dormirAleatorio();
        }
    }

    private static void dormirAleatorio() {
        try { Thread.sleep((long)(Math.random() * 50)); } catch (InterruptedException ignored) {}
    }
}
