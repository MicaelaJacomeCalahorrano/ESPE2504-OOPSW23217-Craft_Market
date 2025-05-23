package ec.espe.edu.model;

import ec.espe.edu.utils.CsvProduct;
import static ec.espe.edu.utils.CsvProduct.readAllProducts;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Micaela Jacome
 */

public class Product {

    private String id;
    private String name;
    private float unitPrice;
    private int stock;
    private String owner;
  
    public Product(String id, float unitPrice, int stock, String name) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.owner = owner;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(float unitPrice) { this.unitPrice = unitPrice; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public void updateStock(int newStock) {
        this.stock = newStock;
    }

    public String toCSV() {
        return name + "," + unitPrice + "," + stock + "," + owner;
    }
    

    public String getDetails() {
        return "Product: " + name + ", Price: $" + unitPrice + ", Stock: " + stock + ", Owner: " + owner;
    }

    public static void showProducts() {
        try (Scanner fileScanner = new Scanner(new File("product_stocks.csv"))) {
            System.out.println("Productos registrados:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                if (data.length < 3) {
                    System.out.println("Error: línea corrupta en CSV -> " + line);
                    continue;
                }

                System.out.println("ID: " + data[0] + " | Nombre: " + data[1] + " | Stock: " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void addProduct(Scanner scanner, String ownerName) {
         System.out.println("=== AÑADIR PRODUCTO ===");

    System.out.print("Nombre del producto: ");
    String name = scanner.nextLine();

    System.out.print("Precio: ");
    float price = Float.parseFloat(scanner.nextLine());

    System.out.print("Stock inicial: ");
    int stock = Integer.parseInt(scanner.nextLine());
    Product product = new Product(name, price, stock, ownerName);
    String newId = CsvProduct.generateNewId();
    CsvProduct.saveProduct(product, newId);
    System.out.println("Producto añadido con ID: " + newId);
    }


    public static void editProductPrice(Scanner scanner, String productId) {
    List<String[]> products = CsvProduct.readAllProducts();
    boolean found = false;

        for (String[] product : products) {
            if (product[0].equals(productId)) {
                System.out.print("Nuevo precio: ");
                String newPrice = scanner.nextLine();
                product[2] = newPrice; // índice 2 es el precio
                found = true;
                break;
            }
        }

        if (found) {
            CsvProduct.overwriteAllProducts(products);
            System.out.println("Precio actualizado correctamente.");
        } else {
            System.out.println("Producto con ID " + productId + " no encontrado.");
        }
    }
    
    public static void editProductStock(Scanner scanner, String productId) {
    List<String[]> products = CsvProduct.readAllProducts();
    boolean found = false;

        for (String[] product : products) {
            if (product[0].equals(productId)) {
                System.out.print("Nuevo stock: ");
                String newStock = scanner.nextLine();
                product[3] = newStock; // índice 3 es el stock
                found = true;
                break;
            }
        }

        if (found) {
            CsvProduct.overwriteAllProducts(products);
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("Producto con ID " + productId + " no encontrado.");
        }
    }
    
    public static void deleteProduct() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del producto que desea eliminar: ");
        String id = scanner.nextLine();
        CsvProduct.deleteProductById(id);
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
