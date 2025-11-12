package ejemplos.syncronized.ejemplo5;

public class Ejemplo5_MetodoEstaticoSincronizado {

    private static int contadorGlobal = 0; // compartido por todos los hilos

    // método estático sincronizado: usa el monitor de Ejemplo5_MetodoEstaticoSincronizado.class
    public synchronized static void incrementar() {
        int copia = contadorGlobal;
        copia = copia + 1;
        contadorGlobal = copia;

        System.out.println(Thread.currentThread().getName() +
                           " incrementa contadorGlobal a " + contadorGlobal);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable tareaIncremento = () -> {
            for (int i = 0; i < 5; i++) {
                incrementar();
                try { Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println(Thread.currentThread().getName() + " interrumpido, deteniendo tarea...");
                    break;
                }
            }
        };

        Thread hilo1 = new Thread(tareaIncremento, "Hilo 1");
        Thread hilo2 = new Thread(tareaIncremento, "Hilo 2");
        Thread hilo3 = new Thread(tareaIncremento, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        int esperado = 3 * 5; // 15 incrementos en total
        System.out.println("Esperado = " + esperado + " | Real = " + contadorGlobal);
    }
}
