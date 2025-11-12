package ejerciciosud2.ejercicio33;

public class HiloEsperador implements Runnable {

    private final EstadoVolatile estado;

    public HiloEsperador(EstadoVolatile estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        System.out.println("El hilo esperador está esperando a que 'activo' sea true...");
        while (!estado.activo) {
            // bucle activo: espera hasta ver el cambio de valor
        }
        System.out.println("El hilo esperador detectó que 'activo' es true y finaliza.");
    }
}
