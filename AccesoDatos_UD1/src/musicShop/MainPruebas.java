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

        LOGGER.getLogLevel(); // para confirmar

        browsingProducts("guitar");

        addingToCart("guitar");

        productBought(goToPayment("guitar", 1200));

        randomErrorOccurs();

        addingToCart("bass");

        productBought(goToPayment("guitar", 1400));

        restock("bass");

        closeShop();


    }

    private static void closeShop() {
        LOGGER.info("Application", "Shop closed at " + LocalTime.now().format(FORMATTER));
    }

    private static void randomErrorOccurs() {
        LOGGER.fatal("Application", "Configuration file 'app.properties' not found - aborting startup");
        LOGGER.fatal("SystemCore", "Unhandled exception in main thread — terminating application");
    }

    /** Restocks the indicated instrument.
     * Returns true if restock was succesful, false if not.
     * @param type
     * @return
     */
    private static boolean restock(String type) {
        LOGGER.info("InventoryService", "Restocked product: " + "product");
        return true;
    }

    private static String goToPayment(String type, int amountPaid) {
        // CALCULAR TOTAL AQUI ---- DALE UN INT TOTAL A LOS INSTRUMENTOS
        String sale = null;
        LOGGER.debug("PaymentService", "Calculated total: subtotal=1199.99€, tax=252.00€, shipping=0.00€");
        if (amountPaid<1300){
        LOGGER.error("OrderService", "Payment rejected");
        } else {
         sale = type;
        }
        return sale;
    }

    private static void productBought(String type) {
        // if (type.stock < 3) { AÑADIR WARN:
        LOGGER.warn("InventoryService", "Stock low for: " + "product" + ". Few units left");
    }

    private static void addingToCart(String type) {
        System.out.println("Added to cart: " + type);
        LOGGER.debug("InventoryService", "Stock before sale: " + "product (" + "units" + ")");
        LOGGER.trace("CartManager", "Applying discount: -10%");
    }

    public static void browsingProducts(String type){
        MUSIC_SHOP.productService(type);
        LOGGER.info("Application", "Shop opened at " + LocalTime.now().format(FORMATTER));
        LOGGER.trace("ProductService", "Fetching product details...");
    }

}
