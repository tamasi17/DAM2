package ficheros.ej11Serializar;

import java.io.Serializable;

public class Empleado implements Serializable {

    String nombre;
    String departamento;
    double salario;

    public Empleado(String nombre, String departamento, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return  nombre +
                ": " + departamento +
                " - " + salario +
                " euros.\n";
    }
}
