package musicShop;

import java.util.Objects;

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

    public int getProductID() {
        return productID;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCode() {
        return "BASS";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bass bass = (Bass) o;
        return Double.compare(price, bass.price) == 0 && strings == bass.strings && electric == bass.electric && Objects.equals(brand, bass.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, strings, brand, electric);
    }
}
