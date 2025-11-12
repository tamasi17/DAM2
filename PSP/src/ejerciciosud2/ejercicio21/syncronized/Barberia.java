package ejerciciosud2.ejercicio21.syncronized;

import java.util.ArrayDeque;
import java.util.Queue;

public class Barberia {

    private final Queue<Cliente> colaDeEspera = new ArrayDeque<>();
    private final int capacidadMaximaSalaEspera;

    private boolean barberoOcupado = false;
    private boolean llegadasCerradas = false; // ← NUEVO: ya no llegarán más clientes

    // Métricas opcionales
    private int clientesAtendidos = 0;
    private int clientesRechazados = 0;
    private int totalIntentosAtencion = 0;

    public Barberia(int capacidadMaximaSalaEspera) {
        this.capacidadMaximaSalaEspera = capacidadMaximaSalaEspera;
    }

    // Llamado por el generador al acabar
    public synchronized void cerrarLlegadas() {
        llegadasCerradas = true;
        notifyAll(); // despierta al barbero si está dormido y no hay más trabajo
    }

    // Llega un cliente (devuelve true si entra en sistema, false si se marcha)
    public synchronized boolean llegaCliente(Cliente cliente) {
        totalIntentosAtencion++;

        // Si el barbero está libre y no hay nadie en cola → pasa directo y despierta
        if (!barberoOcupado && colaDeEspera.isEmpty()) {
            barberoOcupado = true;
            colaDeEspera.add(cliente);
            System.out.println("["+cliente + "] despierta al barbero y pasa directamente a la silla.");
            notifyAll();
            return true;
        }

        // Si hay sillas libres → se sienta en espera
        if (colaDeEspera.size() < capacidadMaximaSalaEspera) {
            colaDeEspera.add(cliente);
            System.out.println("["+cliente+ "] se sienta en la sala de espera (" +
                    colaDeEspera.size() + "/" + capacidadMaximaSalaEspera + ").");
            notifyAll();
            return true;
        }

        // Sala llena → se marcha
        clientesRechazados++;
        System.out.println("["+cliente + "] encuentra la sala llena y se marcha.");
        return false;
    }

    // El barbero pide el siguiente cliente; devuelve null si debe terminar
    public synchronized Cliente obtenerSiguienteCliente() {
        while (colaDeEspera.isEmpty()) {
            // Condición de cierre correcta: no hay cola y no habrá más llegadas
            if (llegadasCerradas) {
                return null;
            }
            System.out.println("[BARBERO] No hay clientes. Se duerme...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Cliente siguienteCliente = colaDeEspera.poll();
        barberoOcupado = true;
        System.out.println("[BARBERO] Atiende a " + siguienteCliente +
                ". Clientes en espera: " + colaDeEspera.size());
        return siguienteCliente;
    }

    // El barbero termina el corte con un cliente
    public synchronized void finalizarCorte(Cliente clienteAtendido) {
        clientesAtendidos++;
        System.out.println("[BARBERO] Termina con " + clienteAtendido +
                ". Total atendidos: " + clientesAtendidos +
                " | Intentos: " + totalIntentosAtencion +
                " | Rechazados: " + clientesRechazados);

        if (colaDeEspera.isEmpty()) {
            barberoOcupado = false;
        }
        notifyAll();
    }


}
