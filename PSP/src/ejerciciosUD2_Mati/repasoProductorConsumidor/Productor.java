package ejerciciosUD2_Mati.repasoProductorConsumidor;

public class Productor implements Runnable {

    int cantidadManzanas;
    ContenedorSidra contenedor;

    public Productor(int cantidadManzanas) {
        this.cantidadManzanas = cantidadManzanas;
    }

    public Productor(ContenedorSidra contenedor, int cantidadManzanas) {
        this.cantidadManzanas = cantidadManzanas;
        this.contenedor = contenedor;
    }

    @Override
    public void run() {

        // aqui llama a put(cantidadManzanas)
        contenedor.put(cantidadManzanas);
        System.out.println(Thread.currentThread().getName()+ ": poniendo manzanas - "+ cantidadManzanas);

    }
}
