package ejerciciosUD2.ejercicio19.sol;

class Hilo implements Runnable {

    Cuenta c1, c2;
    String nomHilo;

    Hilo(Cuenta c1, Cuenta c2, String nomHilo) {
        this.c1 = c1;
        this.c2 = c2;
        this.nomHilo = nomHilo;
    }

    @Override
    public void run() {
        int cantidad = 10;
        int numTransf = 0;
        for (int i = 0; i < 10000; i++) {
            if (GestorTransferencias.transferencia(c1, c2, cantidad)) {
                numTransf++;
            }
        }
        System.out.printf("**** FIN de %s, %d transferencias hechas de %s a %s.\n",
                this.getNomHilo(), numTransf, c1.getNumCuenta(), c2.getNumCuenta());
    }

    public String getNomHilo() {
        return nomHilo;
    }

}
