package musicShop;

public class Guitar implements StringInstrument{

    int strings = 6;
    String brand;
    boolean electric;

    public Guitar(String brand, boolean electric) {
        this.brand=brand;
        this.electric=electric;
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
