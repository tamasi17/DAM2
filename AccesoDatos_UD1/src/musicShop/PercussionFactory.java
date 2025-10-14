package musicShop;

public class PercussionFactory extends InstrumentFactory{

    @Override
    public Instrument createInstrument(String type) {
        return switch (type.toLowerCase()){
            case "drumset" -> new DrumSet("Pearl", 5);
            case "bongo" -> new Bongo("Leather");
            default -> throw new IllegalStateException("Unexpected type: " + type.toLowerCase());
        };
    }
}
