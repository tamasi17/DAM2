package ficheros;

public class Alumno implements Comparable<Alumno> {
    private final String nombre;
    private final double nota;

    public Alumno(String n, double no) {
        nombre = n;
        nota = no;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }


    @Override
    public int compareTo(Alumno a) {
        return Double.compare(a.nota,this.nota);
    }
}
