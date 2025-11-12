package ejerciciosud2.ejercicio26;

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
