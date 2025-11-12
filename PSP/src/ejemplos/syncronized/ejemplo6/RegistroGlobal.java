package ejemplos.syncronized.ejemplo6;

public class RegistroGlobal {

    public static synchronized void escribirLog(String mensaje) {
        System.out.print(Thread.currentThread().getName() + " -> ");
        System.out.println(mensaje);
        try { Thread.sleep(50); } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrumpido, deteniendo registro.");
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable tarea = () -> {
            for (int i = 1; i <= 5; i++) {
                escribirLog("línea " + i);
            }
        };

        Thread hiloA = new Thread(tarea, "Hilo A");
        Thread hiloB = new Thread(tarea, "Hilo B");
        Thread hiloC = new Thread(tarea, "Hilo C");

        hiloA.start();
        hiloB.start();
        hiloC.start();

        hiloA.join();
        hiloB.join();
        hiloC.join();
    }
}
