package ejerciciosUD2_Mati.reto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/*  @author:    */

public class FilosofoMati implements Runnable {

    private final PalilloMati palilloIzquierdo;
    private final PalilloMati palilloDerecho;
    private int id;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    FilosofoMati(PalilloMati left, PalilloMati right, int id) {
        this.palilloIzquierdo = left;
        this.palilloDerecho = right;
        this.id = id;
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
                synchronized (palilloIzquierdo){

                doAction(LocalTime.now().format(formatter) + ": Coge el palillo izquierdo, palillo: "+  palilloIzquierdo.getNumeroPalillo());
                doAction(LocalTime.now().format(formatter) + ": Coge el palillo derecho, palillo: "+  palilloDerecho.getNumeroPalillo());
    			doAction(LocalTime.now().format(formatter)+ ":  Comiendo");
                doAction(LocalTime.now().format(formatter) + ": Deja el palillo derecho, palillo: "+  palilloDerecho.getNumeroPalillo());
                doAction(LocalTime.now().format(formatter) + ": Deja el palillo izquierdo:"+  palilloIzquierdo.getNumeroPalillo());
                }
    			doAction(LocalTime.now().format(formatter)+ ":  Pensando");

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


