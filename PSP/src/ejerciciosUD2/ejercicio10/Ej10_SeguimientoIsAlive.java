package ejerciciosUD2.ejercicio10;


public class Ej10_SeguimientoIsAlive {

    public static void main(String[] args) throws InterruptedException {

        Thread carga = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                ie.printStackTrace();
            }
        }, "Carga");

        carga.start();

        while (carga.isAlive()) {
            System.out.print(".");
            Thread.sleep(100);
        }

        System.out.println("\nCompletado");
    }
}
