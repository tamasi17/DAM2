package ejerciciosUD2_Mati.primerasPruebasHilos;

public class HiloRunnable implements Runnable{

    //2. Hilo muestra mensaje cinco veces, esperando un segundo cada vez. implements Runnable


    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Segundo thread implementa Runnable");
        }
    }

}
