package ejemplos.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo2_Reentrante {

    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Ejemplo2_Reentrante().metodoA();
    }

    public void metodoA() {
        lock.lock();
        try {
            System.out.println("Entrando en metodoA");
            metodoB();
        } finally {
            lock.unlock();
        }
    }

    public void metodoB() {
        lock.lock();
        try {
            System.out.println("Entrando en metodoB (reentrancia)");
        } finally {
            lock.unlock();
        }
    }
}
