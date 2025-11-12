package ejemplos.concurrent.Semaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Ejemplo3_BarraDiscoteca {

    private static final int CAPACIDAD_BARRA = 5;
    private static final Queue<String> barra = new LinkedList<>();

    private static final Semaphore espaciosLibres = new Semaphore(CAPACIDAD_BARRA);
    private static final Semaphore bebidasDisponibles = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1); // exclusión mutua

    public static void main(String[] args) {
        new Thread(new Camarero()).start();
        new Thread(new Cliente()).start();
    }

    static class Camarero implements Runnable {
        private int contador = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    espaciosLibres.acquire(); // espera si la barra está llena
                    mutex.acquire();
                    String bebida = "Copa " + (++contador);
                    barra.add(bebida);
                    System.out.println("Camarero prepara " + bebida);
                    mutex.release();
                    bebidasDisponibles.release(); // hay una nueva bebida
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Cliente implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    bebidasDisponibles.acquire(); // espera una bebida
                    mutex.acquire();
                    String bebida = barra.poll();
                    System.out.println("Cliente recoge " + bebida);
                    mutex.release();
                    espaciosLibres.release(); // deja sitio libre en la barra
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
