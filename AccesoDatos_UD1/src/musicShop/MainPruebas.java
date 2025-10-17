package musicShop;

import log4Mats.LogManager;
import log4Mats.Logger;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainPruebas {


    private static final File XML = new File("config\\configlog.xml");
    private static final Logger LOGGER = LogManager.getLogger(XML);
    private static final Shop MUSIC_SHOP = new Shop(7,4, 3,6);
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    static void main() {

        LOGGER.getLogLevel(); // uncomment to check priority level
        Guitar fender = new Guitar("Fender", true);

        System.out.println("Stock guitarras: " + MUSIC_SHOP.getStock(fender));


        startBrowsing();


        browsingProducts(fender); // opens shop

        addingToCart(fender);

        goToPayment(fender, 1200); // if product is bought, writes to binary document

        randomErrorOccurs();

        Bass ibanez = new Bass("Ibanez", true);

        addingToCart(ibanez);

        goToPayment(ibanez, 1400);

        restock(ibanez, 3);

        closeShop(); // reads sales from binary document


    }

    public static void startBrowsing(){
        LOGGER.info("Application", "Shop opened at " + LocalTime.now().format(FORMATTER));
    }

    public static void browsingProducts(Instrument type){
        MUSIC_SHOP.productService(type);
        LOGGER.trace("ProductService", "Fetching product details...");
    }

    private static void addingToCart(Instrument type) {
        System.out.println("Added to cart: " + type.getCode());
        LOGGER.debug("InventoryService", "Stock before sale: " + "product (" + "units" + ")");
        LOGGER.trace("CartManager", "Applying discount: -10%");
    }


    private static void randomErrorOccurs() {
        LOGGER.fatal("Application", "Configuration file 'app.properties' not found - aborting startup");
        LOGGER.fatal("SystemCore", "Unhandled exception in main thread — terminating application");
    }


    private static Instrument goToPayment(Instrument type, int amountPaid) {

        //  SIEMPRE SALE EL STOCK TYPE NULL


        if(MUSIC_SHOP.getStock(type) == null){
            LOGGER.warn("OrderService", "No stock for required instrument");
            return null;
        }

        Instrument sale = null;

        LOGGER.debug("PaymentService", "Calculated total: " + type.getPrice());

        if (amountPaid<type.getPrice()){
            LOGGER.error("OrderService", "Payment rejected");
        } else {
            sale = type;
            productBought(sale);
        }
        return sale;
    }

    /**
     * Method that confirms a sale, writes to binary document calling writeToRaf()
     * @param type
     */
    private static void productBought(Instrument type) {
        MUSIC_SHOP.writeToRaf(type);



         if (MUSIC_SHOP.getStock(type) < 3) {
            LOGGER.warn("InventoryService", "Stock low for: " + type.getCode() + ". " +
                    "Less than three units left");
            MUSIC_SHOP.restock(type,4);
         }
        }




    /** Restocks the indicated instrument.
     * Returns true if restock was succesful, false if not.
     * @param type
     * @return
     */
    private static boolean restock(Instrument type, int quantity) {
        MUSIC_SHOP.restock(type, quantity);
        LOGGER.info("InventoryService", "Restocked product: " + type.getCode());
        return true;
    }


    private static void closeShop() {
        // Reading sales from the binary document
        MUSIC_SHOP.readRaf();
        LOGGER.info("Application", "Shop closed at " + LocalTime.now().format(FORMATTER));
    }
}
