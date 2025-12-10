package ejerciciosUD2.ejercicio34;

import java.util.concurrent.CyclicBarrier;

public class MainCyclicBarrier {
    public static void main(String[] args) {
        final int CORREDORES = 5;

        // Acción que se ejecutará una vez que todos lleguen a la barrera
        Runnable inicioCarrera = () -> System.out.println("\n¡Todos listos! ¡La carrera comienza!\n");

        CyclicBarrier barrera = new CyclicBarrier(CORREDORES, inicioCarrera);

        for (int i = 1; i <= CORREDORES; i++) {
            new Thread(new Corredor(i, barrera)).start();
        }
    }
}
