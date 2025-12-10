package ejerciciosUD2.ejercicio21.concurrent_reentrantlock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BarberiaConcurrente {

    private final Queue<Cliente> colaDeEspera = new ArrayDeque<>();
    private final int capacidadMaximaSalaEspera;

    private final ReentrantLock lock = new ReentrantLock(true); // fairness ON
    private final Condition colaNoVacia = lock.newCondition();

    private boolean barberoOcupado = false;    // true si la silla del barbero está en uso
    private boolean llegadasCerradas = false;  // true cuando el generador señala fin de llegadas

    // Métricas claras
    private int totalIntentosAtencion = 0; // llegan a la puerta (aceptados o rechazados)
    private int totalEncolados = 0;        // aceptados en sistema (silla o sala)
    private int totalRechazados = 0;       // no caben (sala llena) → se marchan
    private int totalAtendidos = 0;        // servicio completado

    public BarberiaConcurrente(int capacidadMaximaSalaEspera) {
        this.capacidadMaximaSalaEspera = capacidadMaximaSalaEspera;
    }

    /** El generador avisa: no habrá más llegadas. */
    public void cerrarLlegadas() {
        lock.lock();
        try {
            llegadasCerradas = true;
            colaNoVacia.signalAll(); // despierta al barbero para que reevalúe cierre
        } finally {
            lock.unlock();
        }
    }

    /** Llega un cliente: pasa directo si barbero libre, espera si hay sillas, o se marcha si lleno. */
    public boolean llegaCliente(Cliente cliente) {

        lock.lock();
        try {
            totalIntentosAtencion++;

            // Barbero libre y sin cola, pasa directo (despierta si estaba dormido)
            if (!barberoOcupado && colaDeEspera.isEmpty()) {
                barberoOcupado = true;
                colaDeEspera.add(cliente);
                totalEncolados++;
                log("["+cliente + "] despierta al barbero y pasa directamente a la silla.");
                colaNoVacia.signal();
                return true;
            }

            // Hay sillas libres en la sala de espera
            if (colaDeEspera.size() < capacidadMaximaSalaEspera) {
                colaDeEspera.add(cliente);
                totalEncolados++;
                log("["+cliente + "] se sienta en la sala de espera (" +
                        colaDeEspera.size() + "/" + capacidadMaximaSalaEspera + ").");
                colaNoVacia.signal();
                return true;
            }

            // Sala llena, se marcha
            totalRechazados++;
            log("["+cliente + "] encuentra la sala llena y se marcha.");
            return false;

        } finally {
            lock.unlock();
        }
    }

    /**
     * El barbero solicita el siguiente cliente.
     * Devuelve null cuando no hay cola y llegadasCerradas==true, finaliza jornada.
     */
    public Cliente obtenerSiguienteCliente() {

        lock.lock();

        try {
            while (colaDeEspera.isEmpty()) {
                if (llegadasCerradas) {
                    return null; // no habrá más clientes y no hay cola → fin
                }
                log("[BARBERO] No hay clientes. Se duerme...");
                try {
                    colaNoVacia.await(); // esperar a que llegue alguien o al cierre
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null; // salida ordenada si interrumpen el hilo barbero
                }
            }

            Cliente siguienteCliente = colaDeEspera.poll();
            barberoOcupado = true;
            log("[BARBERO] Atiende a " + siguienteCliente +
                    ". Clientes en espera: " + colaDeEspera.size());
            return siguienteCliente;

        } finally {
            lock.unlock();
        }
    }

    /** El barbero termina el corte y actualiza métricas/estado. */
    public void finalizarCorte(Cliente clienteAtendido) {
        lock.lock();
        try {
            totalAtendidos++;
            log("[BARBERO] Termina con " + clienteAtendido +
                ". Atendidos=" + totalAtendidos +
                " | Intentos=" + totalIntentosAtencion +
                " | Encolados=" + totalEncolados +
                " | Rechazados=" + totalRechazados);

            if (colaDeEspera.isEmpty()) {
                barberoOcupado = false;
            }
            // Mantiene el sistema reactivo si añades más condiciones a futuro.
            colaNoVacia.signalAll();

        } finally {
            lock.unlock();
        }
    }

    /** Resumen para imprimir al final de la simulación. */
    public void imprimirResumen() {
        lock.lock();
        try {
            log("\n--- RESUMEN ---");
            log("Intentos de atención: " + totalIntentosAtencion);
            log("Encolados (aceptados): " + totalEncolados);
            log("Atendidos (servicio completado): " + totalAtendidos);
            log("Rechazados (sala llena): " + totalRechazados);
        } finally {
            lock.unlock();
        }
    }

    private void log(String mensaje) {
        System.out.println(mensaje);
    }
}
