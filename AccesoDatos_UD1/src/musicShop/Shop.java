package musicShop;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private int guitarStock;
    private int bassStock;
    private int drumSetStock;
    private int bongoStock;

    private Shop(int guitarStock, int bassStock, int drumSetStock, int bongoStock){
        this.guitarStock = guitarStock;
        this.bassStock = bassStock;
        this.drumSetStock = drumSetStock;
        this.bongoStock = bongoStock;
    }

    public static Shop openShop(int guitarStock, int bassStock, int drumSetStock, int bongoStock){
        return new Shop(guitarStock, bassStock, drumSetStock, bongoStock);
    }

    public void productService(String type){
        System.out.println("Product information:");
        switch (type.toLowerCase()){
            case "guitar" -> System.out.println("Fender Guitar. Available stock: " + this.bassStock);
            case "bass" -> System.out.println("Ibanez Bass. Available stock: " + this.bassStock);
            case "drumset" -> System.out.println("Pearl 5 piece Drum Set. Available stock: " + this.drumSetStock);
            case "bongo" -> new Bongo("Leather Bongos. Available stock: " + this.bongoStock);
            default -> throw new IllegalStateException("Unexpected type: " + type.toLowerCase());
        };
    }

}
