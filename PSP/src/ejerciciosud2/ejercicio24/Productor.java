package ejerciciosud2.ejercicio24;

import java.util.ArrayList;
import java.util.List;

public class Productor implements Runnable {
    private final Cesta cesta;
    private final int total;
    private final int lote; // cuántos produce de golpe

    public Productor(Cesta cesta, int total, int lote) {
        this.cesta = cesta;
        this.total = total;
        this.lote = lote;
    }

    @Override
    public void run() {
        int valor = 1;
        while (valor <= total) {
            List<Integer> items = new ArrayList<>();
            for (int i = 0; i < lote && valor <= total; i++) {
                items.add(valor++);
            }
            try {
                cesta.producir(items);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
