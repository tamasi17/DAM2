package musicShop;

/**
 * Factoria de instrumentos siguiendo el modelo de refactoring.guru
 * Para este ejemplo habria sido mas sencillo una SimpleFactory.
 */
public abstract class InstrumentFactory {

    public abstract Instrument createInstrument(String type);
}
