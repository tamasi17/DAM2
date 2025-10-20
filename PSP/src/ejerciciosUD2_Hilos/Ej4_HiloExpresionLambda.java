package ejerciciosUD2_Hilos;

public class Ej4_HiloExpresionLambda {

    /*
    Crea un hilo usando una expresión lambda
    (ya que Runnable es una interfaz funcional).
    El hilo debe contar de 5 a 1 con una pausa de 120 ms entre números,
    mostrando el nombre del hilo en cada impresión.
     */

    static void main() {
        Thread thread1 = new Thread(()->{
            for (int i = 5; i >= 1; i--) {
                System.out.println("Hilo: " + i);
            }
        });

        thread1.start();
    }

}
