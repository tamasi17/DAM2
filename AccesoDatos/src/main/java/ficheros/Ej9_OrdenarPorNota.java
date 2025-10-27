package ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ej9_OrdenarPorNota {

    public static void main(String[] args) {

    String rutaOriginal = "ficheros/estudiantes.txt";
    String rutaDestino = "ficheros/estudiantesOrdenados.txt";
    File f = new File(rutaOriginal);

    if (!f.exists()){
        System.out.println("No encuentro el fichero.");
        return;
    }

    String linea="";
    String[] alumnos=null;

    // Record Alumno(nota,int)
    Alumno alumno=null;
    ArrayList<Alumno> lista = new ArrayList<>();


    try (BufferedReader br = new BufferedReader(new FileReader(f))){
        while ((linea=br.readLine()) != null && !linea.isEmpty()){
            alumnos=linea.split(",");
            lista.add(new Alumno(alumnos[0],Double.parseDouble(alumnos[1])));
        }
    } catch (EOFException e){
        System.err.println(e.getMessage());
    } catch (IOException e) {
        System.err.println(e.getMessage());

    }

        System.out.println("Lista de alumnos: ");
        for (Alumno a : lista) {
            System.out.println(a.getNombre() + ", " + a.getNota());
        }

//        Collections.sort(lista);
        lista.sort(Comparator.comparingDouble(Alumno::getNota).reversed());

        System.out.println("\nLista de alumnos ordenada: ");
        for (Alumno a : lista) {
            System.out.println(a.getNombre() + ", " + a.getNota());
        }




    }

}
