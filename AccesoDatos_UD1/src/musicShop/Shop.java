package musicShop;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private final Map<Instrument, Integer> stock = new HashMap<>(); // maybe enums for each instrument?
    private File stockArchive;

    private Shop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        stock.put(new Guitar("Fender",true), guitarStock);
        stock.put(new Bass("Ibanez", true), bassStock);
        stock.put(new Bongo("Leather"), bongoStock);
        stock.put(new DrumSet("Pearl", 5), drumSetStock);
    }


    public static Shop openShop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        // could implement Singleton logic here
        return new Shop(guitarStock, bassStock, drumSetStock, bongoStock);
    }

    public void productService(Instrument type) {
        System.out.println("Product information:\n" + type.toString());
    }

    public boolean buy(String type, int quantity) {
        // mirar computeIfPresent()
        boolean result = false;
        if (stock.get(type) > 0) {
            stock.remove(type, -1);
            result = true;
        }
        return result;
    }

    public void restock(Instrument type, int quantity){
        // put() would replace the amount
        // merge() adds the key if it does not exist yet
        // Integer::sum is (oldValue, newValue) -> oldValue + newValue.
        stock.merge(type, quantity, Integer::sum);
    }

    private void writeToDocument(Instrument type, int quantity){
        // Binary document here
        // int productID + Instrument type + double price

    }

    public Integer getStock(Instrument type) {
        return stock.get(type);
    }
}
