package ejemplos.syncronized.ejemplo3;

// Clase principal que lanza los hilos y espera a que terminen
public class LanzaHilosYEsperaQueTerminen {
    public static void main(String[] args) {
        // Creamos dos hilos (h1 y h2) a partir de la clase Hilo
        Thread h1 = new Thread(new Hilo("H1"));
        Thread h2 = new Thread(new Hilo("H2"));

        // Arrancamos los hilos: a partir de aquí se ejecutan en paralelo al main
        h1.start();
        h2.start();

        try {
            // El main espera a que h1 y h2 terminen su ejecución
            // join() hace que el hilo principal se bloquee hasta que el otro hilo acabe
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            // Si el hilo principal es interrumpido mientras espera
            System.out.println("Hilo principal interrumpido.");
        }

        System.out.println("Hilo principal terminado.");
    }
}
