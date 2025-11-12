package ejerciciosud2.ejercicio21.syncronized;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneradorClientes implements Runnable {

    private final Barberia barberia;
    private final int totalClientes; // número de clientes a GENERAR (no a atender)
    private final Random generadorAleatorio = new Random();
    private final AtomicInteger contadorClientes = new AtomicInteger(1);

    private static final int RETRASO_MINIMO_LLEGADA_MS = 80;
    private static final int RETRASO_MAXIMO_LLEGADA_MS = 250;

    public GeneradorClientes(Barberia barberia, int totalClientes) {
        this.barberia = barberia;
        this.totalClientes = totalClientes;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalClientes; i++) {
                int retrasoLlegada = generarAleatorio(RETRASO_MINIMO_LLEGADA_MS, RETRASO_MAXIMO_LLEGADA_MS);
                dormir(retrasoLlegada);

                int idCliente = contadorClientes.getAndIncrement();
                Cliente nuevoCliente = new Cliente(idCliente);
                barberia.llegaCliente(nuevoCliente);
            }
        } finally {
            // Muy importante: señal de fin de llegadas para cierre ordenado
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

    private int generarAleatorio(int minimo, int maximo) {
        return minimo + generadorAleatorio.nextInt(maximo - minimo + 1);
    }
}
