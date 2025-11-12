package ejemplos.syncronized.ejemplo6;

public class ImpresoraCompartida {

    private final Object candadoImpresion = new Object();

    public void imprimirDocumento(String nombreDoc) {
        System.out.println(Thread.currentThread().getName() + " está preparando " + nombreDoc);
        try { Thread.sleep(100); } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrumpido, cancelando impresión.");
            return;
        }

        synchronized (candadoImpresion) {
            System.out.println(Thread.currentThread().getName() + " imprime " + nombreDoc + "...");
            try { Thread.sleep(200); } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido durante la impresión.");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " terminó de imprimir " + nombreDoc);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ImpresoraCompartida impresora = new ImpresoraCompartida();

        Runnable tarea = () -> {
            for (int i = 1; i <= 3; i++) {
                impresora.imprimirDocumento("Documento_" + i + ".pdf");
            }
        };

        Thread usuario1 = new Thread(tarea, "Usuario 1");
        Thread usuario2 = new Thread(tarea, "Usuario 2");

        usuario1.start();
        usuario2.start();

        usuario1.join();
        usuario2.join();
    }
}
