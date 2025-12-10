package ejerciciosUD2.ejercicio32;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomic implements Runnable {

    private final AtomicInteger contador;

    public ContadorAtomic(AtomicInteger contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementAndGet(); // operación atómica
        }
    }
}
