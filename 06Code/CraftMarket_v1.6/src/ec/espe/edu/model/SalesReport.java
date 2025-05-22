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
    private String date;

    public SalesReport(String productName, float unitPrice, int quantity, float total, String artisanName, String date) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.artisanName = artisanName;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String toCSV() {
        return productName + "," + unitPrice + "," + quantity + "," + total + "," + artisanName + "," + date;
    }

    public String getDisplayString() {
        return "Product: " + productName + " || Price: " + unitPrice + " || Quantity: " + quantity
                + " || Total: " + total + " || Artisan: " + artisanName + "|| date: " + date ;
    }
}
