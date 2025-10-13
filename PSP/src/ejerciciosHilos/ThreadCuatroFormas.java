package ejerciciosHilos;

public class ThreadCuatroFormas {
    static void main() {

        /*
        run() en el main lanza el metodo desde main. no sirve pa na.
        no levanta ningun hilo.

        IMPORTANTE
        levantamos hilos con start() que llama al run() de cada hilo !!!!!
         */


        Thread thread1 = new HiloThread();
        thread1.start();

        Thread thread2 = new Thread(new HiloRunnable());
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                System.out.println("Hilo hecho con clase anonima");
                }
            }
        });
        thread3.start();


        ///  ##LAMBDAS
        /*
        Runnable es funcional. Solo tiene un metodo.
        Al llamar a () sabe que llamamos a su constructor.
        Al llamar a {} sabe que llamamos a su unico metodo.
        Dentro de {} podemos escribir el funcionamiento del metodo.
        Asi que:
                InterfazFuncional nombre = () -> { lo que hace el metodo };
         */
        Runnable runnable = () -> {
            for (int i = 0; i < 3; i++) {
            System.out.println("Hilo con lambda");
            }
        };
        Thread thread4 = new Thread(runnable);
        thread4.start();


        Thread thread5 = new Thread( () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Hilo con lambda dos");
            }
        });
        thread5.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Active count: " + Thread.activeCount());

    }
}
