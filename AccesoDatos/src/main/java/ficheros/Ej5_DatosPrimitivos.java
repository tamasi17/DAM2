package ficheros;

import java.io.*;

public class Ej5_DatosPrimitivos {

    public static void main(String[] args) {



    // ruta
    String ruta="ficheros" + File.separator + "datos.bin";

    File fichero = new File(ruta);
    if (fichero.exists()){
        System.out.println("El fichero ya existe");
    }

    int entero = 2025;
    double doble = 3.14159;
    boolean bool = true;

    // Creamos el objeto DataOutputStream para la salida de datos binarios.


    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))){

        dos.writeInt(entero);
        dos.writeDouble(doble);
        dos.writeBoolean(bool);
    } catch (IOException e){
        System.err.println(e.getMessage());
    }

    // Leemos en el mismo orden en el que escribimos, usando DataInputStream
    // Lectura en bucle hasta EOFException

    int nuevoEntero = 0;
    double nuevoDoble = 0;
    boolean nuevoBool = false;


    try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))){
        while (true){
        nuevoEntero = dis.readInt();
        nuevoDoble = dis.readDouble();
        nuevoBool = dis.readBoolean();
        }
    } catch (EOFException e){
        System.out.println(">> Fin de fichero alcanzado");
    } catch (IOException e){

    }


        // leer

    // gestion errores

    }
}
