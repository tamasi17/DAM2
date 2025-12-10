package ejerciciosUD2_Mati.repasoProductorConsumidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjercicioProdConsumidor {

    /**
     * Contenedor de sidra, un recolector (min) y tres marcas (min un hilo cada una)
     * El Gaitero: 30.000 manzanas
     * Ladron de manzanas: 20.000
     * Sidra Trabanco: 10.000
     * Capacidad 50.000
     * Cuando recolectores llenen contenedor, avisan a marcas.
     * Cada marca intentará recoger su cantidad de manzanas:
     *  Si hay suficientes, recogen y descuentan del total
     *  Si no, la marca muestra un mensaje indicando que no puede, y no retirará ninguna manzana.
     *
     *  Cuando contenedor quede vacio, recolectores volveran a llenarlo y el ciclo se repetira.
     */

    public static final ContenedorSidra CONTENEDOR = new ContenedorSidra();
    public static int MAX_CICLOS = 5;
    public static int CANTIDAD_RECOLECTADAS = 50000;

    static void main() {

        Productor recolector = new Productor(CONTENEDOR, CANTIDAD_RECOLECTADAS);
        for (int i = 0; i < MAX_CICLOS; i++) {
            Thread hiloRecolector = new Thread(recolector, "Recolector"+i);
            hiloRecolector.start();
        }

        Consumidor gaitero = new Consumidor(CONTENEDOR, 30000);
        Consumidor ladron = new Consumidor(CONTENEDOR, 20000);
        Consumidor trabanco = new Consumidor(CONTENEDOR, 10000);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 2; i++) {
            executorService.submit(gaitero);
        }

        for (int i = 0; i < 2; i++) {
            executorService.submit(ladron);
        }

        for (int i = 0; i < 2; i++) {
            executorService.submit(trabanco);
        }


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras hilo principal espera");
        }

        executorService.shutdown(); // no permite más
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)){
                executorService.shutdownNow(); // forzamos si no se cerró en 2 segundos
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Manzanas: "+ CONTENEDOR.cantidadManzanas);



    }
}
