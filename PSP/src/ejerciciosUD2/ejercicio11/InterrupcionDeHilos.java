package ejerciciosUD2.ejercicio11;


class HiloInterrSleep implements Runnable {

  String nombre;

  public String getNombre() {
    return nombre;
  }

  HiloInterrSleep(String nombre) {
    this.nombre = nombre;
  }

  public void run() {

    try {

      while(true) {
        System.out.println(this.nombre);
        Thread.sleep(1000);
      }

    } catch (InterruptedException ex) {
      System.out.println("***INTERRUMPIDO*** " + this.getNombre());
    }
  }

}

class HiloInterrNoSleep implements Runnable {

  String nombre;

  public String getNombre() {
    return nombre;
  }

  HiloInterrNoSleep(String nombre) {
    this.nombre = nombre;
  }

  public void run() {

    while (!Thread.currentThread().isInterrupted()) {
      for (long i = 0; i < 1000000000L; i++) {
      }
      System.out.println(this.nombre);
    }
    System.out.println("***INTERRUMPIDO*** " + this.getNombre());

  }

}

public class InterrupcionDeHilos {

  public static void main(String[] args) {

    try {
      Thread hilo1 = new Thread(new HiloInterrSleep("H1"));
      Thread hilo2 = new Thread(new HiloInterrNoSleep("H2"));
      hilo1.start();
      hilo2.start();
      Thread.sleep(5000);
      hilo1.interrupt();
      hilo2.interrupt();

    } catch (InterruptedException ie) {
      System.out.println("Hilo principal interrumpido.");
    }

  }

}
