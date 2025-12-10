package ejerciciosUD2.ejercicio13;

public class Consumidor implements Runnable {
    private final Cesta cesta;

    public Consumidor(Cesta cesta) {
        this.cesta = cesta;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) { // cada consumidor coge 5 manzanas
            cesta.consumirManzana();
            try {
                Thread.sleep(400); // simula el tiempo de consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
