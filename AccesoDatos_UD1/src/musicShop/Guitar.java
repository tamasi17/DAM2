package musicShop;

import java.util.Objects;

public class Guitar implements StringInstrument{

    double price=900;
    int strings = 6;
    String brand;
    boolean electric;

    static int contador = 4000;
    private int productID;

    public Guitar(String brand, boolean electric) {
        this.brand=brand;
        this.electric=electric;
        productID=++contador;
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

    public int getProductID() {
        return productID;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getCode() {
        return "GUIT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Guitar guitar = (Guitar) o;
        return Double.compare(price, guitar.price) == 0 && strings == guitar.strings && electric == guitar.electric && Objects.equals(brand, guitar.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, strings, brand, electric);
    }
}
