package ejerciciosud2.ejercicio29;

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
