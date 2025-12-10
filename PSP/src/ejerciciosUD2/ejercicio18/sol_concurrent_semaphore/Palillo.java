package ejerciciosUD2.ejercicio18.sol_concurrent_semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Palillo {

    private final int idPalillo;
    private final Semaphore semaforoPalillo;

    public Palillo(int idPalillo, boolean esJusto) {
        this.idPalillo = idPalillo;
        this.semaforoPalillo = new Semaphore(1, esJusto);
    }

    public int getIdPalillo() {
        return idPalillo;
    }

    /** Intenta coger el palillo sin bloquear. */
    public boolean intentarCoger() {
        return semaforoPalillo.tryAcquire();
    }

    /** Intenta coger el palillo con un tiempo máximo de espera. */
    public boolean intentarCogerConEspera(long tiempoMaximoMs) throws InterruptedException {
        return semaforoPalillo.tryAcquire(tiempoMaximoMs, TimeUnit.MILLISECONDS);
    }

    /** Suelta el palillo. */
    public void soltar() {
        semaforoPalillo.release();
    }

    @Override
    public String toString() {
        return "Palillo " + idPalillo;
    }
}
