package ejerciciosUD2.ejercicio21.concurrent_semaphore;

public class Barbero implements Runnable {

    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        barberia.atenderClientes();
    }
}
