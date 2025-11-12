package ejemplos.syncronized.ejemplo5;

public class Ejemplo5_MetodoSincronizadoInstancia {

    private int contador = 0;

    public synchronized void incrementar() {
        int copia = contador;
        copia = copia + 1;
        contador = copia;
    }

    public static void main(String[] args) throws InterruptedException {

        Ejemplo5_MetodoSincronizadoInstancia recursoCompartido = new Ejemplo5_MetodoSincronizadoInstancia();

        Runnable tareaIncremento = () -> {
            for (int i = 0; i < 200_000; i++) {
                recursoCompartido.incrementar();
            }
        };

        Thread hilo1 = new Thread(tareaIncremento, "Hilo 1");
        Thread hilo2 = new Thread(tareaIncremento, "Hilo 2");
        hilo1.start(); hilo2.start();
        hilo1.join();  hilo2.join();

        int esperado = 2 * 200_000; // = 400000
        System.out.println("Esperado = " + esperado + " | Real = " + recursoCompartido.contador);
    }
}
