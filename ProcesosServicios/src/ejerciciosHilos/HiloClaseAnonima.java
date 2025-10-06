package ejerciciosHilos;

public class HiloClaseAnonima {
    static void main() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });
    }
}
