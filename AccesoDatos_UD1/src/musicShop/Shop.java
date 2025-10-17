package musicShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Shop {

    private final Map<Instrument, Integer> stock = new HashMap<>(); // maybe enums for each instrument?

    private final String stockArchivePath = "/stock/stockArchive.bin";

    private Shop(int guitarStock, int bassStock, int drumSetStock, int bongoStock) {
        stock.put(new Guitar("Fender", true), guitarStock);
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

    public void restock(Instrument type, int quantity) {
        // put() would replace the amount
        // merge() adds the key if it does not exist yet
        // Integer::sum is (oldValue, newValue) -> oldValue + newValue.
        stock.merge(type, quantity, Integer::sum);
    }

    public void writeToRaf(Instrument type) {
        // Binary document here, writes to stockArchive.

        File parent = new File("stock");
        if (!parent.exists()) parent.mkdirs();


        try (RandomAccessFile raf = new RandomAccessFile(stockArchivePath, "rw")) {

            // If there is already info written in the stockArchive, go to the end of the file
            if (raf.length() > 0) {
                raf.seek(raf.length());
            }

            raf.write(type.getProductID()); // int ID = 4 bytes
            raf.writeDouble(type.getPrice()); // double price = 8 bytes
            raf.writeChars(type.getCode()); // four char code, each char 2 bytes = 8 bytes

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found. " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when accessing stock archive.");
        }

    }

    public void readRaf(){

        try (RandomAccessFile raf = new RandomAccessFile(stockArchivePath, "r")) {

            while (raf.getFilePointer()<raf.length()) {

                int id = raf.readInt();
                double price = raf.readDouble();
                String code = raf.readUTF();     // COMO SÉ CUANTO VA A AVANZAR? NECESITO UN RAF COMO ATRIBUTO?

                System.out.println(id + ": " + code + " - " + price);
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("File not found" + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when accessing stock archive.");
        }

    }


    public Integer getStock(Instrument type) {
        return stock.get(type);
    }
}
