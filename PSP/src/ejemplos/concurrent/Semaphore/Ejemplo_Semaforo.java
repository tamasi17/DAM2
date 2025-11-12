package ejemplos.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class Ejemplo_Semaforo {

    // Solo 3 coches pueden entrar a la vez
    private static final Semaphore parking = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(new Coche(i)).start();
        }
    }

    static class Coche implements Runnable {
        private final int id;

        Coche(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Coche " + id + " quiere entrar al parking...");
                parking.acquire(); // espera si no hay plazas libres
                System.out.println("Coche " + id + " ha entrado. Plazas libres: " + parking.availablePermits());

                Thread.sleep((long) (Math.random() * 2000 + 1000)); // simula tiempo aparcado

                System.out.println("Coche " + id + " sale del parking.");
                parking.release(); // libera una plaza
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
