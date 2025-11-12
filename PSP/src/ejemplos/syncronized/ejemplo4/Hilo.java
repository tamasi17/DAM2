package ejemplos.syncronized.ejemplo4;

// Clase Hilo: trabajo que realizará cada Thread
class Hilo implements Runnable {
    int numHilo;      // identificador del hilo (0,1,2...)
    int miParte;      // cuántas veces le toca incrementar
    int miCuenta = 0; // cuántas operaciones ha hecho este hilo
    private final Contador cont; // referencia al contador compartido

    public int getMiCuenta() {
        return miCuenta;
    }

    // Constructor: asigna número de hilo, cuántas operaciones hará y el contador compartido
    Hilo(int numHilo, int miParte, Contador contador) {
        this.numHilo = numHilo;
        this.miParte = miParte;
        this.cont = contador;
    }

    @Override
    public void run() {
        // Cada hilo incrementa el contador tantas veces como se le asignó
        for (int i = 0; i < miParte; i++) {
            this.cont.incrementa(); // acceso al recurso compartido
            miCuenta++;             // cuenta local de este hilo
        }
        // Mensaje final de cada hilo
        System.out.printf("Hilo %d terminado, cuenta local: %d%n",
                numHilo, getMiCuenta());
    }


}
