package musicShop;

public class DrumSet implements PercussionInstrument{

    int pieces;
    String brand;

    public DrumSet(String brand, int pieces) {
        this.brand=brand;
        this.pieces=pieces;
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
