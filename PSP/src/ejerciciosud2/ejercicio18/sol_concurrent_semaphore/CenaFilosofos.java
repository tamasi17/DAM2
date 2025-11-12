package ejerciciosud2.ejercicio18.sol_concurrent_semaphore;

public class CenaFilosofos {

    private static final int NUMERO_FILOSOFOS = 5;
    private static final int CICLOS_POR_FILOSOFO = 6;
    private static final boolean SEMAFOROS_JUSTOS = true;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(">>> Cena de los Filósofos (Semáforos, versión clean y ordenada) <<<\n");

        Palillo[] palillos = new Palillo[NUMERO_FILOSOFOS];
        for (int i = 0; i < NUMERO_FILOSOFOS; i++) {
            palillos[i] = new Palillo(i, SEMAFOROS_JUSTOS);
        }

        // Mostrar configuración inicial de la mesa
        for (int i = 0; i < NUMERO_FILOSOFOS; i++) {
            Palillo palilloIzquierdo = palillos[i];
            Palillo palilloDerecho  = palillos[(i + 1) % NUMERO_FILOSOFOS];
            System.out.println("Filósofo " + (i + 1));
            System.out.println("  Palillo izquierdo: " + palilloIzquierdo.getIdPalillo());
            System.out.println("  Palillo derecho: " + palilloDerecho.getIdPalillo());
        }
        System.out.println();

        // Crear hilos
        Thread[] hilosFilosofo = new Thread[NUMERO_FILOSOFOS];

        for (int i = 0; i < NUMERO_FILOSOFOS; i++) {
            Palillo palilloIzquierdo = palillos[i];
            Palillo palilloDerecho  = palillos[(i + 1) % NUMERO_FILOSOFOS];
            Filosofo filosofo = new Filosofo(i + 1, palilloIzquierdo, palilloDerecho, CICLOS_POR_FILOSOFO);
            hilosFilosofo[i] = new Thread(filosofo, "Filosofo-" + (i + 1));
        }

        // Lanzar hilos
        for (Thread hilo : hilosFilosofo) {
            hilo.start();
        }

        // Esperar a que terminen
        for (Thread hilo : hilosFilosofo) {
            hilo.join();
        }

        System.out.println("\n>>> Fin de la cena <<<");
    }
}
