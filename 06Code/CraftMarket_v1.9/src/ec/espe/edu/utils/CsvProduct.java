package ec.espe.edu.utils;

/**
 *
 * @author jorge
 */

import ec.espe.edu.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvProduct {
    private static final String FILE_PATH = "products.csv";

    public static List<String[]> readAllProducts() {
        List<String[]> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(",");
                products.add(productData);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo de productos: " + e.getMessage());
        }

        return products;
    }

    public static void overwriteAllProducts(List<String[]> updatedProducts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (String[] product : updatedProducts) {
                bw.write(String.join(",", product));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir el archivo de productos: " + e.getMessage());
        }
    }
    public static String generateNewId() {
        int lastId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    try {
                        int currentId = Integer.parseInt(parts[0]);
                        if (currentId > lastId) {
                            lastId = currentId;
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (FileNotFoundException e) {
            // Si no existe, es el primer producto
            return String.format("%03d", 1);
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return String.format("%03d", lastId + 1);
    }
    public static void saveProduct(Product product, String id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(id + "," + product.getName() + "," + product.getUnitPrice() + "," + product.getStock() + "," + product.getOwner());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }
    public static List<Product> loadAllProducts() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id= parts[0];
                    String name = parts[1];
                    float price = Float.parseFloat(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    String owner = parts[4];

                    products.add(new Product(id, name, price, stock, owner));

                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }

        return products;
    }
    public static void deleteProductById(String productId) throws FileNotFoundException, IOException {
        String filePath = "products.csv";
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length > 0 && fields[0].equals(productId)) {
                found = true;
                continue;
            }
            updatedLines.add(line);
        }
            if (!found) {
            System.out.println("Producto con ID " + productId + " no encontrado.");
            return;
        }

    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        for (String updatedLine : updatedLines) {
            writer.write(updatedLine);
            writer.newLine();
        }
        System.out.println("Producto eliminado correctamente.");
    } catch (IOException e) {
        System.out.println("Error al escribir el archivo: " + e.getMessage());

        }
    
    }
    
    
}
