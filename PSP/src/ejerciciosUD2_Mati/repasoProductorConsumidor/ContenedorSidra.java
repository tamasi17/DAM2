package ejerciciosUD2_Mati.repasoProductorConsumidor;

public class ContenedorSidra {

    private final int MAX_MANZANAS = 50000;
    int cantidadManzanas;

    public ContenedorSidra() {
        this.cantidadManzanas = 0;
    }

    public synchronized void put(int añadidas){
        // si está lleno, wait
        while (  (cantidadManzanas + añadidas) > MAX_MANZANAS){
            try {
                wait();
            } catch (InterruptedException ie) {
                System.err.println("Error mientras Productor espera a meter manzanas");
            }
        }

        // si no, put cantidadManzanas
        cantidadManzanas += añadidas;
        System.out.println(Thread.currentThread().getName() + ": he añadido "
                + añadidas + " manzanas. Cantidad actual: "+ cantidadManzanas);

    }

    public synchronized void take (int quitadas){
        // si está vacio o hay menos que las que necesita cada marca, wait
        while ( (cantidadManzanas - quitadas) < 0){
            try {
                wait();
            } catch (InterruptedException ie) {
                System.err.println("Error mientras Consumidor espera a recibir manzanas");
            }
        }

        // si no, take cantidadManzanas
        cantidadManzanas -= quitadas;
        System.out.println(Thread.currentThread().getName() + ": he quitado "
                + quitadas + " manzanas. Cantidad actual: "+ cantidadManzanas);
        notifyAll();

    }

}
