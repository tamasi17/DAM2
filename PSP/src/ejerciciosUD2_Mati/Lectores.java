package ejerciciosUD2_Mati;

import java.util.concurrent.Phaser;

public class Lectores implements Runnable{
    private Phaser phaser;

    public Lectores(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        ReadWrite.leer.lock();
        phaser.arriveAndAwaitAdvance();
    }
}
