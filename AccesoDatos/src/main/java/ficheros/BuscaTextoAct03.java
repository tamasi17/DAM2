package ficheros;

import java.io.*;
import java.util.Scanner;

public class BuscaTextoAct03 {

    // Este programa busca un texto dado en un fichero de texto.
    static void main(String[] args) {

        // Se comprueba si la ruta del archivo está introducido como argumento de la linea de comandos.
        if (args.length < 1) {
            System.out.println("Indica el nombre del fichero en el argumento del programa");
            return;
        }

        // Almacena el nombre del archivo que hemos pasado como argumento.
        String nomFich = args[0];

        // Scanner para que pasemos por teclado la palabra buscada
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe una palabra: ");
        String textoBuscado = sc.next();


        // Bloque try with resources para que cierre main.java.ficheros automaticamente
        try (BufferedReader br = new BufferedReader(new FileReader(nomFich))) {
            int i = 0;
            String linea = br.readLine();
            Integer pos = -1;

            // Bucle que busca la posicion de textoBuscado linea por linea.
            while (linea!=null) {
                pos=linea.indexOf(textoBuscado);
                if (pos!=-1){
                    System.out.println("El texto se encuentra en la linea " + i + " en la posicion " + pos);
                    System.out.println();
                }
                i++;
                linea = br.readLine();
            }

            if (pos==-1) System.out.println("Esta palabra no se encuentra en este archivo.");

        // Captura la excepcion si no encuentra el archivo
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + nomFich);
        }
        // Captura otras main.java.excepciones de E/S.
        catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
        // Captura cualquier otra excepción no especificada.
        catch (Exception e) {
            e.printStackTrace();
        }

    sc.close();

    }

}
