package ejerciciosud2.ejercicio23;

public class Productor implements Runnable {

    private final Cesta cesta;
    private final int cuantos;

    public Productor(Cesta cesta, int cuantos) {
        this.cesta = cesta;
        this.cuantos = cuantos;
    }

    @Override
    public void run() {
        for (int i = 1; i <= cuantos; i++) {
            try {
                cesta.producir(i);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
