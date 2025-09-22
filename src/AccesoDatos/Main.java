package AccesoDatos;

import java.util.ArrayList;

public class Main {

    static void main() {

        ArrayList<Integer> lista = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            lista.add(i);
        }

        for (Integer i : lista) {
            System.out.println(i);
        }

        }
    }
