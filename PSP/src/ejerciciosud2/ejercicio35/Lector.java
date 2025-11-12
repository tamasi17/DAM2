package ejerciciosud2.ejercicio35;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Lector implements Runnable {
    private final RecursoCompartido recurso;
    private final int lecturas; // p.ej. 20

    public Lector(RecursoCompartido recurso, int lecturas) {
        this.recurso = recurso;
        this.lecturas = lecturas;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();

        try {
            for (int i = 0; i < lecturas && !Thread.currentThread().isInterrupted(); i++) {
                List<Integer> snap = recurso.leerSnapshot(nombre);
                long suma = 0;
                for (int v : snap) suma += v;

                System.out.printf("[%s] lee → %s (elementos leídos=%d)%n",
                        nombre, snap, snap.size());

                // pausa 80–200 ms
                Thread.sleep(ThreadLocalRandom.current().nextInt(80, 201));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] interrumpido durante lectura%n", nombre);
        }
    }
}
