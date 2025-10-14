package musicShop;

public class Bongo implements PercussionInstrument{

    String material;

    public Bongo(String material) {
        this.material=material;
    }

    @Override
    public void play() {
        System.out.println("bong o bong");
    }

    @Override
    public String toString() {
        return material + " bongo.";
    }
}
