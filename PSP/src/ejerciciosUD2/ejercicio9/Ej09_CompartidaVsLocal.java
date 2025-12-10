package ejerciciosUD2.ejercicio9;

public class Ej09_CompartidaVsLocal {

    private static int compartida = 0; // compartida por todos los hilos

    public static void main(String[] args) {
        Runnable tarea = () -> {
            int local = 0; // propia de cada hilo (pila)
            for (int i = 0; i < 4; i++) {
                compartida++;
                local++;
                System.out.println(Thread.currentThread().getName() +
                        " -> compartida=" + compartida + ", local=" + local);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ie) {
                    System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                    ie.printStackTrace();
                }
            }
        };

        Thread hilo1 = new Thread(tarea, "Hilo 1");
        Thread hilo2 = new Thread(tarea, "Hilo 2");

        hilo1.start();
        hilo2.start();
    }
}
