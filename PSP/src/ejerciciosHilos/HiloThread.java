package ejerciciosHilos;

        /*
        1. Hilo muestra mensaje cinco veces, esperando un segundo cada vez. extends Thread

         */

public class HiloThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Primer thread hereda de Thread");
        }
    }
}
