package ejerciciosUD2.ejercicio12.productorconsumidor;

class Productor implements Runnable {
    private final Cesta recurso;

    public Productor(Cesta recurso) {
        this.recurso = recurso;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            recurso.producir(i);
            try {
                Thread.sleep(500); // simula el tiempo de producción
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

