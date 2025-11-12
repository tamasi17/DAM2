package ejerciciosud2.ejercicio5;

public class Tarea implements Runnable {

    private final String texto;
    private final int pausa;

    public Tarea(String texto, int pausa) {
        this.texto = texto;
        this.pausa = pausa;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(texto);

            try {
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
            }
        }
    }
}
