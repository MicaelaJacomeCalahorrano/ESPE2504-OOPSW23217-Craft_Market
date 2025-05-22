package ec.espe.edu.utils;

/**
 *
 * @author Micaela Jacome DESKTOP-46VMNHU ESPE
 */

import java.io.FileWriter;
import java.io.IOException;

public class CsvUtilsPrice {
    public static void save(String productName, float newPrice){
        try (FileWriter writer = new FileWriter("product_prices.csv", true)){
            writer.append(productName)
                    .append(",")
                    .append(String.valueOf(newPrice))
                    .append("\n");
        } catch (IOException e) {
            System.out.println("Error writing price to CSV: " + e.getMessage());
        }
    }
}
