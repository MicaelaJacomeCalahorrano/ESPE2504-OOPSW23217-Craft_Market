
package ec.espe.edu.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SalesReport {
    private String productName;
    private float unitPrice;
    private int quantity;
    private float total;
    private String artisanName;
    

    public SalesReport(String productName, float unitPrice, int quantity, float total, String artisanName) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.artisanName = artisanName;
    }

    public String toCSV() {
        return productName + "," + unitPrice + "," + quantity + "," + total + "," + artisanName;
    }

    public String getDisplayString() {
        return "Product: " + productName + " | Price: " + unitPrice + " | Quantity: " + quantity +
               " | Total: " + total + " | Artisan: " + artisanName + "|Artisan " ;
    }
}

