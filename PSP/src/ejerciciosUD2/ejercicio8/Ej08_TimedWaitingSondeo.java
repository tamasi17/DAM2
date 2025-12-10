package ejerciciosUD2.ejercicio8;

public class Ej08_TimedWaitingSondeo {

    public static void main(String[] args) throws InterruptedException {

        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(600);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                ie.printStackTrace();
            }
        }, "Dormilon");

        hilo.start();

        while (hilo.isAlive()) {
            System.out.println("Estado actual: " + hilo.getState()); // esperado: TIMED_WAITING durante sleep
            Thread.sleep(100);
        }
        System.out.println("Estado final: " + hilo.getState()); // TERMINATED
    }
}
