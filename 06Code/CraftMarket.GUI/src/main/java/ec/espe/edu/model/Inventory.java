package ec.espe.edu.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.espe.edu.utils.MongoConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Jorge Fuentes Crafters_Market DCCO ESPE
 */
public class Inventory {
    private List<Product> allProducts = new ArrayList<>();

    private static final MongoDatabase db = MongoConnection.getDatabase();
    private static final MongoCollection<Document> productCollection = db.getCollection("Product");
    private static final MongoCollection<Document> artisanCollection = db.getCollection("Artisan");

    public Inventory() {
        loadProductsFromDatabase();
    }

    public void loadProductsFromDatabase() {
        allProducts.clear();
        FindIterable<Document> documents = productCollection.find();

        for (Document doc : documents) {
            int id = doc.getInteger("Id");
            String name = doc.getString("Producto");

            float unitPrice;
            Object priceObj = doc.get("Precio");

            switch (priceObj) {
                case Double aDouble -> unitPrice = aDouble.floatValue();
                case Integer integer -> unitPrice = integer.floatValue();
                default -> unitPrice = 0.0f; 
            }

            int stock = doc.getInteger("stock", 0);
            String owner = doc.getString("Artesano");

            Product product = new Product(id, name, unitPrice, stock, owner);
            allProducts.add(product);
        }
    }

    public List<Product> getAllProducts() {
        loadProductsFromDatabase();
        return allProducts;
    }
    
    public boolean artisanExists(String artisanName) {
        Bson artisanFilter = Filters.eq("username", artisanName);
        Document artisanFound = artisanCollection.find(artisanFilter).first();
        return artisanFound != null;
    }
    
    public List<Product> getProductsByArtisan(Artisan artisan) {
        String capitalizedArtisanName = capitalizeFirstLetter(artisan.getName());
        return allProducts.stream()
                .filter(p -> p.getOwner().equalsIgnoreCase(artisan.getName()))
                .collect(Collectors.toList());
    }
    
    public DefaultTableModel getPersonalInventoryTableModel (String artisanName){
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        model.addColumn("Id");
        model.addColumn("Producto");
        model.addColumn("Precio");
        model.addColumn("Stock");
        
        String capitalizedArtisanName = capitalizeFirstLetter(artisanName);
        Bson filter = Filters.eq("Artesano", capitalizeFirstLetter(artisanName));
        FindIterable<Document> documents = productCollection.find(filter);
        
        for(Document doc : documents){
            int id = doc.getInteger("Id");
            String productName = doc.getString("Producto");
            double price = doc.getDouble("Precio");
            int stock = doc.getInteger("Stock");
            
            model.addRow(new Object[]{id, productName, price, stock});
            
        }
        return model;
    }
    
    public boolean hasProducts (String artisanName){
        String capitalizedArtisanName = capitalizeFirstLetter(artisanName);
        Bson filter = Filters.eq("Artesano", capitalizeFirstLetter(artisanName));
        return productCollection.countDocuments(filter) >0;
    }
    
    private String capitalizeFirstLetter(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public DefaultTableModel getGeneralInventoryTableModel(){
         DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        
        };
        
        model.addColumn("Id");
        model.addColumn("Producto");
        model.addColumn("Precio");
        model.addColumn("Stock");
        model.addColumn("Artesano");
        
        loadProductsFromDatabase();
        
        for(Product product : allProducts){
            model.addRow(new Object[]{
                product.getId(),
                product.getName(),
                product.getUnitPrice(),
                product.getStock(),
                product.getOwner()
                
            });
        }
        return model;
         
    }
}
