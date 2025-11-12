package ejerciciosud2.ejercicio12.contadorsincronizado.sinsincronizar;

public class ContadorNoSincronizado {

    public static void main(String[] args) throws InterruptedException {
        final int HILOS = 5;
        final int INCREMENTOS_POR_HILO = 1000;

        Contador contador = new Contador(); // sin sincronizar

        Thread[] hilos = new Thread[HILOS];
        for (int i = 0; i < HILOS; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTOS_POR_HILO; j++) {
                    contador.incrementar(); // ++ no atómico
                }
            });
            hilos[i].start();
        }

        // Esperar a que terminen todos
        for (Thread hilo : hilos ) {
            hilo.join();
        }

        int esperado = HILOS * INCREMENTOS_POR_HILO;
        System.out.println("Esperado: " + esperado);
        System.out.println("Obtenido (NO sincronizado): " + contador.getValor());
    }
}


class Contador {

    private int valor = 0;

    public void incrementar() {
        // valor++ es equivalente a: leer (valor), sumar 1, escribir — no atómico
        valor++;
    }

    public int getValor() {
        return valor;
    }
}
