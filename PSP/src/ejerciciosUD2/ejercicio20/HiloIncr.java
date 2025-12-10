package ejerciciosUD2.ejercicio20;

class HiloIncr implements Runnable {

    private final String id;
    private final Contador cont;

    HiloIncr(String id, Contador c) {
        this.id = id;
        this.cont = c;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.cont) {
                this.cont.incrementa();
                this.cont.notify();
                System.out.printf("Hilo %s incrementa, valor contador: %d\n", this.id,
                        this.cont.getCuenta());
            }
        }
    }
}
