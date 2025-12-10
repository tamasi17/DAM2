package ejerciciosUD2.ejercicio1;

public class ContadorThread extends Thread {

    public ContadorThread(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " -> " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido " + getName());
                ie.printStackTrace();
            }

        }
    }
}
