package ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ej3_PokemonBinario {

    static void main() {

        File fichero = new File("/DAM2/ficheros/equipoPokemon.bin");

        crearFichero(fichero);

        System.out.println("Escribimos con DataOutputStream - FileOutputStream");
        escribirEquipoPokemon(fichero);

        System.out.println("Leemos con DataInputStream - FileInputStream");
        leerEquipoPokemon(fichero);
    }

    private static void crearFichero(File fichero) {
        fichero.getParentFile().mkdirs();

        try {
            fichero.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error al crear el fichero.");
            System.err.println(ioe.getLocalizedMessage());
        }
    }

    private static void escribirEquipoPokemon(File fichero) {
        System.out.println("Escribiendo equipo pokemon en fichero: " + fichero.getName());
        List<String> pokemon = new ArrayList<>(Arrays.asList("Pikachu","Charmander", "Bulbasaur","Squirtle"));

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))){
            for (String p : pokemon) {
            dos.writeUTF(p);
            }
        } catch (FileNotFoundException fnfe){
            System.err.println("No se encuentra el archivo donde escribir.");
        } catch (IOException ioe) {
            System.err.println("Error de E/S" + ioe.getLocalizedMessage());
            System.exit(1);
        }
    }

    private static void leerEquipoPokemon(File fichero) {
        System.out.println("Leyendo equipo pokemon desde fichero: " + fichero.getName());
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
            String linea;
            while (true) {
                linea = dis.readUTF();
                System.out.println("·" + linea);
            }
        } catch (EOFException eofe){
            System.out.println("\nFinal de fichero, cerramos lectura de archivo.");
            return;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo" + e.getLocalizedMessage());
            System.exit(1);
            // cero si el proceso funciona correctamente. A partir de ahi, nosotros marcamos el exitCode.
        }
    }

}
