package ejerciciosud2.ejercicio27;

public class MainSalaOrdenadores {

    public static void main(String[] args) {

        final int PUESTOS = 4;
        final int ESTUDIANTES = 10;

        SalaOrdenadores sala = new SalaOrdenadores(PUESTOS);
        Thread[] hilos = new Thread[ESTUDIANTES];

        for (int i = 0; i < ESTUDIANTES; i++) {
            hilos[i] = new Thread(new Estudiante(i + 1, sala), "Estudiante-" + (i + 1));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Main interrumpido mientras esperaba a " + hilo.getName());
                break;
            }
        }

        System.out.println("Todos los estudiantes han terminado su sesión.");
    }
}
