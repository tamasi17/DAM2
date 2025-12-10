package ejerciciosUD2.ejercicio16;

import java.time.Duration;
import java.time.Instant;

public class CuentaParesConcurrenteClasico {

    static int totalPares = 0; // contador compartido

    // Método sincronizado para actualizar el total sin condiciones de carrera
    public static synchronized void agregarPares(int n) {
        totalPares += n;
    }

    // Clase interna que representa una tarea de conteo
    static class ContadorPares extends Thread {
        private final int inicio;
        private final int fin;

        public ContadorPares(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public void run() {
            int contador = 0;
            for (int i = inicio; i <= fin; i++) {
                if (i % 2 == 0) {
                    contador++;
                }
            }
            agregarPares(contador); // añadimos al total global
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int x = 1;
        int y = 100_000_000;
        int rango = 20_000_000;

        Instant inicio = Instant.now();

        // Crear los 5 hilos
        ContadorPares[] hilos = new ContadorPares[5];
        int inicioRango = x;

        for (int i = 0; i < hilos.length; i++) {
            int finRango = inicioRango + rango - 1;
            hilos[i] = new ContadorPares(inicioRango, finRango);
            inicioRango = finRango + 1;
        }

        // Iniciar los hilos
        for (ContadorPares hilo : hilos) {
            hilo.start();
        }

        // Esperar a que todos terminen
        for (ContadorPares hilo : hilos) {
            hilo.join();
        }

        Instant fin = Instant.now();
        long tiempo = Duration.between(inicio, fin).toMillis();

        System.out.println("Número de pares en el intervalo: " + x + " - " + y +
                " es igual a " + totalPares +
                " calculado en " + tiempo + " milisegundos");
    }
}
