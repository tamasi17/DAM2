package musicShop;

import log4Mats.LogManager;
import log4Mats.Logger;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainPruebas {

    // COMO SE LE PASA UN .CLASS EN VEZ DE UN .XML ?
//    private static final Logger logger = LogManager.getLogger(Log4jEjemplo.class);

    private static final File XML = new File("config\\configlog.xml");
    private static final Logger LOGGER = LogManager.getLogger(XML);
    private static final Shop MUSIC_SHOP = Shop.openShop(7,4, 3,6);
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    static void main() {

        LOGGER.getLogLevel(); // uncomment to check priority level

        startBrowsing();

        browsingProducts(new Guitar("Fender", true)); // opens shop

        addingToCart("guitar");

        goToPayment("guitar", 1200);

        randomErrorOccurs();

        addingToCart("bass");

        goToPayment("bass", 1400);

        restock(new Bass("Ibanez", true), 3);

        closeShop();


    }

    public static void startBrowsing(){
        LOGGER.info("Application", "Shop opened at " + LocalTime.now().format(FORMATTER));
    }

    public static void browsingProducts(Instrument type){
        MUSIC_SHOP.productService(type);
        LOGGER.trace("ProductService", "Fetching product details...");
    }

    private static void addingToCart(Instrument type) {
        System.out.println("Added to cart: " + type.getClass());
        LOGGER.debug("InventoryService", "Stock before sale: " + "product (" + "units" + ")");
        LOGGER.trace("CartManager", "Applying discount: -10%");
    }


    private static void randomErrorOccurs() {
        LOGGER.fatal("Application", "Configuration file 'app.properties' not found - aborting startup");
        LOGGER.fatal("SystemCore", "Unhandled exception in main thread — terminating application");
    }


    private static String goToPayment(String type, int amountPaid) {
        // CALCULAR TOTAL AQUI ---- DALE UN INT PRECIO A LOS INSTRUMENTOS
        String sale = null;
        LOGGER.debug("PaymentService", "Calculated total: subtotal=1199.99€, tax=252.00€, shipping=0.00€");
        if (amountPaid<1300){
            LOGGER.error("OrderService", "Payment rejected");
        } else {
            sale = type;
            productBought(sale);
        }
        return sale;
    }

    private static void productBought(Instrument type) {

         if (MUSIC_SHOP.getStock(type) < 3) {
            LOGGER.warn("InventoryService", "Stock low for: " + type.getClass() + ". " +
                    "Less than three units left");
         }

    }


    /** Restocks the indicated instrument.
     * Returns true if restock was succesful, false if not.
     * @param type
     * @return
     */
    private static boolean restock(Instrument type, int quantity) {
        MUSIC_SHOP.restock(type, quantity);
        LOGGER.info("InventoryService", "Restocked product: " + type.getClass());
        return true;
    }


    private static void closeShop() {
        LOGGER.info("Application", "Shop closed at " + LocalTime.now().format(FORMATTER));
    }
}
