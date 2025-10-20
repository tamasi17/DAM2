package ejerciciosUD2_Hilos;

public class Ej1_HiloClaseThread extends Thread {

    /*
    Crea una clase que extienda la clase Thread.
    En su método run(), debe imprimir su nombre y un contador del 1 al 5,
    con una pausa de 200 ms entre cada impresión.
    Lanza el hilo desde el método main().
     */

    @Override
    public void run() {
        System.out.println(this.getName());
        for (int i = 0; i < 5; i++) {
            System.out.println(i+1);
        }
    }

    static void main() {
        Thread claseThread = new Ej1_HiloClaseThread();

        claseThread.start();
    }
}
