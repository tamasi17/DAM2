package ejerciciosud2.ejercicio23;

public class Main {

    public static void main(String[] args)  {

        Cesta cesta = new Cesta();
        int numeroElementos = 10; // produce/consume 10 elementos

        Thread productor = new Thread(new Productor(cesta, numeroElementos), "Productor");
        Thread consumidor = new Thread(new Consumidor(cesta, numeroElementos), "Consumidor");

        productor.start();
        consumidor.start();


        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            // 1) Informamos
            System.err.println("Main interrumpido mientras esperaba a los hilos: " + e.getMessage());
            // 2) Restablecemos el flag de interrupción por buena práctica
            Thread.currentThread().interrupt();
        }


        System.out.println("Fin.");
    }
}
