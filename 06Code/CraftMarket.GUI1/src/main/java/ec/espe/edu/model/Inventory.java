package ec.espe.edu.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
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

    private static final MongoDatabase db = MongoConnection.getDatabase();
    private static final MongoCollection<Document> collection = db.getCollection("products");

    public Inventory() {
        loadProductsFromDatabase();
    }

    public void loadProductsFromDatabase() {
        allProducts.clear();
        FindIterable<Document> documents = collection.find();

        for (Document doc : documents) {
            String id = doc.getString("id");
            String name = doc.getString("name");

            float unitPrice;
            Object priceObj = doc.get("unitPrice");

            switch (priceObj) {
                case Double aDouble -> unitPrice = aDouble.floatValue();
                case Integer integer -> unitPrice = integer.floatValue();
                default -> unitPrice = 0.0f; 
            }

            int stock = doc.getInteger("stock", 0);
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
            System.out.println("ID: " + p.getId()
                    + " / Producto: " + p.getName()
                    + " / Precio: $" + p.getUnitPrice()
                    + " / Stock: " + p.getStock()
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
            System.out.println("ID: " + p.getId()
                    + " / Nombre: " + p.getName()
                    + " / Precio: $" + p.getUnitPrice()
                    + " / Stock: " + p.getStock());
        }
    }
}
