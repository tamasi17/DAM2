package AccesoDatos;

import java.io.File;

public class ListadoDirectorio {

    public static void main(String[] args) {
        /// # Clase de prueba para acceso a ficheros.
        ///  **Inicializa la variable ruta** con un punto que representa el directorio actual. El directorio actual en este caso es el del proyecto.
        String ruta=".";

        // En Run - Debug Configurations puedes pasarle Program Arguments (es decir, al args).
        // Si no ponemos nada, la ruta sera ".", si ponemos un directorio, esa sera la nueva ruta.
        if (args.length>=1) ruta = args[0];
        ///  **Creacion del objeto File**, crea una instancia de la clase File
        File fich = new File(ruta);

        if (!fich.exists()) {
            // Verificamos existencia fichero o directorio
            System.out.println("No existe el fichero o directorio " + ruta);
        } else {
            if (fich.isFile()) {
            System.out.println(ruta + "es un fichero.");
            } else {
                System.out.println(ruta + "es un directorio. Contenidos: ");
                // Creamos un array con los objetos File.
                File[] ficheros = fich.listFiles();
                // Recorremos el array y añadimos un prefijo para mostrar si el objeto es un fichero o directorio.

                for (File f : ficheros) {
                    String textoDescripcion = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                    System.out.println( "( "+textoDescripcion+") " + f.getName());
                }

            }
        }



    }

}
