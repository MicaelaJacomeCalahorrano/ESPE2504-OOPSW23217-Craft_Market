package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.espe.edu.utils.MongoConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SalesReport {

    private String productName;
    private double unitPrice;
    private int quantity;
    private double total;
    private String artisanName;
    private LocalDate saleDate;

    public SalesReport(String productName, double unitPrice, int quantity, double total, String artisanName) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.artisanName = artisanName;
        this.saleDate = LocalDate.now();
    }

 
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    

    public void setTotal(float total) {
        this.total = total;
    }

    public String getArtisanName() {
        return artisanName;
    }

    public void setArtisanName(String artisanName) {
        this.artisanName = artisanName;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String toCSV() {
        return productName + "," + unitPrice + "," + quantity + "," + total + "," + artisanName;
    }

    public static void registerSale(Inventory inventory, int productId, int quantity) {
        Product product = Product.findById(productId);
        if (product == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        if (product.getStock() < quantity) {
            System.out.println("Stock insuficiente.");
            return;
        }

        double total = product.getUnitPrice() * quantity;
        String artisanName = product.getOwner();

        SalesReport sale = new SalesReport(product.getName(), product.getUnitPrice(), quantity, total, artisanName);
        registerSale(sale);

        Product.updateProductStock(productId, product.getStock() - quantity);

        System.out.println("Venta registrada correctamente.");
    }

    public static void registerSale(SalesReport sale) {
       MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("sales");
        Document doc = new Document("productName", sale.productName)
                .append("unitPrice", sale.unitPrice)
                .append("quantity", sale.quantity)
                .append("total", sale.total)
                .append("artisanName", sale.artisanName)
                .append("saleDate", sale.saleDate.toString());
        collection.insertOne(doc);
    }

    public static List<SalesReport> getSalesByDate(LocalDate date) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("sales");
        List<SalesReport> sales = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find(new Document("saleDate", date.toString())).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String productName = doc.getString("productName");
                Double unitPrice = doc.getDouble("unitPrice");
                Integer quantity = doc.getInteger("quantity");
                Double total = doc.getDouble("total");
                String artisanName = doc.getString("artisanName");

                if (productName != null && unitPrice != null && quantity != null && total != null && artisanName != null) {
                    sales.add(new SalesReport(
                            productName,
                            unitPrice,
                            quantity,
                            total,
                            artisanName
                    ));
                }
            }
        } finally {
            cursor.close();
        }
        return sales;
    }
}
