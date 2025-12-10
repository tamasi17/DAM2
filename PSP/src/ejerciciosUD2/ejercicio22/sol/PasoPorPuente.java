package ejerciciosUD2.ejercicio22.sol;

/**
 *
 * programa que controle el paso de personas por un puente, siempre en la misma
 * dirección, para que se cumplan las siguientes restricciones. No pueden pasar
 * más de tres personas a la vez, y no puede haber más de 200 kg de peso en
 * ningún momento. En un sistema real, se podría obtener la información de las
 * personas que llegan y de su peso mediante sensores. Para realizar la
 * simulación, se va a modelar las personas como hilos. El tiempo entre la
 * llegada de dos personas es aleatorio entre 1 y 30 segundos,
 * para pasar entre 10 y 50 segundos, y las personas tienen un peso aleatorio
 * entre 40 y 120 kg.
 *
 */
import java.util.Random;

public class PasoPorPuente {

  public static void main(String[] args) {

    final Puente puente = new Puente();

    int tMinParaLlegadaPersona = 1;
    int tMaxParaLlegadaPersona = 30;
    int tMinPaso = 10;
    int tMaxPaso = 50;
    int minPesoPersona = 40;
    int maxPesoPersona = 120;

    System.out.println(">>>>>>>>>>>> Comienza simulación.");
    Random r = new Random();
    int idPersona = 1;

    while (true) {

      int tParaLlegadaPersona = tMinParaLlegadaPersona + r.nextInt(
              tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1);
      int pesoPersona = minPesoPersona + r.nextInt(
              maxPesoPersona - minPesoPersona + 1);

      System.out.printf("Siguiente persona llega en %d segundos.\n", tParaLlegadaPersona);

      try {
        Thread.sleep(1000*tParaLlegadaPersona);
      } catch (InterruptedException ex) {
        System.out.printf("Interrumpido proceso principal");
      }

      Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P"+idPersona));
      hiloPersona.start();

      idPersona++;

    }

  }

}
