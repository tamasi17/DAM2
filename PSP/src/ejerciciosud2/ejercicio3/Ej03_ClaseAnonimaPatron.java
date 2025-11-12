package ejerciciosud2.ejercicio3;

public class Ej03_ClaseAnonimaPatron {

    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    System.out.println("###");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                        ie.printStackTrace();
                    }
                }
            }
        }, "Hilo1");

        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    System.out.println("---");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                        ie.printStackTrace();
                    }
                }
            }
        }, "Hilo2");

        hilo1.start();
        hilo2.start();
    }
}
