package ejerciciosUD2.ejercicio13;

public class Main {
    public static void main(String[] args) {
        Cesta cesta = new Cesta();

        // Creamos varios productores y consumidores
        Thread productor1 = new Thread(new Productor(cesta), "Productor-1");
        Thread productor2 = new Thread(new Productor(cesta), "Productor-2");

        Thread consumidor1 = new Thread(new Consumidor(cesta), "Consumidor-1");
        Thread consumidor2 = new Thread(new Consumidor(cesta), "Consumidor-2");
        Thread consumidor3 = new Thread(new Consumidor(cesta), "Consumidor-3");

        // Arrancamos todos los hilos
        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();

    }
}
