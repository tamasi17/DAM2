package ejerciciosud2.repasoProductorConsumidor;

public class Consumidor implements Runnable{

    int cantidadManzanas;
    ContenedorSidra contenedor;

    public Consumidor(ContenedorSidra contenedor, int cantidadManzanas) {
        this.contenedor = contenedor;
        this.cantidadManzanas = cantidadManzanas;
    }

    @Override
    public void run() {

        System.out.println("Cogiendo manzanas");
        // aqui llama a put(cantidadManzanas)

    }
}
