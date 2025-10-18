package musicShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    private final Map<Instrument, Integer> stock = new HashMap<>(); // maybe enums for each instrument?

    private final String stockArchivePath = "stock\\stockArchive.bin";

    private final FileManager fileManager = new FileManager();

    public Shop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        stock.put(new Guitar("Fender Jazzmaster", true), guitarStock);
        stock.put(new Bass("Ibanez GSRM", true), bassStock);
        stock.put(new Bongo("Leather"), bongoStock);
        stock.put(new DrumSet("Pearl", 5), drumSetStock);
    }


    public void productService(Instrument type) {
        System.out.println("\nProduct information:\n" + type.toString());
    }

    public boolean buy(Instrument type) {

        // mirar computeIfPresent() !!!!!

        boolean result = false;
        if (stock.get(type) == null) return result;

        if (stock.get(type) > 0) {
            stock.remove(type, -1);
            result = true;
        }
        return result;
    }

    public void restock(Instrument type, int quantity) {
        // put() would replace the amount
        // merge() adds the key if it does not exist yet
        // Integer::sum is (oldValue, newValue) -> oldValue + newValue.
        stock.merge(type, quantity, Integer::sum);
    }

    public void writeToRaf(Instrument type) {
        fileManager.writeToRaf(type, stockArchivePath);
    }

    public void readRaf(){
        fileManager.readRaf(stockArchivePath);
    }

    public String readRaf(int record){
        String saleInfo = fileManager.readRaf(stockArchivePath, record);
        return saleInfo;
    }

    public void modRaf(int record, Instrument type){
        fileManager.modifyRaf(stockArchivePath, record, type);
    }

    public Integer getStock(Instrument type) {
        Integer stockActual = stock.get(type);
        stockActual = stockActual == null ? 9999 : stockActual;
        return stockActual;
    }

    public String getFullStock(){
        return stock.toString();
    }
}
