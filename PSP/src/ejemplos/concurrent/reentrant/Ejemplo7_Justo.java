package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo7_Justo {

    private static final ReentrantLock lock = new ReentrantLock(true); // justo

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " obtiene el lock");
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Hilo-" + i).start();
        }
    }
}
