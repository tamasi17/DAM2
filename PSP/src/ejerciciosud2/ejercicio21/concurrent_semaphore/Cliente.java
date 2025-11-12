package ejerciciosud2.ejercicio21.concurrent_semaphore;

public class Cliente implements Runnable {

    private final int idCliente;
    private final Barberia barberia;

    public Cliente(int idCliente, Barberia barberia) {
        this.idCliente = idCliente;
        this.barberia = barberia;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public void run() {
        barberia.llegaCliente(this);
    }

    @Override
    public String toString() {
        return "Cliente " + idCliente;
    }
}
