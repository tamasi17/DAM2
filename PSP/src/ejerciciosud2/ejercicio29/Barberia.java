package ejerciciosud2.ejercicio29;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Barberia {

    private final int capacidadSalaEspera;
    private final Queue<Cliente> colaDeEspera = new ArrayDeque<>();

    // Semáforos
    private final Semaphore mutex = new Semaphore(1);                // exclusión mutua
    private final Semaphore clientesDisponibles = new Semaphore(0);  // cuántos clientes hay esperando
    private final Semaphore barberoDisponible = new Semaphore(0);    // barbero listo para atender

    // Métricas
    private int clientesAtendidos = 0;
    private int clientesRechazados = 0;
    private int totalIntentos = 0;

    private volatile boolean llegadasCerradas = false;

    public Barberia(int capacidadSalaEspera) {
        this.capacidadSalaEspera = capacidadSalaEspera;
    }

    // Generador avisa de fin de llegadas
    public void cerrarLlegadas() {
        llegadasCerradas = true;
        // liberamos un permiso para que el barbero salga del bucle si estaba bloqueado
        clientesDisponibles.release();
    }

    public void llegaCliente(Cliente cliente) {
        try {
            mutex.acquire();
            totalIntentos++;

            if (colaDeEspera.size() < capacidadSalaEspera) {
                colaDeEspera.add(cliente);
                System.out.println("[" + cliente + "] entra en la barbería. Esperando (" +
                        colaDeEspera.size() + "/" + capacidadSalaEspera + ")");
                clientesDisponibles.release(); // avisa al barbero de que hay alguien
            } else {
                clientesRechazados++;
                System.out.println("[" + cliente + "] encuentra la sala llena y se marcha.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            mutex.release();
        }
    }

    public void atenderClientes() {
        try {
            while (true) {
                clientesDisponibles.acquire(); // espera a que haya algún cliente

                if (llegadasCerradas && colaDeEspera.isEmpty()) {
                    System.out.println("[BARBERO] No quedarán más clientes. Fin de la jornada.");
                    break;
                }

                Cliente clienteActual;

                mutex.acquire();
                clienteActual = colaDeEspera.poll();
                mutex.release();

                if (clienteActual != null) {
                    System.out.println("[BARBERO] Atiende a " + clienteActual);
                    cortarPelo(clienteActual);
                    barberoDisponible.release(); // libera al cliente (en este caso, solo simbólico)
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void cortarPelo(Cliente cliente) throws InterruptedException {
        long tiempo = 300 + (long) (Math.random() * 600);
        System.out.println("[BARBERO] Cortando el pelo a " + cliente + " (" + tiempo + " ms).");
        Thread.sleep(tiempo);
        clientesAtendidos++;
        System.out.println("[BARBERO] Termina con " + cliente +
                ". Atendidos=" + clientesAtendidos +
                " | Intentos=" + totalIntentos +
                " | Rechazados=" + clientesRechazados);
    }


}
