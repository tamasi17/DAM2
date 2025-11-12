package ejerciciosud2.ejercicio35;

public class MainLectoresEscritores {

    public static void main(String[] args) {

        final int NUM_LECTORES = 6;
        final int NUM_ESCRITORES = 2;
        final int LECTURAS = 20;
        final int ESCRITURAS = 15;

        RecursoCompartido recurso = new RecursoCompartido();

        Thread[] lectores = new Thread[NUM_LECTORES];
        Thread[] escritores = new Thread[NUM_ESCRITORES];

        // Crear lectores
        for (int i = 0; i < NUM_LECTORES; i++) {
            lectores[i] = new Thread(new Lector(recurso, LECTURAS), "lector-" + (i + 1));
        }
        // Crear escritores
        for (int i = 0; i < NUM_ESCRITORES; i++) {
            escritores[i] = new Thread(new Escritor(recurso, ESCRITURAS), "escritor-" + (i + 1));
        }

        // Iniciar
        for (Thread hiloLector : lectores) hiloLector.start();
        for (Thread hiloEscritor : escritores) hiloEscritor.start();

        // Esperar fin
        try {
            for (Thread t : lectores) t.join();
            for (Thread t : escritores) t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main interrumpido durante join()");
        }

        System.out.println("Lectores–Escritores: fin de la simulación.");
    }
}
