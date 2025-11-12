package ejemplos.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class Ejemplo1_AforoDiscoteca {

    // La discoteca solo permite la entrada de 3 personas a la vez
    private static final Semaphore aforo = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
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
            try {
                System.out.println("Persona " + id + " quiere entrar a la discoteca...");
                aforo.acquire(); // espera si el local está lleno
                System.out.println("Persona " + id + " entra. Plazas libres: " + aforo.availablePermits());

                Thread.sleep((long) (Math.random() * 2000 + 1000)); // tiempo dentro de la discoteca

                System.out.println("Persona " + id + " sale de la discoteca.");
                aforo.release(); // libera una plaza
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
