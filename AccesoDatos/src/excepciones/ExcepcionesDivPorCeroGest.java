package excepciones;

// Excepción gestionada durante la ejecución de un programa.
public class ExcepcionesDivPorCeroGest {

    static void main(String[] args) {
        int a, b;
        a = 5;
        b = 2;

        // Descomentar para probar funcionamiento con tres try-catch


        /*
            try {
                System.out.println(a + " / " + b + " = " + a / b);
            } catch (ArithmeticException e) {
                System.err.println("Error al dividir: " + a + " / " + b);
            }

            try {
                b = 0;
                System.out.println(a + " / " + b + " = " + a / b);
            } catch (ArithmeticException e) {
                System.err.println("Error al dividir: " + a + " / " + b);
            }

            try {
                b = 3;
                System.out.println(a + " / " + b + " = " + a / b);
            } catch (ArithmeticException e) {
                System.err.println("Error al dividir: " + a + " / " + b);
            }
        */


        // Descomentar para probar con un solo try-catch
        try {
            System.out.println(a + " / " + b + " = " + a / b);
            b = 0;
            System.out.println(a + " / " + b + " = " + a / b);
            b = 3;
            System.out.println(a + " / " + b + " = " + a / b);
        } catch (ArithmeticException e) {
            System.err.println("Error al dividir: " + a + " / " + b);
        }

    }

    public int divide(int a, int b) {
        return a / b;
    }
}
