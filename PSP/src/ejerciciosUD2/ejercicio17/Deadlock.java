package ejerciciosUD2.ejercicio17;

public class Deadlock {

    private static final Object recursoA = new Object();
    private static final Object recursoB = new Object();

    public static void main(String[] args) {

        Thread hilo1 = new Thread(() -> {

            synchronized (recursoA) {

                System.out.println("Hilo 1: bloqueó recurso A");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println("Hilo interrumpido durante la pausa: " + ie.getMessage());
                    Thread.currentThread().interrupt(); // restablece el flag
                }

                System.out.println("Hilo 1: intentando bloquear recurso B...");
                synchronized (recursoB) {
                    System.out.println("Hilo 1: bloqueó recurso B");
                }
            }

        });

        Thread hilo2 = new Thread(() -> {
            synchronized (recursoB) {

                System.out.println("Hilo 2: bloqueó recurso B");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println("Hilo interrumpido durante la pausa: " + ie.getMessage());
                    Thread.currentThread().interrupt(); // restablece el flag
                }
                System.out.println("Hilo 2: intentando bloquear recurso A...");
                synchronized (recursoA) {
                    System.out.println("Hilo 2: bloqueó recurso A");
                }
            }
        });

        hilo1.start();
        hilo2.start();
    }
}
