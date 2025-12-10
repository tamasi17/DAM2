package ejerciciosUD2.ejercicio29;

public class MainBarberoDormilon {

    private static final int SILLAS_ESPERA = 5;
    private static final int TOTAL_CLIENTES = 25;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(">>> Simulación (Semáforos): Barbero Dormilón <<<");
        System.out.println("Sillas de espera: " + SILLAS_ESPERA +
                " | Clientes totales: " + TOTAL_CLIENTES + "\n");

        Barberia barberia = new Barberia(SILLAS_ESPERA);

        Thread hiloBarbero = new Thread(new Barbero(barberia), "Barbero");
        Thread generador = new Thread(new GeneradorClientes(barberia, TOTAL_CLIENTES), "Generador");

        hiloBarbero.start();
        generador.start();

        generador.join();
        hiloBarbero.join();

        System.out.println("\n>>> Fin de la simulación con semáforos <<<");
    }
}
