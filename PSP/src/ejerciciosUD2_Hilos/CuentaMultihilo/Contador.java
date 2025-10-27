package ejerciciosUD2_Hilos.CuentaCrateres;

public class Contador extends Thread{

    static int pares;
    int x = 0;
    int y = 100000000;
    int indice;
    int hilos;

    public Contador(int hilos, int indice) {

        this.indice = (y/hilos) * indice;

        this.x += this.indice;

        if (indice == 0) {
            this.y = y/hilos;
        } else {
        this.y = x + indice;
        }
    }


    @Override
    public void run() {
        for (int i = x; i <= y; i++) {
            if (i % 2 == 0) {
                pares++;
                System.out.println(i + " es par");
            }
        }
    }

    public static int getPares() {
        return pares;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndice() {
        return indice;
    }

    public int getHilos() {
        return hilos;
    }
}
