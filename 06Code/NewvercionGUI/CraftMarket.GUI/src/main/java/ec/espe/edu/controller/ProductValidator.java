package ec.espe.edu.controller;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class ProductValidator {
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static boolean isValidStock(int stock) {
        return stock >= 0;
    }
}
