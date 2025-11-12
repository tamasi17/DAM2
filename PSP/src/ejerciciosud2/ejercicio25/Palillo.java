package ejerciciosud2.ejercicio25;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Palillo {

    private final int idPalillo;
    private final ReentrantLock palilloLock;

    public Palillo(int idPalillo, boolean esJusto) {
        this.idPalillo = idPalillo;
        this.palilloLock = new ReentrantLock(esJusto);
    }

    public int getIdPalillo() {
        return idPalillo;
    }

    /** Intenta coger el palillo sin esperar (modo no bloqueante). */
    public boolean intentarCoger() {
        return palilloLock.tryLock();
    }

    /** Intenta coger el palillo con un tiempo máximo de espera. */
    public boolean intentarCogerConEspera(long tiempoMaximoMs) throws InterruptedException {
        return palilloLock.tryLock(tiempoMaximoMs, TimeUnit.MILLISECONDS);
    }

    /** Suelta el palillo si el hilo actual lo posee. */
    public void soltar() {
        if (palilloLock.isHeldByCurrentThread()) {
            palilloLock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Palillo " + idPalillo;
    }
}
