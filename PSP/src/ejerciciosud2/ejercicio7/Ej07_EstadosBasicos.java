package ejerciciosud2.ejercicio7;

public class Ej07_EstadosBasicos {

    public static void main(String[] args) throws InterruptedException {

        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                ie.printStackTrace();
            }
        }, "Estado");

        System.out.println("Antes de start: " + hilo.getState()); // NEW
        hilo.start();
        Thread.sleep(50);
        System.out.println("Durante ejecución: " + hilo.getState()); // RUNNABLE o TIMED_WAITING
        hilo.join();
        System.out.println("Tras finalizar: " + hilo.getState()); // TERMINATED
    }
}
