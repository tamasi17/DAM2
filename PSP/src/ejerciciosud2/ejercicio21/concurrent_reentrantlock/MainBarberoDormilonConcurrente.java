package ejerciciosud2.ejercicio21.concurrent_reentrantlock;

public class MainBarberoDormilonConcurrente {

    private static final int NUMERO_SILLAS_ESPERA = 5;
    private static final int TOTAL_CLIENTES_A_GENERAR = 25;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(">>> Simulación (ReentrantLock/Condition): Barbero Dormilón <<<");
        System.out.println("Sillas de espera: " + NUMERO_SILLAS_ESPERA +
                " | Clientes a generar: " + TOTAL_CLIENTES_A_GENERAR + "\n");

        BarberiaConcurrente barberia = new BarberiaConcurrente(NUMERO_SILLAS_ESPERA);

        Thread hiloBarbero   = new Thread(new BarberoConcurrente(barberia), "Hilo-Barbero");
        Thread hiloGenerador = new Thread(new GeneradorClientesConcurrente(barberia, TOTAL_CLIENTES_A_GENERAR), "Hilo-Generador");

        hiloBarbero.start();
        hiloGenerador.start();

        hiloGenerador.join();
        hiloBarbero.join();

        // Informe final para el alumnado
        barberia.imprimirResumen();

        System.out.println("\n>>> Fin de la simulación concurrente <<<");
    }
}
