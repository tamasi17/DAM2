package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo1_Basico {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int contador = 0;

    public static void main(String[] args) {
        Runnable tarea = () -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    contador++;
                    System.out.println(Thread.currentThread().getName() + " -> contador = " + contador);
                } finally {
                    lock.unlock();
                }
            }
        };

        new Thread(tarea, "Hilo-1").start();
        new Thread(tarea, "Hilo-2").start();
    }
}
