package ejemplos.syncronized.ejemplo1;

public class E1_InfoHilo {
    public static void main(String[] args) {
        // Obtenemos el hilo actual (el que está ejecutando main)
        Thread hiloActual = Thread.currentThread();

        // Mostramos información básica del hilo
        System.out.println("Información del hilo actual:");
        System.out.println("Nombre: " + hiloActual.getName());
        System.out.println("ID: " + hiloActual.getId());
        System.out.println("Prioridad: " + hiloActual.getPriority());
        System.out.println("Estado: " + hiloActual.getState());
        System.out.println("Grupo: " + hiloActual.getThreadGroup().getName());
    }
}
