package ejerciciosud2.ejercicio21.concurrent_reentrantlock;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneradorClientesConcurrente implements Runnable {

    private final BarberiaConcurrente barberia;
    private final int totalClientesAGenerar;

    private final Random generadorAleatorio = new Random();
    private final AtomicInteger contadorClientes = new AtomicInteger(1);

    private static final int RETRASO_MINIMO_LLEGADA_MS = 80;
    private static final int RETRASO_MAXIMO_LLEGADA_MS = 250;

    public GeneradorClientesConcurrente(BarberiaConcurrente barberia, int totalClientesAGenerar) {
        this.barberia = barberia;
        this.totalClientesAGenerar = totalClientesAGenerar;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalClientesAGenerar; i++) {
                int retrasoLlegada = generarAleatorio(RETRASO_MINIMO_LLEGADA_MS, RETRASO_MAXIMO_LLEGADA_MS);
                dormir(retrasoLlegada);

                int idCliente = contadorClientes.getAndIncrement();
                barberia.llegaCliente(new Cliente(idCliente));
            }
        } finally {
            // señal imprescindible para cierre ordenado del barbero
            barberia.cerrarLlegadas();
        }
    }

    private void dormir(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int generarAleatorio(int minimoIncluido, int maximoIncluido) {
        return minimoIncluido + generadorAleatorio.nextInt(maximoIncluido - minimoIncluido + 1);
    }
}
