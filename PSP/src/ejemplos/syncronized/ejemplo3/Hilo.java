package ejemplos.syncronized.ejemplo3;

import java.util.Random;

// Clase que representa un hilo de ejecución
// Implementa Runnable para definir qué hará el hilo cuando se ejecute
class Hilo implements Runnable {
    private final String nombre;

    Hilo(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.printf("Hola, soy el hilo: %s.%n", this.nombre);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            // Generamos un tiempo de pausa aleatorio entre 10 y 500 ms
            int pausa = 10 + random.nextInt(500 - 10);

            System.out.printf("Hilo: %s hace pausa de %d ms.%n", this.nombre, pausa);

            try {
                // Simulamos trabajo: el hilo “duerme” durante la pausa
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido durante el sleep, se muestra el aviso
                System.out.printf("Hilo %s interrumpido.%n", this.nombre);
            }
        }

        System.out.printf("Hilo %s terminado.%n", this.nombre);
    }
}
