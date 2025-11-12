package ejemplos.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class Ejemplo4_Guardarropa {

    private static final Semaphore mutex = new Semaphore(1);
    private static int prendasGuardadas = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            new Thread(new Cliente(i)).start();
        }
    }

    static class Cliente implements Runnable {
        private final int id;

        Cliente(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                mutex.acquire(); // exclusión mutua: solo un cliente a la vez
                int valor = prendasGuardadas;
                Thread.sleep(500); // tiempo de gestión del guardarropa
                prendasGuardadas = valor + 1;
                System.out.println("Cliente " + id + " deja su prenda. Total guardadas: " + prendasGuardadas);
                mutex.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
