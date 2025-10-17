package musicShop;

public class Guitar implements StringInstrument{

    double price=1200;
    int strings = 6;
    String brand;
    boolean electric;

    static int contador = 4000;
    private int productID;

    public Guitar(String brand, boolean electric) {
        this.brand=brand;
        this.electric=electric;
        contador++;
        productID=contador;
    }

    @Override
    public void play() {
        System.out.println("wah wah");
    }

    @Override
    public String toString() {
        return brand + " "
                + (electric ? "Electric" : "Classical") + " guitar.";
    }
}
