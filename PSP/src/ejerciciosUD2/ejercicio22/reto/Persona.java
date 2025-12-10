package ejerciciosUD2.ejercicio22.reto;


import java.util.concurrent.ThreadLocalRandom;

public class Persona implements Runnable {

    private final Puente puente;

    private final String idPersona;
    private final int peso;
    private final int tMinPaso, tMaxPaso;

    public int getPeso() {
        return peso;
    }

    public Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP) {
        this.puente = puente;
        this.peso = peso;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.idPersona = idP;
    }

    @Override
    public void run() {


        boolean autorizado = false;
        while (!autorizado) {
            synchronized (this.puente) {
                autorizado = this.puente.autorizacionPaso(this);
                if (!autorizado) {
                    try {
                        this.puente.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }


        ThreadLocalRandom random = ThreadLocalRandom.current();
        int tiempoPaso = this.tMinPaso + random.nextInt(this.tMaxPaso - this.tMinPaso + 1);

        try {

            Thread.sleep(1000 * tiempoPaso);

        } catch (InterruptedException ie) {

        }

        synchronized (this.puente) {
            this.puente.terminaPaso(this);

            puente.notifyAll();
        }
    }
}
