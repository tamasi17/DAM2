package ejerciciosUD2.ejercicio6;

public class Tarea implements Runnable {

    private final String nombre;
    private final int pausaMs;
    private final int repeticiones;

    public Tarea(String nombre, int pausaMs, int repeticiones) {
        this.nombre = nombre;
        this.pausaMs = pausaMs;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        for (int i = 1; i <= repeticiones; i++) {
            System.out.println(nombre + " - ejecución " + i);
            try {
                Thread.sleep(pausaMs);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                ie.printStackTrace();
            }
        }
    }
}
