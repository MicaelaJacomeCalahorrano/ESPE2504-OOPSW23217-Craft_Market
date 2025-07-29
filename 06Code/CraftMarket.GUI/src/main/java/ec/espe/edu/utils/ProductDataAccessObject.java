package ec.espe.edu.utils;

/**
 *
 * @author Micaela Jácome
 */
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.espe.edu.model.Product;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class ProductDataAccessObject {
    private int id;
    private String product;
    private double price;
    private int stock;
    private String artisan;
    

    private final MongoCollection<Document> collection;

    public ProductDataAccessObject() {
       
       this.collection = MongoConnection.connect().getCollection("Product");
    }

    public ProductDataAccessObject(int id, String product, double price, int stock, MongoCollection<Document> collection) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.stock = stock;
        this.collection = MongoConnection.connect().getCollection("Product");
    }
    

    
    public void addProduct(Product product) {
        Document doc = new Document("id", product.getId())
                .append("name", product.getName())
                .append("price", product.getUnitPrice())
                .append("stock", product.getStock());
        collection.insertOne(doc);
    }

    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for (Document doc : collection.find()) {
            products.add(new Product(
                doc.getInteger("id"),
                doc.getString("name"),
                doc.getDouble("price"),
                doc.getInteger("stock"),
                doc.getString("artisan")
            ));
        }
        return products;
    }

    
    public void updateProduct(Product product) {
        collection.updateOne(
            Filters.eq("id", product.getId()),
            new Document("$set", new Document()
                .append("name", product.getName())
                .append("price", product.getUnitPrice())
                .append("stock", product.getStock())
                .append("Artisan", product.getOwner())
            )
        );
    }

    
    public void deleteProduct(int id) {
        collection.deleteOne(Filters.eq("id", id));
    }
    
}
