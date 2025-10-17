package musicShop;

import java.util.Objects;

public class DrumSet implements PercussionInstrument{

    double price=3100;
    int pieces;
    String brand;

    static int contador = 3000;
    private int productID;

    public DrumSet(String brand, int pieces) {
        this.brand=brand;
        this.pieces=pieces;
        contador++;
        productID=contador;
    }

    @Override
    public void play() {
        System.out.println("ba dum tss");
    }

    @Override
    public String toString() {
        return brand + " drum set: " +
                + pieces + " pieces.";
    }

    public int getProductID() {
        return productID;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCode() {
        return "DRUM";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DrumSet drumSet = (DrumSet) o;
        return Double.compare(price, drumSet.price) == 0 && pieces == drumSet.pieces && Objects.equals(brand, drumSet.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, pieces, brand);
    }
}
