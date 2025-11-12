package ejerciciosud2.ejercicio19.sol;

public class TransferenciaSinBloqueo {

  public static void main(String[] args) {

    Cuenta c1 = new Cuenta("ES1112345678901234547999", 12500);
    Cuenta c2 = new Cuenta("ES3338901234567890123111", 23400);
    
    System.out.printf("Saldo inicial de %s: %d\n", c1.getNumCuenta(), c1.getSaldo());
    System.out.printf("Saldo inicial de %s: %d\n", c2.getNumCuenta(), c2.getSaldo());
    System.out.println("---------------------------------------");

    // Dos hilos, uno que hace transferencias de c1 a c2, y otro que hace transferencias de c2 a c1.
    Thread h1 = new Thread(new Hilo(c1, c2, "H1"));
    Thread h2 = new Thread(new Hilo(c2, c1, "H2"));

    h1.start();
    h2.start();

    try {
      h1.join();
      h2.join();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    System.out.println("---------------------------------------");
    System.out.printf("Saldo final en %s : %d\n", c1.getNumCuenta(), c1.getSaldo());
    System.out.printf("Saldo final en %s : %d\n", c2.getNumCuenta(), c2.getSaldo());

  }

}
