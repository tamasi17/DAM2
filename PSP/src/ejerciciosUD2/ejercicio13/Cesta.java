package ejerciciosUD2.ejercicio13;

public class Cesta {
    private int manzana;             // la manzana compartida
    private boolean disponible = false; // indica si hay manzana o no

    public synchronized void producirManzana() {
        while (disponible) { // si ya hay una manzana, el productor espera
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        manzana = (int) (Math.random() * 100); // se produce una manzana (valor aleatorio)
        System.out.println(Thread.currentThread().getName() +
                " produce la manzana " + manzana);

        disponible = true;
        notifyAll(); // avisa a todos los consumidores que pueden cogerla
    }

    public synchronized void consumirManzana() {
        while (!disponible) { // si no hay manzana, el consumidor espera
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("   " + Thread.currentThread().getName() +
                " consume la manzana " + manzana);

        disponible = false;
        notifyAll(); // avisa a los productores que pueden producir otra
    }
}
