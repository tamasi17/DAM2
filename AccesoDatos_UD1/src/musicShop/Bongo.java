package musicShop;

import java.util.Objects;

public class Bongo implements PercussionInstrument{

    double price=40;
    String material;

    static int contador = 2000;
    private int productID;

    public Bongo(String material) {
        this.material=material;
        contador++;
        productID=contador;
    }

    @Override
    public void play() {
        System.out.println("bong o bong");
    }

    @Override
    public String toString() {
        return material + " bongo.";
    }

    public int getProductID() {
        return productID;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bongo bongo = (Bongo) o;
        return Double.compare(price, bongo.price) == 0 && Objects.equals(material, bongo.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, material);
    }

    @Override
    public String getCode() {
        return "BONG";
    }

}
