package ejemplos.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class Ejemplo2_EntradaRapida {

    private static final Semaphore aforo = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            new Thread(new Persona(i)).start();
        }
    }

    static class Persona implements Runnable {
        private final int id;

        Persona(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            if (aforo.tryAcquire()) { // intenta entrar sin bloquear
                try {
                    System.out.println("Persona " + id + " entra en la discoteca.");
                    Thread.sleep((long) (Math.random() * 2000 + 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.println("Persona " + id + " sale de la discoteca.");
                    aforo.release();
                }
            } else {
                System.out.println("Persona " + id + " no pudo entrar. Aforo completo.");
            }
        }
    }
}
