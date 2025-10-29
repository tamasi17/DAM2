package ejerciciosud2.ejercicio19.reto;

public class TransferenciaDeadlock {

  public static void main(String[] args) {

    Cuenta cuenta1 = new Cuenta("ES1112345678901234547999", 12500);
    Cuenta cuenta2 = new Cuenta("ES3338901234567890123111", 23400);
    
    System.out.printf("Saldo inicial de %s: %d\n", cuenta1.getNumCuenta(), cuenta1.getSaldo());
    System.out.printf("Saldo inicial de %s: %d\n", cuenta2.getNumCuenta(), cuenta2.getSaldo());
    System.out.println("---------------------------------------");

    // Dos hilos, uno que hace transferencias de c1 a c2, y otro que hace transferencias de c2 a c1.
    Thread hilo1 = new Thread(new Hilo(cuenta1, cuenta2, "Hilo1"));
    Thread hilo2 = new Thread(new Hilo(cuenta2, cuenta1, "Hilo2"));

    hilo1.start();
    hilo2.start();

    try {
      hilo1.join();
      hilo2.join();

    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    System.out.println("---------------------------------------");
    System.out.printf("Saldo final en %s : %d\n", cuenta1.getNumCuenta(), cuenta1.getSaldo());
    System.out.printf("Saldo final en %s : %d\n", cuenta2.getNumCuenta(), cuenta2.getSaldo());

  }

}
