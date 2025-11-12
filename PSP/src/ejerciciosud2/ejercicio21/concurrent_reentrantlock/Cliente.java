package ejerciciosud2.ejercicio21.concurrent_reentrantlock;

public class Cliente {
    private final int idCliente;

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "Cliente " + idCliente;
    }
}
