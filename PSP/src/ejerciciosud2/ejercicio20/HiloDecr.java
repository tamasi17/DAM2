package ejerciciosud2.ejercicio20;

class HiloDecr implements Runnable {

    private final String id;
    private final Contador cont;

    HiloDecr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                while (this.cont.getCuenta() < 1) {
                    System.out.printf("!!! Hilo %s no puede decrementar, valor contador: %d\n",
                            this.id, this.cont.getCuenta());
                    try {
                        this.cont.wait();
                    } catch (InterruptedException ex) {
                    }
                }
                this.cont.decrementa();
                System.out.printf("Hilo %s decrementa, valor contador: %d\n", this.id,
                        this.cont.getCuenta());
            }
        }
    }
}
