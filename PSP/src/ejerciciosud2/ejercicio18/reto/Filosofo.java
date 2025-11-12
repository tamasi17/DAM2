package ejerciciosud2.ejercicio18.reto;

import ejerciciosUD2_Hilos.reto.PalilloMati;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/*  @author:    */

public class Filosofo implements Runnable {

    private final Palillo palilloIzquierdo;
    private final Palillo palilloDerecho;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    Filosofo(Palillo left, Palillo right) {
        this.palilloIzquierdo = left;
        this.palilloDerecho = right;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((ThreadLocalRandom.current().nextInt(1500,3000)+ 1));
    }

    @Override
    public void run() {

        try {
        
            while (true) {

                doAction(LocalTime.now().format(formatter) + ": Pensando. Mis palillos son el "+  palilloIzquierdo.getNumeroPalillo()+" y el "+ +  palilloDerecho.getNumeroPalillo());
                doAction(LocalTime.now().format(formatter) + ": Coge el palillo izquierdo, palillo: "+  palilloIzquierdo.getNumeroPalillo());
                doAction(LocalTime.now().format(formatter) + ": Coge el palillo derecho, palillo: "+  palilloDerecho.getNumeroPalillo());
    			doAction(LocalTime.now().format(formatter)+ ":  Comiendo");
                doAction(LocalTime.now().format(formatter) + ": Deja el palillo derecho, palillo: "+  palilloDerecho.getNumeroPalillo());
                doAction(LocalTime.now().format(formatter) + ": Deja el palillo izquierdo:"+  palilloIzquierdo.getNumeroPalillo());
    			doAction(LocalTime.now().format(formatter)+ ":  Pensando");

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


