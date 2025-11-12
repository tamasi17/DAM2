package ejerciciosud2.ejercicio29;

import java.util.Random;

public class GeneradorClientes implements Runnable {

    private final Barberia barberia;
    private final int totalClientes;
    private final Random aleatorio = new Random();

    public GeneradorClientes(Barberia barberia, int totalClientes) {
        this.barberia = barberia;
        this.totalClientes = totalClientes;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalClientes; i++) {
            Cliente cliente = new Cliente(i, barberia);
            new Thread(cliente, "Cliente-" + i).start();

            try {
                Thread.sleep(80 + aleatorio.nextInt(200)); // llegada aleatoria
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        barberia.cerrarLlegadas();
    }
}
