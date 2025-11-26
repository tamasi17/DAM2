package conectores.repaso;

import java.sql.Date;
import java.util.Objects;

public class Cliente {

    int idCliente;
    String nombre;
    Date fechaNac;
    double altura;
    boolean migrado;

    public Cliente() {
    }


    public Cliente(String nombre, Date fechaNac, double altura) {
        this.idCliente=0;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.altura = altura;
        this.migrado = false;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public boolean isMigrado() {
        return migrado;
    }

    public void setMigrado(boolean migrado) {
        this.migrado = migrado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente && Double.compare(altura, cliente.altura) == 0 && Objects.equals(nombre, cliente.nombre) && Objects.equals(fechaNac, cliente.fechaNac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombre, fechaNac, altura);
    }

    @Override
    public String toString() {
        return "[" + idCliente + "]" + nombre +
                ", " + fechaNac +
                ", " + altura + "cm";
    }
}
