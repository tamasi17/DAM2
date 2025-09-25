package ficheros;

import java.io.File;
import java.text.SimpleDateFormat;

public class VerFicheros {

    public static void main(String[] args) {
        /// # Clase de prueba para acceso a ficheros.
        ///  **Inicializa la variable ruta** con un punto que representa el directorio actual. El directorio actual en este caso es el del proyecto DAM2.
        String ruta = ".";

        // En Run - Debug Configurations puedes pasarle Program Arguments (es decir, al args).
        // Si no ponemos nada, la ruta sera ".", si ponemos un directorio, esa sera la nueva ruta.
        if (args.length >= 1) ruta = args[0];
        ///  **Creacion del objeto File**, crea una instancia de la clase File
        File fich = new File(ruta);

        ///  Para formatear la salida por pantalla de la fecha de ultima modificacion.
        SimpleDateFormat sdf = new SimpleDateFormat();

        if (!fich.exists()) {
            // Verificamos existencia fichero o directorio
            System.out.println("No existe el fichero o directorio " + ruta);
        } else {
            if (fich.isFile()) {
                System.out.println(ruta + "es un fichero.");
                System.out.println(fich.length());

            } else {
                System.out.println(ruta + "\n Es un directorio. Contenidos: \n");
                // Creamos un array con los objetos File.
                File[] ficheros = fich.listFiles();
                // Recorremos el array y añadimos un prefijo para mostrar si el objeto es un fichero o directorio.
                // Además indicamos tamaño si es fichero, última fecha de modificación y permisos.
                for (File f : ficheros) {
                    String textoDescripcion = f.isDirectory() ? "/" : f.isFile() ? "_ " + fich.length() + " bytes" : "?";
                    System.out.println("(" + textoDescripcion + ") " + f.getName() + " - Last modified: " + sdf.format(fich.lastModified()));

                    String textoPermisos = "";
                    if (fich.canRead()) {
                        textoPermisos += "r";
                    } else textoPermisos += "-";
                    if (fich.canWrite()) {
                        textoPermisos += "w";
                    } else textoPermisos += "-";
                    if (fich.canExecute()) {
                        textoPermisos += "x";
                    } else textoPermisos += "-";
                    System.out.println(textoPermisos);
                }

            }
        }

    }
}