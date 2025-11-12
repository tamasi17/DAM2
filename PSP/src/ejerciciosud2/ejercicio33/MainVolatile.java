package ejerciciosud2.ejercicio33;

public class MainVolatile {

    public static void main(String[] args) {

        EstadoVolatile estado = new EstadoVolatile();

        Thread actualizador = new Thread(new HiloActualizador(estado));
        Thread esperador = new Thread(new HiloEsperador(estado));

        esperador.start();
        actualizador.start();
    }
}
