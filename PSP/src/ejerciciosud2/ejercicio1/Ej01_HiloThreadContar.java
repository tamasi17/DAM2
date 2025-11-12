package ejerciciosud2.ejercicio1;

public class Ej01_HiloThreadContar {

    public static void main(String[] args) {

        // Creamos e iniciamos el hilo
        ContadorThread hilo = new ContadorThread("Hilo-Thread");

        hilo.start();
    }
}
