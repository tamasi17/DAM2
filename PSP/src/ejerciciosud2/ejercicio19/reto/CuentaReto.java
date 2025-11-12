package ejerciciosud2.ejercicio19.reto;

public class CuentaReto {
  
  int saldo;
  final String numCuenta;

  public CuentaReto(String numCuenta, int saldoInicial) {
    this.saldo = saldoInicial;
    this.numCuenta = numCuenta;
  }
  
  public synchronized int getSaldo() {
    return this.saldo;
  }
  
  public synchronized void ingresar(int cantidad) {
    this.saldo += cantidad;
  }
  
  public synchronized void sacar(int cantidad) {
    this.saldo -= cantidad;
  }
  
  public String getNumCuenta() {
    return this.numCuenta;
  }
  
}
