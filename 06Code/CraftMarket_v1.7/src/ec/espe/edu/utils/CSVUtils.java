package ec.espe.edu.utils;
import ec.espe.edu.model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
     private static final String PRODUCTS_FILE = "products.csv";

    // Guardar lista de productos a CSV
    public static void saveProducts(List<Product> products) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PRODUCTS_FILE))) {
            for (Product p : products) {
                writer.println(p.toCSV());
            }
            System.out.println("Products saved successfully.");
        } catch (IOException e) {
            System.out.println(" Error saving products: " + e.getMessage());
        }
    }

    // Cargar productos desde CSV
    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    float price = Float.parseFloat(parts[1]);
                    int stock = Integer.parseInt(parts[2]);
                    String owner = parts[3];
                    products.add(new Product(name, price, stock, owner));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, returning empty product list.");
        } catch (IOException e) {
            System.out.println("Error reading products: " + e.getMessage());
        }
        return products;
    }
}
