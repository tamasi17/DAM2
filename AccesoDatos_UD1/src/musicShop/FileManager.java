package musicShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

public class FileManager {

    private static int recordCounter = 0;


    public void writeToRaf(Instrument type, String path) {

        File parent = new File("stock");
        if (!parent.exists()) parent.mkdirs();

        File stockArchive = null;
        try {
            stockArchive = new File(path);
            stockArchive.createNewFile();
        } catch (IOException ioe) {
            System.err.println("Error creating the binary file: " + stockArchive.getAbsolutePath());
        }

        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {


            // If there is already info written in the stockArchive, go to the end of the file
            if (raf.length() > 0) {
                raf.seek(raf.length());
            }

            recordCounter++;

            raf.writeInt(recordCounter); // int = 4 bytes
            raf.writeInt(type.getProductID()); // int ID = 4 bytes
            raf.writeDouble(type.getPrice()); // double price = 8 bytes
            raf.writeChars(type.getCode()); // four char code, each char 2 bytes = 8 bytes
            // Total = 24 bytes

        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not write: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when writing to stock archive.");
        }

    }

    /**
     * Reads the whole content of a binary file
     *
     * @param path to the binary file to be read
     */
    public void readRaf(String path) {

        System.out.println("\nReading sales from binary file: " + path + "\n");


        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {

            System.out.println("Sales: " + LocalDate.now() + "\n");
            while (raf.getFilePointer() < raf.length()) {

                int record = raf.readInt();
                int id = raf.readInt();
                double price = raf.readDouble();
                StringBuilder codeBuilder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    codeBuilder.append(raf.readChar());
                }
                String code = codeBuilder.toString();

                System.out.println("[" + record + "] " + id + ": " + code + " - " + price + " euros.");
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not read: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when reading stock archive.");
        }
    }

    /**
     * Reads a specific record of a binary file
     *
     * @param path to the binary file to be read
     */
    public String readRaf(String path, int record) {
        System.out.println("\nReading record " + record + " from binary file: " + path + "\n");

        String outcome = "Empty record";

        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {

            // Accesing the record directly
            long recordSize = 24;
            long position = (record - 1) * recordSize;
            // If position is outside the file, returns "Empty record".
            if (position < raf.length()) {
                raf.seek(position);

                // Reading data
                int recordId = raf.readInt();
                int saleId = raf.readInt();
                double price = raf.readDouble();
                StringBuilder codeBuilder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    codeBuilder.append(raf.readChar());
                }
                String code = codeBuilder.toString();

                outcome = "[" + recordId + "] " + saleId + ": " + code + " - " + price + " euros.";
            }

        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not read specific record: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when reading specific record.");
        }

        return outcome;
    }

    public void modifyRaf(String path, int record, Instrument type) {
        System.out.println("\nModifying sales from binary file: " + path + "\n");

        try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {

            // Accesing the record directly
            long recordSize = 24;
            long position = (record - 1) * recordSize;
            if (position < raf.length()) {
                raf.seek(position);

                // Modifying data
                raf.writeInt(record); // int = 4 bytes
                raf.writeInt(type.getProductID()); // int ID = 4 bytes
                raf.writeDouble(type.getPrice()); // double price = 8 bytes
                raf.writeChars(type.getCode());
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Binary file not found, could not modify specific record: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            System.err.println("I/O error occurred when modifying specific record.");
        }
    }

}
