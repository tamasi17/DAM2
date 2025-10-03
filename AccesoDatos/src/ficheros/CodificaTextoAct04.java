package ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CodificaTextoAct04 {

    static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Indica el archivo en el argumento del programa.");
        }

        String original = "original.txt";
        String codISO = "codificadoISO.txt";
        String codUTF16 = "codificadoUTF16.txt";
        File fOriginal = new File(original);
        File fCodISO = new File(codISO);
        File fCodUTF = new File(codUTF16);


        if (fOriginal.exists()){
            System.out.println("Codificando el fichero " + original + "a ISO-8859-1 y a UTF16 ...");
        } else {
            System.out.println("Creado el fichero: " + original);
        }

        /*


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fCodISO))){

        }


         */
    }
}
