package ec.espe.edu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jorge Fuentes
 */
public class Inventory {

    private List<Product> allProducts = new ArrayList<>();
    private static final String CSV_DIRECTORY = "ventas/";
    private static final String PRODUCTS_FILE_PATH = CSV_DIRECTORY + "products.csv";

    public Inventory() {
        ensureDirectoryExists();
        try {
            openProductsFromCVS(PRODUCTS_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error al cargar el inventario: " + e.getMessage());
            // Si hay un error al cargar (ej. archivo corrupto), podrías intentar crear uno nuevo
            // o simplemente continuar con un inventario vacío, que es lo que hace actualmente.
        }
        // ******* AÑADIR ESTO: Asegurar que el archivo products.csv exista ******
        // Esto crea el archivo vacío si no existe después de intentar cargarlo
        File productsFile = new File(PRODUCTS_FILE_PATH);
        if (!productsFile.exists()) {
            try {
                if (productsFile.createNewFile()) {
                    System.out.println("Archivo '" + PRODUCTS_FILE_PATH + "' creado exitosamente por Inventory (estaba vacío).");
                } else {
                    System.err.println("Error: No se pudo crear el archivo '" + PRODUCTS_FILE_PATH + "'. Verifica los permisos.");
                }
            } catch (IOException e) {
                System.err.println("Error al intentar crear el archivo '" + PRODUCTS_FILE_PATH + "': " + e.getMessage());
            }
        }
        // **********************************************************************
    }

    private void ensureDirectoryExists() {
        File directory = new File(CSV_DIRECTORY);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directorio '" + CSV_DIRECTORY + "' creado exitosamente por Inventory.");
            } else {
                System.err.println("Error: No se pudo crear el directorio '" + CSV_DIRECTORY + "' por Inventory. Verifica los permisos.");
            }
        }
    }

    public void openProductsFromCVS(String fileRoute) throws IOException {
        allProducts.clear();
        File file = new File(fileRoute);

        if (!file.exists()) {
            System.out.println("El archivo de inventario '" + fileRoute + "' no existe. Se iniciará con un inventario vacío.");
            // No se lanza excepción aquí, se permite continuar con un inventario vacío
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.err.println("Advertencia: Línea de inventario con formato inválido (menos de 5 campos): " + line);
                    continue;
                }

                String id = parts[0].trim();
                String productName = parts[1].trim();
                float price = Float.parseFloat(parts[2].trim());
                int stock = Integer.parseInt(parts[3].trim());
                String artisanName = parts[4].trim();

                Product product = new Product(id, productName, price, stock, artisanName);
                allProducts.add(product);
            }
            System.out.println("Inventario cargado exitosamente desde: " + fileRoute);
        } catch (FileNotFoundException e) {
            System.err.println("Error: El archivo '" + fileRoute + "' no se encontró. " + e.getMessage());
            throw e; // Relanza la excepción para que el constructor la capture
        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico en el archivo '" + fileRoute + "': " + e.getMessage() + ". Revisa los valores de precio/stock.");
            // Aquí podrías decidir si quieres relanzar la excepción, loggear, o ignorar la línea
            throw new IOException("Error de formato en el archivo: " + fileRoute, e); // Para encapsular y relanzar como IOException
        } catch (IOException e) {
            System.err.println("Error inesperado al leer el archivo '" + fileRoute + "': " + e.getMessage());
            throw e;
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
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Product p : allProducts) {
            System.out.println("ID: " + p.getId() + " /Producto: " + p.getName() + " /Precio: " + p.getUnitPrice() + " /Stock: " + p.getStock() + " /Dueño: " + p.getOwner());
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