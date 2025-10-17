package musicShop;

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
}
