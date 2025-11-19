package ejerciciosud2;

import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWrite {


    public static ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
    public static Lock leer = rrw.readLock();
    public static Lock escribir = rrw.writeLock();
    public static String texto;

    static void main() {

        Phaser phaser = new Phaser(1);
        for (int i = 0; i < 10; i++) {
            phaser.register();
            THread(new Lectores).start();
        }

    }
}
