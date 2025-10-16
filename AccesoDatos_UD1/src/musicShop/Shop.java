package musicShop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {

    private final Map<String, Integer> stock = new HashMap<>(); // maybe enums for each instrument?


    public Shop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        stock.put("guitar", guitarStock);
        stock.put("bass", bassStock);
        stock.put("bongo", bongoStock);
        stock.put("drumset", drumSetStock);
    }


    public static Shop openShop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        return new Shop(guitarStock, bassStock, drumSetStock, bongoStock);
    }

    public void productService(String type) {
        System.out.println("Product information:");
        switch (type.toLowerCase()) {
            case "guitar" -> System.out.println("Fender Guitar. Available stock: " + stock.get("guitar"));
            case "bass" -> System.out.println("Ibanez Bass. Available stock: " + stock.get("bass"));
            case "drumset" -> System.out.println("Pearl 5 piece Drum Set. Available stock: " + stock.get("drumset"));
            case "bongo" -> new Bongo("Leather Bongos. Available stock: " + stock.get("bongo"));
            default -> throw new IllegalStateException("Unexpected type: " + type.toLowerCase());
        }
        ;
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

    public void restock(String product, int quantity){
        // put() would replace the amount
        // merge() adds the key if it does not exist yet
        // Integer::sum is (oldValue, newValue) -> oldValue + newValue.
        stock.merge(product.toLowerCase(), quantity, Integer::sum);
    }

}
