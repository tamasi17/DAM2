package ejemplos.syncronized.ejemplo7;

public class Notify {

    public static void main(String[] args) {
        final Object monitor = new Object();

        Thread hiloEsperando = new Thread(new Esperador(monitor), "Esperador");
        Thread hiloNotificador = new Thread(new Notificador(monitor), "Notificador");

        hiloEsperando.start();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {} // dejar que espere
        hiloNotificador.start();
    }
}

class Esperador implements Runnable {
    private final Object monitor;

    public Esperador(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + " esperando...");
            try {
                monitor.wait(); // se libera el candado
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido.");
            }
            System.out.println(Thread.currentThread().getName() + " reanudado tras notify()");
        }
    }
}

class Notificador implements Runnable {
    private final Object monitor;

    public Notificador(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + " realiza notify()");
            monitor.notify(); // despierta a UN hilo que esté esperando
            System.out.println(Thread.currentThread().getName() + " termina.");
        }
    }
}
