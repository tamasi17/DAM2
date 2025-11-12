package ejerciciosud2.ejercicio4;

public class Ej04_LambdaCuentaAtras {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for (int i = 5; i >= 1; i--) {
                System.out.println(Thread.currentThread().getName() + " -> " + i);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException ie) {
                    System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                    ie.printStackTrace();
                }
            }
        }, "Lambda");

        t.start();
    }
}
