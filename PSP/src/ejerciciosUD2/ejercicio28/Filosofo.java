package ejerciciosUD2.ejercicio28;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Filosofo implements Runnable {

    private final int idFilosofo;
    private final Palillo palilloIzquierdo;
    private final Palillo palilloDerecho;
    private final int ciclosComida;

    // Tiempos largos para trazas legibles (ms)
    private static final int TIEMPO_PENSAR_MIN_MS = 1500;
    private static final int TIEMPO_PENSAR_MAX_MS = 3000;
    private static final int TIEMPO_COMER_MIN_MS  = 1500;
    private static final int TIEMPO_COMER_MAX_MS  = 3000;

    // Control de contención
    private static final int TIEMPO_ESPERA_DERECHO_MS = 300;  // timeout intentando coger el derecho
    private static final int TIEMPO_BACKOFF_MAX_MS     = 200;  // pausa aleatoria antes de reintentar

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Filosofo(int idFilosofo, Palillo palilloIzquierdo, Palillo palilloDerecho, int ciclosComida) {
        this.idFilosofo = idFilosofo;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
        this.ciclosComida = ciclosComida;
    }

    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        escribirEvento("Pensando");
        dormir(random.nextInt(TIEMPO_PENSAR_MIN_MS, TIEMPO_PENSAR_MAX_MS + 1));

        for (int i = 0; i < ciclosComida; i++) {

            // 1) Intentar coger izquierdo
            if (!palilloIzquierdo.intentarCoger()) {
                dormir(random.nextInt(20, 60));
                continue;
            }
            escribirEvento("Coge el palillo izquierdo");

            try {
                // 2) Intentar coger derecho con timeout; si falla, soltar izquierdo y reintentar
                if (!palilloDerecho.intentarCogerConEspera(TIEMPO_ESPERA_DERECHO_MS)) {
                    palilloIzquierdo.soltar();
                    dormir(random.nextInt(10, TIEMPO_BACKOFF_MAX_MS + 1));
                    continue;
                }

                // 3) Comer
                escribirEvento("Coge el palillo derecho (comiendo)");
                dormir(random.nextInt(TIEMPO_COMER_MIN_MS, TIEMPO_COMER_MAX_MS + 1));

                // 4) Dejar ambos y pensar
                escribirEvento("Deja el palillo derecho");
                palilloDerecho.soltar();

                escribirEvento("Deja el palillo izquierdo. Se pone a pensar.");
                palilloIzquierdo.soltar();

                escribirEvento("Pensando");
                dormir(random.nextInt(TIEMPO_PENSAR_MIN_MS, TIEMPO_PENSAR_MAX_MS + 1));

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Liberación defensiva ante interrupción
                try { palilloDerecho.soltar(); } catch (Exception ignored) {}
                try { palilloIzquierdo.soltar(); } catch (Exception ignored) {}
                return;
            }
        }
    }

    private void dormir(long tiempoMs) {
        try {
            Thread.sleep(tiempoMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void escribirEvento(String mensaje) {
        String hora = LocalTime.now().format(FORMATO_HORA);
        System.out.println("Filósofo " + idFilosofo + " " + hora + ": " + mensaje);
    }
}
