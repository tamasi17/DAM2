package ejerciciosUD2.ejercicio12.productorconsumidor;

// Consumidor.java
public class Consumidor implements Runnable {
    private final Cesta recurso;

    public Consumidor(Cesta recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {

            recurso.consumir();
            try {
                Thread.sleep(800); // simula el tiempo de consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
