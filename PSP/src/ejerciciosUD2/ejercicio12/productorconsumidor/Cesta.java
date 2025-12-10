package ejerciciosUD2.ejercicio12.productorconsumidor;

// ClaseCompartida.java
public class Cesta {

    private int manzana;
    private boolean disponible = false; // indica si hay manzana

    // método para producir
    public synchronized void producir(int valor) {
        while (disponible) { // si hay manzana, espera
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        manzana = valor;
        System.out.println("Productor produce → " + valor);
        disponible = true;
        notify(); // avisa al consumidor
    }

    // método para consumir
    public synchronized void consumir() {
        while (!disponible) { // si no hay manzana, espera
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("     Consumidor consume: ← " + manzana);
        disponible = false;
        notify(); // avisa al productor
    }
}
