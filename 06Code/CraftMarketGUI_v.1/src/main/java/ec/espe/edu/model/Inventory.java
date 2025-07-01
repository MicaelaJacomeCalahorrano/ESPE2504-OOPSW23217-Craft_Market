package ec.espe.edu.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ec.espe.edu.utils.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Inventory {
    private List<Product> allProducts = new ArrayList<>();

    public Inventory() {
        loadProductsFromDatabase();
    }

    public void loadProductsFromDatabase() {
        allProducts.clear();
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("products");
        FindIterable<Document> documents = collection.find();

        for (Document doc : documents) {
            String id = doc.getString("id");
            String name = doc.getString("name");
            float unitPrice = doc.getDouble("unitPrice").floatValue(); // cuidado si está guardado como Double
            int stock = doc.getInteger("stock");
            String owner = doc.getString("owner");

            Product product = new Product(id, name, unitPrice, stock, owner);
            allProducts.add(product);
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
            System.out.println("ID: " + p.getId() + " / Producto: " + p.getName()
                    + " / Precio: $" + p.getUnitPrice() + " / Stock: " + p.getStock()
                    + " / Dueño: " + p.getOwner());
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
            System.out.println("ID: " + p.getId() + " / Nombre: " + p.getName()
                    + " / Precio: $" + p.getUnitPrice() + " / Stock: " + p.getStock());
        }
    }
}
