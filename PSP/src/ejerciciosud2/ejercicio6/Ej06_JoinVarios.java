package ejerciciosud2.ejercicio6;

public class Ej06_JoinVarios {

    public static void main(String[] args) throws InterruptedException {

        // Creamos tres hilos, cada uno con una tarea diferente
        Thread hilo1 = new Thread(new Tarea("hilo1", 120, 4), "Hilo-1");
        Thread hilo2 = new Thread(new Tarea("hilo2", 90,  5), "Hilo-2");
        Thread hilo3 = new Thread(new Tarea("hilo3", 150, 3), "Hilo-3");

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperamos a que todos terminen antes de continuar
        hilo1.join();
        hilo2.join();
        hilo3.join();

        System.out.println("Fin: todas las ejecuciones han terminado.");
    }
}
