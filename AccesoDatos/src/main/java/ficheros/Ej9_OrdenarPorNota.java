package main.java.ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ej9_OrdenarPorNota {

    public static void main(String[] args) {

    String origen = "estudiantes.txt";
    String destino = "estudiantesOrdenados.txt";
    File f = new File(origen);

    if (!f.exists()){
        System.out.println("No encuentro el fichero.");
        return;
    }

    String linea="";
    String[] palabras=null;

    // Record Alumno(nota,int)
    Alumno alumno=null;
    ArrayList<Alumno> lista = new ArrayList<>();


    try (BufferedReader br = new BufferedReader(new FileReader(f))){
        while ((linea=br.readLine()) != null && !linea.isEmpty()){
            palabras=linea.split(",");
            lista.add(new Alumno(palabras[0],Double.parseDouble(palabras[1])));
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

        Collections.sort(lista);

        System.out.println("\nLista de alumnos ordenada: ");
        for (Alumno a : lista) {
            System.out.println(a.getNombre() + ", " + a.getNota());
        }




    }

}
