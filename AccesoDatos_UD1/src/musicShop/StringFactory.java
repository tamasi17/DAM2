package musicShop;

public class StringFactory extends InstrumentFactory{

    @Override
    public Instrument createInstrument(String type) {
        return switch (type.toLowerCase()){
            case "guitar" -> new Guitar("Fender", true); // usando lambdas por usar lambdas
            case "bass" -> new Bass("Ibanez", true);
            default -> throw new IllegalStateException("Unexpected type: " + type.toLowerCase());
        };
    }
}
