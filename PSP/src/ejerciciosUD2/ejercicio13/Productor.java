package ejerciciosUD2.ejercicio13;

public class Productor implements Runnable {
    private final Cesta cesta;

    public Productor(Cesta cesta) {
        this.cesta = cesta;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) { // cada productor hace 5 manzanas
            cesta.producirManzana();
            try {
                Thread.sleep(300); // simula el tiempo de producción
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
