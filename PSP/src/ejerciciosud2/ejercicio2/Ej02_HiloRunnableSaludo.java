package ejerciciosud2.ejercicio2;

public class Ej02_HiloRunnableSaludo {

    public static void main(String[] args) {

        // Creamos dos hilos, cada uno con un mensaje distinto
        Thread h1 = new Thread(new Saludador("Hola"), "H1");
        Thread h2 = new Thread(new Saludador("Adiós"), "H2");

        // Iniciamos los hilos
        h1.start();
        h2.start();
    }
}
