package ejerciciosUD2.ejercicio35;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RecursoCompartido {

    private static final int CAPACIDAD_MAX = 10;

    private final List<Integer> datos = new ArrayList<>(CAPACIDAD_MAX);
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true); // fairness

    /** Inserta un número si hay espacio. Devuelve true si insertó, false esta lleno. */
    public boolean escribir(int valor, String nombreHilo) {

        rwLock.writeLock().lock();

        try {
            if (datos.size() >= CAPACIDAD_MAX) {
                System.out.printf("[%s] escribe omitido (buffer lleno, tamaño=%d)%n",
                        nombreHilo, datos.size());
                return false;
            }
            datos.add(valor);
            System.out.printf("[%s] escribe → %d (ocupados=%d / libres=%d)%n",
                    nombreHilo, valor, datos.size(), CAPACIDAD_MAX - datos.size());
            return true;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /** Devuelve una instantánea inmutable del contenido actual. */
    public List<Integer> leerSnapshot(String nombreHilo) {
        rwLock.readLock().lock();
        try {
            // Instantánea para que el lector no bloquee
            return Collections.unmodifiableList(new ArrayList<>(datos));
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
