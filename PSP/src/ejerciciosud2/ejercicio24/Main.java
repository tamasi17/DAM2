package ejerciciosud2.ejercicio24;

public class Main {
    public static void main(String[] args) {
        Cesta cesta = new Cesta();

        Thread productor = new Thread(new Productor(cesta, 20, 3), "Productor");
        Thread consumidor = new Thread(new Consumidor(cesta, 20), "Consumidor");

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

        System.out.println("Fin del programa.");
    }
}
