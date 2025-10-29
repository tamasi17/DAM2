package ejerciciosud2.ejercicio19.reto;

public class GestorTransferencias {

  public static boolean transferencia(Cuenta cuenta1, Cuenta cuenta2, int cantidad) {

    boolean result = false;

    synchronized (cuenta1) {
      synchronized (cuenta2) {
        if (cuenta1.getSaldo() >= cantidad) {
          cuenta1.sacar(cantidad);
          cuenta2.ingresar(cantidad);
          result = true;
        }
      }
    }
    return result;
  }

}
