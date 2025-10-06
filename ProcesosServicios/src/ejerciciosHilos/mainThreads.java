package ejerciciosHilos;

public class mainThreads {
    static void main() {
        Thread thread1 = new HiloThread();
        thread1.start();
        Thread thread2 = new Thread(new HiloRunnable());
        thread2.start();



    }
}
