package ec.espe.edu.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
import java.util.Date;

public class Artisan {

    private int id;
    private String name;
    private String products;
    private float totalFines;
    private Date registerDate;

    public Artisan(int id, String name, String products, float totalFines, Date registerDate) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.totalFines = totalFines;
        this.registerDate = registerDate;
    }

    private void registerSale() {
        System.out.println("Artisan " + name + " registered a sale of " + products);
    }

    private void attendance() {
        System.out.println("Artisan " + name + " attended an exhibition");
    }

    private void payFine() {
        System.out.println("Artisan " + name + " paid a fine of $" + totalFines);
    }

 
    @Override
    public String toString() {
        return "Artisan{" + "id=" + id+ ", name='" + name  + ", products='" + products + ", totalFines=" + totalFines+ ", registerDate=" + registerDate+ '}';
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public float getTotalFines() {
        return totalFines;
    }

    public void setTotalFines(float totalFines) {
        this.totalFines = totalFines;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
