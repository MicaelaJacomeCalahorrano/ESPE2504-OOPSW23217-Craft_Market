package ec.espe.edu.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Product {
    private String name;
    private float unitPrice;
    private int stock;
    private String owner;

    public Product(String name, float unitPrice, int stock, String owner) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
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

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toCSV() {
        return name + "," + unitPrice + "," + stock + "," + owner;
    }

    public String getDetails() {
        return "Product: " + name + ", Price: $" + unitPrice + ", Stock: " + stock + ", Owner: " + owner;
    }

    void updateStock(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
