package jdbc;

import java.util.ArrayList;

public class User {

    private int id;
    private String nombre;
    private String email;
    private ArrayList<Integer> telefonos;


    public User(int id, ArrayList<Integer> telefonos) {
        this.id = id;
        this.telefonos = telefonos;
    }

}
