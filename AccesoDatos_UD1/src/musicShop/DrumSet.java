package musicShop;

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
}
