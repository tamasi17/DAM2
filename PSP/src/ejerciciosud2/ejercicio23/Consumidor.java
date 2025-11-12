package ejerciciosud2.ejercicio23;

public class Consumidor implements Runnable {

    private final Cesta cesta;
    private final int cuantos;

    public Consumidor(Cesta cesta, int cuantos) {
        this.cesta = cesta;
        this.cuantos = cuantos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= cuantos; i++) {
            try {
                cesta.consumir();
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
