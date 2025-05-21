
package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Sale {
     private Product product;
    private int quantitySold;
    private float totalPrice;
    private Date date;
    private Artisan seller;

    public Sale(Product product, int quantitySold, float unitPrice, Date date, Artisan seller) {
        this.product = product;
        this.quantitySold = quantitySold;
        this.totalPrice = quantitySold * unitPrice;
        this.date = date;
        this.seller = seller;
    }

    public float calculateTotalPrice() {
        return totalPrice;
    }

    public Artisan getProductOwner() {
        return product.getOwner();
    }

    public String getSaleDetails() {
        return "Producto: " + product.getName() + ", Cantidad: " + quantitySold + ", Total: " + totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public Artisan getSeller() {
        return seller;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantitySold() {
        return quantitySold;
    }
}

