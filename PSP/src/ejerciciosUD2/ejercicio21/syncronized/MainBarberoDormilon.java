package ejerciciosUD2.ejercicio21.syncronized;

public class MainBarberoDormilon {

    private static final int NUMERO_SILLAS_ESPERA = 5;
    private static final int TOTAL_CLIENTES_A_GENERAR = 25; // ajusta libremente

    public static void main(String[] args) throws InterruptedException {
        System.out.println(">>> Simulación: Problema del Barbero Dormilón <<<");
        System.out.println("Sillas de espera: " + NUMERO_SILLAS_ESPERA +
                " | Clientes a generar: " + TOTAL_CLIENTES_A_GENERAR + "\n");

        // NUEVO: Barbería sin parámetro “objetivo de atendidos”
        Barberia barberia = new Barberia(NUMERO_SILLAS_ESPERA);

        Thread hiloBarbero   = new Thread(new Barbero(barberia), "Hilo-Barbero");
        Thread hiloGenerador = new Thread(new GeneradorClientes(barberia, TOTAL_CLIENTES_A_GENERAR), "Hilo-Generador");

        hiloBarbero.start();
        hiloGenerador.start();

        hiloGenerador.join();
        hiloBarbero.join();

        System.out.println("\n>>> Fin de la simulación del Barbero Dormilón <<<");
    }
}
