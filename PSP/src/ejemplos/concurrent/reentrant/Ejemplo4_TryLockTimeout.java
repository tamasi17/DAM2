package ejemplos.concurrent.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo4_TryLockTimeout {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable tarea = () -> {
            String nombre = Thread.currentThread().getName();
            try {
                if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(nombre + " obtuvo el lock");
                        Thread.sleep(1000);
                    } finally {
                        lock.unlock();
                        System.out.println(nombre + " liberó el lock");
                    }
                } else {
                    System.out.println(nombre + " esperó demasiado, aborta");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(tarea, "Hilo-1").start();
        new Thread(tarea, "Hilo-2").start();
    }
}
