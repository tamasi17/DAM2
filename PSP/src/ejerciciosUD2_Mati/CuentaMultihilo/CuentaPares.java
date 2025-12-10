package ejerciciosUD2_Mati.CuentaMultihilo;

import java.time.Duration;
import java.time.Instant;

public class CuentaPares {

    public static void main(String[] args) {
        int n = 0;
        int x = 1;
        int y = 100000000;

        // Marcamos el instante inicial
        Instant inicio = Instant.now();


        int hilos=20;

        Contador hilo = null;

        for (int i = 0; i < hilos ; i++) {
            hilo = new Contador(hilos,i);
            hilo.start();
        }



/*
        for (int i = x; i <= y; i++) {
            if (i % 2 == 0) {
                n++;
                System.out.println(i + " es par");
            }
        }

 */

        // Marcamos el instante final
        Instant fin = Instant.now();

        // Calculamos la duración entre ambos instantes
        Duration duracion = Duration.between(inicio, fin);

        System.out.println("Número de pares en el intervalo: " + hilo.getX() + " - " + hilo.getY() +
                " es igual a " + Contador.pares +
                " calculado en " + duracion.toMillis() + " milisegundos");
/*

        System.out.println("Número de pares en el intervalo: " + x + " - " + y +
                " es igual a " + n +
                " calculado en " + duracion.toMillis() + " milisegundos");
 */
    }

    public Contador getHilo(int hilos, int indice){
        return new Contador(hilos,indice);
    }


}