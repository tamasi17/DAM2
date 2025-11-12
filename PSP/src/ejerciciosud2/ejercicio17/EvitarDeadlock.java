package ejerciciosud2.ejercicio17;

public class EvitarDeadlock {

    private static final Object recursoA = new Object();
    private static final Object recursoB = new Object();

    public static void main(String[] args) {

        Thread hilo1 = new Thread(() -> conseguirRecursos(recursoA, recursoB));
        Thread hilo2 = new Thread(() -> conseguirRecursos(recursoA, recursoB)); // mismo orden

        hilo1.start();
        hilo2.start();
    }

    private static void conseguirRecursos(Object primero, Object segundo) {

        synchronized (primero) {

            System.out.println(Thread.currentThread().getName() + " bloqueó el primero");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido durante la pausa: " + ie.getMessage());
                Thread.currentThread().interrupt(); // restablece el flag
            }
                synchronized (segundo) {
                System.out.println(Thread.currentThread().getName() + " bloqueó el segundo");
            }
        }
    }
}
