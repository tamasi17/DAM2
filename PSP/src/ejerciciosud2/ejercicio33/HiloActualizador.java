package ejerciciosud2.ejercicio33;

public class HiloActualizador implements Runnable {

    private final EstadoVolatile estado;

    public HiloActualizador(EstadoVolatile estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000); // espera 2 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        estado.activo = true;
        System.out.println("El hilo actualizador ha cambiado 'activo' a true.");
    }
}
