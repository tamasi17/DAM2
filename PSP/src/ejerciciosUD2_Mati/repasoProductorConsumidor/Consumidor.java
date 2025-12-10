package ejerciciosUD2_Mati.repasoProductorConsumidor;

public class Consumidor implements Runnable{

    int cantidadManzanas;
    ContenedorSidra contenedor;

    public Consumidor(ContenedorSidra contenedor, int cantidadManzanas) {
        this.contenedor = contenedor;
        this.cantidadManzanas = cantidadManzanas;
    }

    @Override
    public void run() {

        // aqui llama a put(cantidadManzanas)
        contenedor.take(cantidadManzanas);
        System.out.println("Intentando coger manzanas "+ Thread.currentThread().getName());

    }
}
