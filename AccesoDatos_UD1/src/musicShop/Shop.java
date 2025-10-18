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
        // mirar computeIfPresent()
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

        File parent = new File("stock");
        if (!parent.exists()) parent.mkdirs();

        File stockArchive = null;
        try {
            stockArchive = new File(stockArchivePath);
            stockArchive.createNewFile();
        } catch (IOException ioe) {
            System.err.println("Error creating the binary file: " + stockArchive.getAbsolutePath());
        }

        try (RandomAccessFile raf = new RandomAccessFile(stockArchivePath, "rw")) {


            // If there is already info written in the stockArchive, go to the end of the file
            if (raf.length() > 0) {
                raf.seek(raf.length());
            }


            raf.writeInt(type.getProductID()); // int ID = 4 bytes
            raf.writeDouble(type.getPrice()); // double price = 8 bytes
            raf.writeChars(type.getCode()); // four char code, each char 2 bytes = 8 bytes

        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not write: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when writing to stock archive.");
        }

    }

    public void readRaf(){

        System.out.println("\nReading sales from binary file: " + stockArchivePath + "\n");


        try (RandomAccessFile raf = new RandomAccessFile(stockArchivePath, "r")) {

            System.out.println("Sales: " + LocalDate.now() + "\n");
            while (raf.getFilePointer()<raf.length()) {

                int id = raf.readInt();
                double price = raf.readDouble();
                StringBuilder codeBuilder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    codeBuilder.append(raf.readChar());
                }
                String code = codeBuilder.toString();

                System.out.println(id + ": " + code + " - " + price);
            }


        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not read: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when reading stock archive.");
        }

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
