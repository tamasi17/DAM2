package ejemplos.syncronized.ejemplo5;

public class Contador {

    private int cuenta = 0;  // variable global que van a incrementar los hilos

    public int getCuenta() {
        return cuenta;
    }

    // Incrementa la cuenta en 1 y devuelve el nuevo valor
    public int incrementa() {
        this.cuenta++;
        return cuenta;
    }
}

