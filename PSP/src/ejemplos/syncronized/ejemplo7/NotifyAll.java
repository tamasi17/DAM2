package ejemplos.syncronized.ejemplo7;

public class NotifyAll {

    public static void main(String[] args) {
        final Object monitor = new Object();

        // Tres hilos que esperarán
        for (int i = 1; i <= 3; i++) {
            Thread hiloEsperador = new Thread(new EsperadorMultiple(monitor), "Esperador " + i);
            hiloEsperador.start();
        }

        // Pequeña pausa para que todos entren en wait()
        try { Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // Un hilo notificador que despierta a todos
        Thread hiloNotificador = new Thread(new NotificadorMultiple(monitor), "Notificador");
        hiloNotificador.start();
    }
}

class EsperadorMultiple implements Runnable {
    private final Object monitor;

    public EsperadorMultiple(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + " esperando...");
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido.");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " reanudado tras notifyAll()");
        }
    }
}

class NotificadorMultiple implements Runnable {
    private final Object monitor;

    public NotificadorMultiple(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + " llama a notifyAll()");
            monitor.notifyAll(); // despierta a TODOS los hilos esperando
            System.out.println(Thread.currentThread().getName() + " termina.");
        }
    }
}
