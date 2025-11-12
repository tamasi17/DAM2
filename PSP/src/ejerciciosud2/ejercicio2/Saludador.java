package ejerciciosud2.ejercicio2;

public class Saludador implements Runnable {

    private final String mensaje;

    public Saludador(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + mensaje + " (" + i + ")");
            try {
                Thread.sleep(150);
            } catch (InterruptedException ie) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
                ie.printStackTrace();
            }
        }
    }
}
