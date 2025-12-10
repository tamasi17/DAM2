package ejerciciosUD2.ejercicio21.concurrent_reentrantlock;

import java.util.Random;

public class BarberoConcurrente implements Runnable {

    private final BarberiaConcurrente barberia;
    private final Random generadorAleatorio = new Random();

    private static final int TIEMPO_CORTE_MINIMO_MS = 300;
    private static final int TIEMPO_CORTE_MAXIMO_MS = 900;

    public BarberoConcurrente(BarberiaConcurrente barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Cliente clienteEnAtencion = barberia.obtenerSiguienteCliente();
            if (clienteEnAtencion == null) {
                System.out.println("[BARBERO] No quedarán más clientes. Fin de la jornada.");
                break;
            }

            int tiempoDeCorte = generarAleatorio(TIEMPO_CORTE_MINIMO_MS, TIEMPO_CORTE_MAXIMO_MS);
            System.out.println("[BARBERO] Cortando el pelo a " + clienteEnAtencion +
                    " (" + tiempoDeCorte + " ms).");
            dormir(tiempoDeCorte);

            barberia.finalizarCorte(clienteEnAtencion);
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
