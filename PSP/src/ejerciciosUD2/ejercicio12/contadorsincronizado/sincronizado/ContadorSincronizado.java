package ejerciciosUD2.ejercicio12.contadorsincronizado.sincronizado;

public class ContadorSincronizado {

    public static void main(String[] args) throws InterruptedException {

        final int HILOS = 5;
        final int INCREMENTOS_POR_HILO = 1000;

        ContadorSeguro contador = new ContadorSeguro(); // sincronizado

        Thread[] hilos = new Thread[HILOS];

        for (int i = 0; i < HILOS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTOS_POR_HILO; j++) {
                    contador.incrementar(); // sección crítica
                }
            });
            hilos[i].start();
        }

        for (Thread hilo : hilos){
            hilo.join();
        }

        int esperado = HILOS * INCREMENTOS_POR_HILO;
        System.out.println("Esperado: " + esperado);
        System.out.println("Obtenido (SINCRONIZADO): " + contador.getValor());
    }
}

// Contador CON sincronización
class ContadorSeguro {
    private int valor = 0;

    // Opción A: sincronizar el método completo
    public synchronized void incrementar() {
        valor++;
    }

    // Opción A: sincronizar getter si necesitas consistencia fuerte
    public synchronized int getValor() {
        return valor;
    }

    /* 
    Opción B (equivalente): 
    public void incrementar() {
        synchronized (this) { valor++; }
    }
    public int getValor() {
        synchronized (this) { return valor; }
    }
    */
}
