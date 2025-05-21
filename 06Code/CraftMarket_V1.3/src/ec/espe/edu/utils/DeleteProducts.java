
package ec.espe.edu.utils;

/**
 *
 * @author Micaela Jacome DESKTOP-46VMNHU ESPE
 */


import java.io.*;
import java.util.*;

public class DeleteProducts {

    public static void deleteProduct(String productId) {
        // Lista de archivos CSV donde se guardan los productos
        String[] filePaths = {
            "product_ids.csv",
            "product_stocks.csv",
            "product_prices.csv"
        };

        for (String filePath : filePaths) {
            deleteFromFile(filePath, productId);
        }

        System.out.println("Product with ID " + productId + " deleted successfully.");
    }

    private static void deleteFromFile(String filePath, String productId) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(productId)) { // Filtra el producto a eliminar
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + filePath + ": " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
