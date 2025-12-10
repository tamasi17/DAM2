package ejerciciosUD2_Mati;

public class Ej3_HiloClaseAnonima {

    /*
    Crea dos hilos mediante clases anónimas que impriman patrones distintos en pantalla
    (por ejemplo, ### y ---).
    Cada hilo repetirá su impresión 4 veces con pausas de 100 ms.
    Observa cómo se entremezclan en la salida.
     */

    static void main() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                System.out.println("#####");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                System.out.println("~~~~");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    thread1.start();
    thread2.start();

    }
}
