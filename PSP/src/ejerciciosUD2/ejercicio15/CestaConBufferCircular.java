package ejerciciosUD2.ejercicio15;

public class CestaConBufferCircular<T> {

    private final Object[] datos;
    private int head = 0;  // posición de extracción
    private int tail = 0;  // posición de inserción
    private int count = 0; // número de elementos
    private final int capacidad;

    public CestaConBufferCircular(int capacidad) {
        if (capacidad <= 0)
            throw new IllegalArgumentException("la capacidad deber ser mayor que 0");
        this.capacidad = capacidad;
        this.datos = new Object[capacidad];
    }

    public synchronized void put(T valor) throws InterruptedException {

        while (count == capacidad) {
            wait(); // buffer lleno → espera
        }
        datos[tail] = valor;
        tail = (tail + 1) % capacidad;
        count++;
        notifyAll(); // hay al menos un elemento para consumir
    }

    public synchronized T take() throws InterruptedException {
        while (count == 0) {
            wait(); // buffer vacío → espera
        }
        T valor = (T) datos[head];
        datos[head] = null; // ayuda al GC
        head = (head + 1) % capacidad;
        count--;
        notifyAll(); // hay al menos un hueco para producir
        return valor;
    }

    public synchronized int size() { return count; }
    public int capacidad() { return capacidad; }
}
