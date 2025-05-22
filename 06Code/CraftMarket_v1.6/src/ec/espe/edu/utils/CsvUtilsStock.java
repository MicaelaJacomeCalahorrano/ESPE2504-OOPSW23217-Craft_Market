package ec.espe.edu.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CsvUtilsStock {
    public static void save(String productName, int newStock) {
    if (productName == null || productName.trim().isEmpty()) {
        System.out.println("Error: Nombre de producto inválido.");
        return;
    }

    if (newStock < 0) {
        System.out.println("Error: El stock no puede ser negativo.");
        return;
    }

    try (FileWriter writer = new FileWriter("product_stocks.csv", true)) {
        writer.append(productName)
              .append(",")
              .append(String.valueOf(newStock))
              .append("\n");
    } catch (IOException e) {
        System.out.println("Error al escribir en el CSV: " + e.getMessage());
    }
}

}
