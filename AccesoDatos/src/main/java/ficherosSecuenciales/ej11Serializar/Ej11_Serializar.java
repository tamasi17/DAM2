package ficherosSecuenciales.ej11Serializar;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Ej11_Serializar {

    /*
    Desarrolla una aplicación que permita serializar y deserializar objetos de una clase Empleado
     */

    static void main() {

        File file = new File("ficherosSecuenciales/empleados.obj");

        Empleado lau = new Empleado("Laura Martin", "Recursos Humanos", 27000);
        Empleado carlos = new Empleado("Carlos Pérez", "Contabilidad", 32000);
        Empleado ana = new Empleado("Ana Ruiz", "Marketing", 29000);

        ArrayList<Empleado> empleados = new ArrayList<>(Arrays.asList(lau, carlos, ana));

        try {
            if (file.createNewFile()) System.out.println("Binary file created: " + file.getPath());
        } catch (IOException ioe) {
            System.err.println("Binary file not created: " + ioe.getLocalizedMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Empleado empleado : empleados) {
                oos.writeObject(empleado);
            }

            System.out.println(">> Lista de empleados serializada correctamente. \n");

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error writing objects");
        }


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            Empleado empleado = null;
            while ((empleado = (Empleado) ois.readObject()) != null) {
                System.out.println(empleado.toString());
            }

        } catch (EOFException eofe) {
            System.out.println("\n>> Lista de empleados leída:");

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found when reading: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error while reading: " + ioe.getLocalizedMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Could not find the class: " + cnfe.getLocalizedMessage());
        }


    }
}
