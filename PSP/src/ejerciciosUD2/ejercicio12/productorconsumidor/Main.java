package ejerciciosUD2.ejercicio12.productorconsumidor;

public class Main {

    public static void main(String[] args) {

        Cesta cesta = new Cesta();

        Thread productor = new Thread(new Productor(cesta));
        Thread consumidor = new Thread(new Consumidor(cesta));

        productor.start();
        consumidor.start();

    }
}
