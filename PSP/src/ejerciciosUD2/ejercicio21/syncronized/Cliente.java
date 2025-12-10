package ejerciciosUD2.ejercicio21.syncronized;

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
