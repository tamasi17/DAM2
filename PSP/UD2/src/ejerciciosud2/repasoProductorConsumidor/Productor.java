package ejerciciosud2.repasoProductorConsumidor;

public class Productor implements Runnable {

    int cantidadManzanas;
    ContenedorSidra contenedor;

    public Productor(int cantidadManzanas) {
        this.cantidadManzanas = cantidadManzanas;
    }

    public Productor(int cantidadManzanas, ContenedorSidra contenedor) {
        this.cantidadManzanas = cantidadManzanas;
        this.contenedor = contenedor;
    }

    @Override
    public void run() {

        System.out.println("Poniendo manzanas");
        // aqui llama a put(cantidadManzanas)

    }
}
