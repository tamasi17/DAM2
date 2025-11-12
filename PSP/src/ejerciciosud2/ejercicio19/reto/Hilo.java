package ejerciciosud2.ejercicio19.reto;

class Hilo implements Runnable {

    Cuenta cuenta1, cuenta2;
    String nomHilo;

    Hilo(Cuenta c1, Cuenta c2, String nombreHilo) {
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
