package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo3_TryLock {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Runnable tarea = () -> {
            String nombre = Thread.currentThread().getName();
            if (lock.tryLock()) {
                try {
                    System.out.println(nombre + " consiguió el lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                    System.out.println(nombre + " liberó el lock");
                }
            } else {
                System.out.println(nombre + " no pudo obtener el lock, reintentará más tarde");
            }
        };

        new Thread(tarea, "Hilo-1").start();
        new Thread(tarea, "Hilo-2").start();
    }
}
