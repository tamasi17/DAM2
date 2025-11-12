package ejerciciosUD2_Hilos;

public class Ej6_UsoJoin {

    static void main() {


        // LAMBDA ENTERA
        Thread hilo1 = new Thread(() -> {
            try{
            System.out.println("Iniciando 1");
            Thread.sleep(30);
            System.out.println("Procesando 2");
            Thread.sleep(10);
            System.out.println("Terminando 3");
            Thread.sleep(50);
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }

        }, "Hilo 1");

        // RUNNABLE ANONIMA
        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Iniciando 2");
                    Thread.sleep(10);
                    System.out.println("Procesando 2");
                    Thread.sleep(50);
                    System.out.println("Terminando 2");
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                }
            }
        }, "Hilo 2");

        // LAMBDA LLAMA A METODO STATIC
        Thread hilo3 = new Thread(() -> trabajar(60,20,10));

        // RUNNABLE LLAMA A METODO STATIC
        Runnable r = () -> trabajar(10,10,10);
        Thread hilo4 = new Thread(r);


        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            System.out.println("Join con todos los hilos");
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hilos finalizados");
    }

    private static void trabajar(int millis, int millis1, int millis2) {
        try {
            System.out.println("Iniciando 3 o 4");
            Thread.sleep(millis);
            System.out.println("Procesando 3 o 4");
            Thread.sleep(millis1);
            System.out.println("Terminando 3 o 4");
            Thread.sleep(millis2);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}
