package musicShop;

public class Bass implements StringInstrument{

    double price=1200;
    int strings = 4;
    String brand;
    boolean electric;

    static int contador = 1000;
    private int productID;

    public Bass(String brand, boolean electric) {
        this.brand=brand;
        this.electric=electric;
        contador++;
        productID=contador;
    }

    @Override
    public void play() {
        System.out.println("dum du dum");
    }

    @Override
    public String toString() {
        return brand + " "
                + (electric ? "Electric" : "Classical") + " bass.";
    }
}
