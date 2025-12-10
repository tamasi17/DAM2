package ejerciciosUD2.ejercicio19.sol;

public class GestorTransferencias {

  public static boolean transferencia(Cuenta cuenta1, Cuenta cuenta2, int cantidad) {

    Cuenta cuentaMenor, cuentaMayor;
    if(cuenta1.getNumCuenta().compareTo(cuenta2.getNumCuenta()) < 0) {
      cuentaMenor = cuenta1;
      cuentaMayor = cuenta2;
    }
    else {
      cuentaMenor = cuenta2;
      cuentaMayor = cuenta1;
    }
    
    boolean result = false;
    synchronized (cuentaMenor) {
      synchronized (cuentaMayor) {
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
