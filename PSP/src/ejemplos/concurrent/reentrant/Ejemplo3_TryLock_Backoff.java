package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo3_TryLock_Backoff {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int recursoCompartido = 0;

    public static void main(String[] args) {

        Runnable tarea = () -> {
            String nombre = Thread.currentThread().getName();

            for (int i = 0; i < 5; i++) {
                if (lock.tryLock()) { // intenta coger el lock sin bloquearse
                    try {
                        System.out.println(nombre + " accede al recurso");
                        recursoCompartido++;
                        Thread.sleep(200); // simula trabajo
                        System.out.println(nombre + " libera el recurso (valor = " + recursoCompartido + ")");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    // No puede entrar, así que retrocede (espera aleatoria)
                    long espera = (long) (Math.random() * 300);
                    System.out.println(nombre + " no pudo entrar, espera " + espera + " ms antes de reintentar");
                    try {
                        Thread.sleep(espera);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        // Lanzamos varios hilos que compiten por el mismo recurso
        for (int i = 1; i <= 3; i++) {
            new Thread(tarea, "Hilo-" + i).start();
        }
    }
}
