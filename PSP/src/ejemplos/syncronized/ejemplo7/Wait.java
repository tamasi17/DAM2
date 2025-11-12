package ejemplos.syncronized.ejemplo7;

public class Wait {

    public static void main(String[] args) {
        ObjetoCompartido recurso = new ObjetoCompartido();

        Thread hiloEsperando = new Thread(new HiloEsperando(recurso), "Hilo Esperando");
        hiloEsperando.start();

        try {
            Thread.sleep(1000); // dar tiempo a que el hilo entre en wait()
        } catch (InterruptedException ignored) {}

        System.out.println("Main: el hilo principal termina sin despertar al otro.");
    }
}

class ObjetoCompartido {
    // nada especial, solo sirve como monitor
}

class HiloEsperando implements Runnable {
    private final ObjetoCompartido recurso;

    public HiloEsperando(ObjetoCompartido recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        synchronized (recurso) {
            System.out.println(Thread.currentThread().getName() + " entra en la sección crítica y llama a wait()");
            try {
                recurso.wait(); // se bloquea indefinidamente
                System.out.println("Esto no se imprimirá hasta que alguien haga notify()");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido; saliendo.");
            }
        }
    }
}
