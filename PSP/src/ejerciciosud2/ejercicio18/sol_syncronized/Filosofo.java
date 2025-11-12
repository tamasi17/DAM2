package ejerciciosud2.ejercicio18.sol_syncronized;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Filosofo implements Runnable {

    // Formato de hora actual (hh:mm:ss)
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Object palilloIzquierdo;
    private final Object palilloDerecho;

    // Generador de números aleatorios compartido por todos los hilos
    private static final Random random = new Random();

    // Constructor que recibe los dos palillos (objetos compartidos entre filósofos)
    Filosofo(Object left, Object right) {
        this.palilloIzquierdo = left;
        this.palilloDerecho = right;
    }

    // Método auxiliar para mostrar la acción y pausar el hilo un tiempo aleatorio
    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((ThreadLocalRandom.current().nextInt(1500,3000)+ 1));// Espera aleatoria entre 1500 y 3000 ms
    }

    @Override
    public void run() {
        try {


            while (true) {

                String horaActual = LocalTime.now().format(formatter);
                doAction(horaActual + ": Pensando");

                // Sección crítica: coge primero el palillo izquierdo
                synchronized (palilloIzquierdo) {
                    horaActual = LocalTime.now().format(formatter);
                    doAction(horaActual + ": Coge el palillo izquierdo");

                    // Luego intenta coger el derecho
                    synchronized (palilloDerecho) {
                        horaActual = LocalTime.now().format(formatter);
                        doAction(horaActual + ": Coge el palillo derecho (comiendo)");

                        horaActual = LocalTime.now().format(formatter);
                        doAction(horaActual + ": Deja el palillo derecho");
                    }

                    horaActual = LocalTime.now().format(formatter);
                    doAction(horaActual + ": Deja el palillo izquierdo. Se pone a pensar.");
                }
            }

        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt(); // Mantiene el estado de interrupción del hilo
        }
    }
}
