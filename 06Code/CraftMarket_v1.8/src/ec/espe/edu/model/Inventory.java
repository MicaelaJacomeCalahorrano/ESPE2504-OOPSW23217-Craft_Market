package ec.espe.edu.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jorge Fuentes
 */

public class Inventory {

    private List<Product> allProducts = new ArrayList<>();

    public Inventory() {
        try {
            openProductsFromCVS("products.csv");
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
        }
    }

    public void openProductsFromCVS(String fileRoute) throws IOException {
        allProducts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileRoute))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5)
                    continue;

                String id = parts[0];
                String productName = parts[1];
                float price = Float.parseFloat(parts[2]);
                int stock = Integer.parseInt(parts[3]);
                String artisanName = parts[4];

                Product product = new Product(id, productName, price, stock, artisanName);
                allProducts.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo..." + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Product> getProductsByArtisan(Artisan artisan) {
        return allProducts.stream()
                .filter(p -> p.getOwner().equalsIgnoreCase(artisan.getName()))
                .collect(Collectors.toList());
    }

    public void showGeneralInventory() {
        System.out.println("\n----- Inventario General -----");
        if (allProducts.isEmpty()) {
            System.out.println("No hay productos registrados");
            return;
        }

        for (Product p : allProducts) {
            System.out.println("ID: " + p.getId() +
                    " /Producto: " + p.getName() +
                    " /Precio: " + p.getUnitPrice() +
                    " /Stock: " + p.getStock() +
                    " /dueño: " + p.getOwner());
        }
    }

    public void showPersonalInventory(Artisan artisan) {
        System.out.println("\n--- MI INVENTARIO ---");
        List<Product> productos = getProductsByArtisan(artisan);
        if (productos.isEmpty()) {
            System.out.println("No tienes productos registrados.");
            return;
        }
        for (Product p : productos) {
            System.out.println("ID: " + p.getId() + " /Nombre: " + p.getName() + " /Precio: " + p.getUnitPrice()
                    + " /Stock: " + p.getStock());
        }
    }
}
