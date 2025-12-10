package ejerciciosUD2.ejercicio18.sol_syncronized;

public class CenaFilosofos {

    public static void main(String[] args) throws Exception {

        Filosofo[] filosofos = new Filosofo[5];
        Object[] palillos = new Object[filosofos.length];

        // Se crean los 5 palillos (recursos compartidos)
        for (int i = 0; i < palillos.length; i++) {
            palillos[i] = new Object();
        }

        // Se crean los filósofos y se les asignan sus palillos
        for (int i = 0; i < filosofos.length; i++) {

            Object palilloIzquierdo = palillos[i];
            Object palilloDerecho = palillos[(i + 1) % palillos.length];

            System.out.println("Filósofo " + (i + 1));
            System.out.println("  Palillo izquierdo: " + i);
            System.out.println("  Palillo derecho: " + ((i + 1) % 5));

            // El último filósofo invierte el orden para evitar el deadlock
            if (i == filosofos.length - 1) {
                filosofos[i] = new Filosofo(palilloDerecho, palilloIzquierdo);
            } else {
                filosofos[i] = new Filosofo(palilloIzquierdo, palilloDerecho);
            }

            // Se crea y lanza el hilo asociado a cada filósofo
            Thread hiloFilosofo = new Thread(filosofos[i], "Filósofo " + (i + 1));
            hiloFilosofo.start();
        }
    }
}
