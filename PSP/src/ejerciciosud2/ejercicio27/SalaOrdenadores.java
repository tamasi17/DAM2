package ejerciciosud2.ejercicio27;

import java.util.concurrent.Semaphore;

public class SalaOrdenadores {

    private final Semaphore semaforo;

    public SalaOrdenadores(int puestos) {
        //política justa para reducir inanición bajo contención
        this.semaforo = new Semaphore(puestos, true);
    }

    public void adquirirPuesto() throws InterruptedException {
        semaforo.acquire();
    }

    public void liberarPuesto() {
        semaforo.release();
    }
}
