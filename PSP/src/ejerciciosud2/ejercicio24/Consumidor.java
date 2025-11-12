package ejerciciosud2.ejercicio24;

public class Consumidor implements Runnable {
    private final Cesta cesta;
    private final int total;

    public Consumidor(Cesta cesta, int total) {
        this.cesta = cesta;
        this.total = total;
    }

    @Override
    public void run() {
        for (int i = 0; i < total; i++) {
            try {
                cesta.consumir();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
