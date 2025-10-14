package musicShop;

public class Bass implements StringInstrument{

    int strings = 4;
    String brand;
    boolean electric;

    public Bass(String brand, boolean electric) {
        this.brand=brand;
        this.electric=electric;
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
