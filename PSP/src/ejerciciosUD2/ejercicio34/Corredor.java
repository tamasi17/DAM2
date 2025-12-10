package ejerciciosUD2.ejercicio34;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class Corredor implements Runnable {
    private final int id;
    private final CyclicBarrier barrera;

    public Corredor(int id, CyclicBarrier barrera) {
        this.id = id;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            int tiempoPreparacion = ThreadLocalRandom.current().nextInt(1000, 4000);
            Thread.sleep(tiempoPreparacion);
            System.out.println("Corredor " + id + " está listo.");

            barrera.await(); // espera a que todos estén listos

            System.out.println("Corredor " + id + " ha comenzado la carrera.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            System.err.println("La barrera se rompió antes de tiempo para el corredor " + id);
        }
    }
}
