package ejerciciosud2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Barrier {


    static void main() {

        CyclicBarrier cr = new CyclicBarrier(5);

        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {

                    try {
                        cr.await();
                    } catch (InterruptedException e) {
                        System.err.println(e.getLocalizedMessage());
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Hola "+ Thread.currentThread().getName());
                }
            });
        }


    }
}
