package ejerciciosUD2.ejercicio19.reto;


class Hilo implements Runnable {

    CuentaReto cuenta1;
    CuentaReto cuenta2;
    String nomHilo;

    Hilo(CuentaReto c1, CuentaReto c2, String nombreHilo) {
        this.cuenta1 = c1;
        this.cuenta2 = c2;
        this.nomHilo = nombreHilo;
    }

    @Override
    public void run() {
        int cantidad = 10;
        int numTransf = 0;
        for (int i = 0; i < 10000; i++) {
            if (GestorTransferencias.transferencia(cuenta1, cuenta2, cantidad)) {
                numTransf++;
            }
        }
        System.out.printf("**** FIN de %s, %d transferencias hechas de %s a %s.\n",
                this.getNomHilo(), numTransf, cuenta1.getNumCuenta(), cuenta2.getNumCuenta());
    }

    public String getNomHilo() {
        return nomHilo;
    }

}
