package ejerciciosUD2_Hilos;

public class Ej2_HiloClaseRunnable implements Runnable {


    /*
    Implementa la interfaz Runnable en una clase.
    El hilo debe mostrar un mensaje de texto 5 veces, con 150 ms de pausa entre cada impresión.
    Lanza dos hilos con mensajes diferentes.

     */

    String message;

    public Ej2_HiloClaseRunnable(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    static void main() {
        Runnable runnable1 = new Ej2_HiloClaseRunnable("Mensaje 1");
        Runnable runnable2 = new Ej2_HiloClaseRunnable("Mensaje 2");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
