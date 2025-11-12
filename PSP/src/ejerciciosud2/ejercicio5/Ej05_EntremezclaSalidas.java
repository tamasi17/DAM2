package ejerciciosud2.ejercicio5;

public class Ej05_EntremezclaSalidas {

    public static void main(String[] args) {

        // Creamos tres hilos con tareas distintas
        Thread a = new Thread(new Tarea("A", 90));
        Thread b = new Thread(new Tarea("  B", 110));
        Thread c = new Thread(new Tarea("    C", 70));

        // Iniciamos los tres hilos
        a.start();
        b.start();
        c.start();
    }
}
