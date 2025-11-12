package ejerciciosud2.ejercicio19.reto;

public class TransferenciaDeadlock {

  public static void main(String[] args) {

    CuentaReto cuentaReto1 = new CuentaReto("ES1112345678901234547999", 12500);
    CuentaReto cuentaReto2 = new CuentaReto("ES3338901234567890123111", 23400);
    
    System.out.printf("Saldo inicial de %s: %d\n", cuentaReto1.getNumCuenta(), cuentaReto1.getSaldo());
    System.out.printf("Saldo inicial de %s: %d\n", cuentaReto2.getNumCuenta(), cuentaReto2.getSaldo());
    System.out.println("---------------------------------------");

    // Dos hilos, uno que hace transferencias de c1 a c2, y otro que hace transferencias de c2 a c1.
    Thread hilo1 = new Thread(new Hilo(cuentaReto1, cuentaReto2, "Hilo1"));
    Thread hilo2 = new Thread(new Hilo(cuentaReto2, cuentaReto1, "Hilo2"));

    hilo1.start();
    hilo2.start();

    try {
      hilo1.join();
      hilo2.join();

    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    System.out.println("---------------------------------------");
    System.out.printf("Saldo final en %s : %d\n", cuentaReto1.getNumCuenta(), cuentaReto1.getSaldo());
    System.out.printf("Saldo final en %s : %d\n", cuentaReto2.getNumCuenta(), cuentaReto2.getSaldo());

  }

}
