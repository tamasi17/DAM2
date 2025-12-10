package ejerciciosUD2.ejercicio27;

import java.util.concurrent.ThreadLocalRandom;

public class Estudiante implements Runnable {

    private final int id;
    private final SalaOrdenadores sala;

    public Estudiante(int id, SalaOrdenadores sala) {
        this.id = id;
        this.sala = sala;
    }

    @Override
    public void run() {

        System.out.println("Estudiante " + id + " intenta acceder a la sala.");
        boolean puestoAdquirido = false;

        try {
            sala.adquirirPuesto();
            puestoAdquirido = true;

            System.out.println("Estudiante " + id + " ha comenzado a usar un ordenador.");

            int usoMs = ThreadLocalRandom.current().nextInt(1000, 3001); // 1000–3000 ms
            Thread.sleep(usoMs);

            System.out.println("Estudiante " + id + " ha terminado y deja libre un ordenador.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Estudiante " + id + " interrumpido. Abandona la sala.");
        } finally {
            if (puestoAdquirido) {
                sala.liberarPuesto();
            }
        }
    }
}
