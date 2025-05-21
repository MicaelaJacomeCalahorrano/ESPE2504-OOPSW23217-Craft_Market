
package ec.edu.espe.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Product {
    private String name;
    private float unitPrice;
    private int stock;
    private Artisan owner;

    public Product(String name, float unitPrice, int stock, Artisan owner) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.owner = owner;
    }

    public void updatePrice(float newPrice) {
        this.unitPrice = newPrice;
    }

    public void updateStock(int newStock) {
        this.stock = newStock;
    }

    public Artisan getOwner() {
        return owner;
    }

    public String getDetails() {
        return name + " - Precio: " + unitPrice + " - Stock: " + stock;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }
}


