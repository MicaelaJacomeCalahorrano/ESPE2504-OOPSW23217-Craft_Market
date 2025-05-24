package ec.espe.edu.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaac
 */
public class SalesReport {
    private String productName;
    private float unitPrice;
    private int quantity;
    private float total;
    private String artisanName;

    private static final String CSV_DIRECTORY = "ventas/";

    public SalesReport(String productName, float unitPrice, int quantity, float total, String artisanName) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.artisanName = artisanName;
    }

    public String toCSV() {
        return productName + "," + unitPrice + "," + quantity + "," + total + "," + artisanName;
    }

    public String getDisplayString() {
        return "Product: " + productName + " | Price: " + unitPrice + " | Quantity: " + quantity +
                " | Total: " + total + " | Artisan: " + artisanName;
    }

    public static void registerSale(Inventory inventory, String id, int soldQuantity) {
        inventory.showGeneralInventory();

        List<Product> products = inventory.getAllProducts();
        Product productToSell = null;

        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) {
                productToSell = p;
                break;
            }
        }

        if (productToSell == null) {
            System.out.println("============== Producto no encontrado. ==============");
            return;
        }
        if (soldQuantity > productToSell.getStock()) {
            System.out.println("============== No hay suficiente stock para esta venta. ==============");
            return;
        }

        float price = productToSell.getUnitPrice();
        float total = price * soldQuantity;
        String productName = productToSell.getName();
        String artisanName = productToSell.getOwner();

        productToSell.setStock(productToSell.getStock() - soldQuantity);

        SalesReport sale = new SalesReport(productName, price, soldQuantity, total, artisanName);
        saveSaleToFile(sale);

        try {
            saveInventoryToFile(products);
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario actualizado: " + e.getMessage());
        }

        System.out.println("Venta registrada correctamente.");
    }

    private static void saveSaleToFile(SalesReport sale) {
        LocalDate today = LocalDate.now();
        String fileName = CSV_DIRECTORY + "ventade_" + today.toString() + ".csv";

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(sale.toCSV() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar la venta: " + e.getMessage());
        }
    }

    private static void saveInventoryToFile(List<Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(CSV_DIRECTORY + "products.csv")) {
            for (Product p : products) {
                writer.write(p.getId() + "," +
                        p.getName() + "," +
                        p.getUnitPrice() + "," +
                        p.getStock() + "," +
                        p.getOwner() + "\n");
            }
        }
    }
}
