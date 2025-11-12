package ejerciciosud2.ejercicio20;

class Contador {

    private int cuenta = 0;

    Contador(int valorInicial) {
        this.cuenta = valorInicial;
    }

    synchronized public int getCuenta() {
        return cuenta;
    }

    synchronized public int incrementa() {
        this.cuenta++;
        return cuenta;
    }

    synchronized public int decrementa() {
        this.cuenta--;
        return cuenta;
    }

}
