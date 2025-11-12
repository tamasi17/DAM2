package ejemplos.syncronized.ejemplo5;

public class Ejemplo5_SincronizadoObjeto {

    private static int contadorCompartido = 0;
    private static final Object candado = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable tareaIncremento = () -> {
            for (int i = 0; i < 200; i++) {
                synchronized (candado) {
                    int copia = contadorCompartido;
                    copia = copia + 1;
                    contadorCompartido = copia;
                }
            }
        };

        Thread hilo1 = new Thread(tareaIncremento, "Hilo 1");
        Thread hilo2 = new Thread(tareaIncremento, "Hilo 2");

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();

        int esperado = 400_000;
        System.out.println("Esperado = " + esperado + " | Real = " + contadorCompartido);
    }
}
