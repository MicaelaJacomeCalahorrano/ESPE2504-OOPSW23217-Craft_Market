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
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        List<Product> products = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                Integer id = doc.getInteger("Id");
                String name = doc.getString("Producto");
                Double price = doc.getDouble("Precio");
                Integer stock = doc.getInteger("Stock");
                String owner = doc.getString("Artesano");

                if (id != null && name != null && price != null && stock != null && owner != null) {
                    products.add(new Product(id, name, price, stock, owner));
                }
            }
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
         MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");

        System.out.print("Nuevo precio: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        collection.updateOne(new Document("Id", productId),
                new Document("$set", new Document("Precio", newPrice)));
    }

    public static void editProductStock(Scanner scanner, String productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");

        System.out.print("Nuevo stock: ");
        int newStock = scanner.nextInt();
        scanner.nextLine();

        collection.updateOne(new Document("Id", productId),
                new Document("$set", new Document("Stock", newStock)));
    }

    public static void addProduct(Product product) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        Document doc = new Document("Id", product.getId())
                .append("Producto", product.getName())
                .append("Precio", product.getUnitPrice())
                .append("Stock", product.getStock())
                .append("Artesano", product.getOwner());
        collection.insertOne(doc);
    }

    public static void updateProductStock(int productId, int newStock) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        collection.updateOne(new Document("Id", productId),
                new Document("$set", new Document("Stock", newStock)));
    }

    public static void updateProductPrice(String productId, float newPrice) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        collection.updateOne(new Document("Id", productId),
                new Document("$set", new Document("Precio", newPrice)));
    }

    public static void deleteProduct(int productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        collection.deleteOne(new Document("Id", productId));
    }

    public static Product findById(int productId) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("Product");
        Document doc = collection.find(new Document("Id", productId)).first();

        if (doc != null) {
            Integer id = doc.getInteger("Id");
            String name = doc.getString("Producto");
            Double price = doc.getDouble("Precio");
            Integer stock = doc.getInteger("Stock");
            String owner = doc.getString("Artesano");

            if (id != null && name != null && price != null && stock != null && owner != null) {
                return new Product(id, name, price, stock, owner);
            }
        }
        return null;
    }
    }


