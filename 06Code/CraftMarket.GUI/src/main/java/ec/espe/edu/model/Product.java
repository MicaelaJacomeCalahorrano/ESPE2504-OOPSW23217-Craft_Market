package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.espe.edu.model.utils.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Micaela Jacome CraftMarket DCCO ESPE
 */
public class Product {

    private int id;
    private String name;
    private double unitPrice;
    private int stock;
    private String owner;

    public Product(int id, String name, double unitPrice, int stock, String owner) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getOwner() {
        return owner;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public static List<Product> getAllProducts() {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        List<Product> products = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                Number unitPriceNumber = doc.get("unitPrice", Number.class);
                float unitPrice = unitPriceNumber.floatValue();

                products.add(new Product(
                        doc.getInteger("id"),
                        doc.getString("name"),
                        unitPrice,
                        doc.getInteger("stock"),
                        doc.getString("owner")
                ));
            }
        } finally {
            cursor.close();
        }
        return products;
    }

    public static void addProduct(Scanner scanner, String artisanName) {
        System.out.print("ID: ");
        int id = scanner.nextInt();

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Precio: ");
        float price = scanner.nextFloat();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(id, name, price, stock, artisanName);
        addProduct(product);
    }

    public static void showProducts() {
        List<Product> products = getAllProducts();
        for (Product p : products) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getName());
            System.out.println("Precio: $" + p.getUnitPrice());
            System.out.println("Stock: " + p.getStock());
            System.out.println("Dueño: " + p.getOwner());
            System.out.println("-------------------------");
        }
    }

    public static void editProductPrice(Scanner scanner, String productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");

        System.out.print("Nuevo precio: ");
        float newPrice = scanner.nextFloat();
        scanner.nextLine();

        collection.updateOne(new Document("id", productId),
                new Document("$set", new Document("unitPrice", newPrice)));
    }

    public static void editProductStock(Scanner scanner, String productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");

        System.out.print("Nuevo stock: ");
        int newStock = scanner.nextInt();
        scanner.nextLine();

        collection.updateOne(new Document("id", productId),
                new Document("$set", new Document("stock", newStock)));
    }

    public static void addProduct(Product product) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        Document doc = new Document("id", product.getId())
                .append("name", product.getName())
                .append("unitPrice", product.getUnitPrice())
                .append("stock", product.getStock())
                .append("owner", product.getOwner());
        collection.insertOne(doc);
    }

    public static void updateProductStock(int productId, int newStock) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        collection.updateOne(new Document("id", productId),
                new Document("$set", new Document("stock", newStock)));
    }

    public static void updateProductPrice(String productId, float newPrice) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        collection.updateOne(new Document("id", productId),
                new Document("$set", new Document("unitPrice", newPrice)));
    }

    public static void deleteProduct(String productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        collection.deleteOne(new Document("id", productId));
    }

    public static Product findById(int productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("products");
        Document doc = collection.find(new Document("id", productId)).first();
        if (doc == null) {
            return null;
        }

        Number unitPriceNumber = doc.get("unitPrice", Number.class);
        float unitPrice = unitPriceNumber.floatValue();

        return new Product(
                doc.getInteger("id"),
                doc.getString("name"),
                unitPrice,
                doc.getInteger("stock"),
                doc.getString("owner")
        );
    }

}
