package ejerciciosUD2.ejercicio21.syncronized;

import java.util.Random;

public class Barbero implements Runnable {

    private final Barberia barberia;
    private final Random generadorAleatorio = new Random();
    private static final int TIEMPO_CORTE_MINIMO_MS = 300;
    private static final int TIEMPO_CORTE_MAXIMO_MS = 900;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Cliente clienteEnAtencion = barberia.obtenerSiguienteCliente();
            if (clienteEnAtencion == null) {
                System.out.println("[BARBERO] No quedan clientes. Cierra la barbería.");
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

    private int generarAleatorio(int minimo, int maximo) {
        return minimo + generadorAleatorio.nextInt(maximo - minimo + 1);
    }
}
