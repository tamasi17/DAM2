package ejerciciosud2.ejercicio35;

import java.util.concurrent.ThreadLocalRandom;

public class Escritor implements Runnable {
    private final RecursoCompartido recurso;
    private final int operaciones; // p.ej. 15

    public Escritor(RecursoCompartido recurso, int operaciones) {
        this.recurso = recurso;
        this.operaciones = operaciones;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        int siguiente = 1;

        try {
            for (int i = 0; i < operaciones && !Thread.currentThread().isInterrupted(); i++) {
                recurso.escribir(siguiente++, nombre);
                // pausa 100–300 ms
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 301));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("[%s] interrumpido durante escritura%n", nombre);
        }
    }
}
