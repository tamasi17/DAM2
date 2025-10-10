package ficheros;

import java.io.*;

public class Ej2_Pokemon {

    static void main() {

        File fichero = new File("/DAM2/ficheros/equipoPokemon.txt");

        crearFichero(fichero);

            escribirEquipoPokemon(fichero);

            leerEquipoPokemon(fichero);

    }

    private static void leerEquipoPokemon(File fichero) {
        System.out.println("Leyendo equipo pokemon desde fichero: " + fichero.getName());
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("·" + linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo" + e.getLocalizedMessage());
            System.exit(1);
            // cero si el proceso funciona correctamente. A partir de ahi, nosotros marcamos el exitCode.
        }
    }

    private static void escribirEquipoPokemon(File fichero) {
        System.out.println("Escribiendo equipo pokemon en fichero: " + fichero.getName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            bw.write("Pikachu");
            bw.newLine();
            bw.write("Charmander");
            bw.newLine();
            bw.write("Bulbasaur");
            bw.newLine();
            bw.write("Squirtle");

        } catch (FileNotFoundException fnfe){
            System.err.println("No se encuentra el archivo donde escribir.");
        } catch (IOException ioe) {
            System.err.println("Error de E/S" + ioe.getLocalizedMessage());
            System.exit(1);
        }
    }

    private static void crearFichero(File fichero) {
        System.out.println("Creando fichero...");
        fichero.getParentFile().mkdirs();

        try {
            fichero.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error al crear el fichero.");
            System.err.println(ioe.getLocalizedMessage());
        }
    }
}
