package ec.espe.edu.model.utils;

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

    private final MongoCollection<Document> collection;

    public ProductDataAccessObject() {
       
       this.collection = MongoConnection.connect().getCollection("products");
    }

    
    public void addProduct(Product product) {
        Document doc = new Document("id", product.getId())
                .append("name", product.getName())
                .append("price", product.getPrice())
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
                doc.getInteger("stock")
            ));
        }
        return products;
    }

    
    public void updateProduct(Product product) {
        collection.updateOne(
            Filters.eq("id", product.getId()),
            new Document("$set", new Document()
                .append("name", product.getName())
                .append("price", product.getPrice())
                .append("stock", product.getStock())
            )
        );
    }

    
    public void deleteProduct(int id) {
        collection.deleteOne(Filters.eq("id", id));
    }
}
