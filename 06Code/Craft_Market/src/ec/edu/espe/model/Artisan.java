package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Artisan {

    private int id;
    private String name;
    private List<Product> products = new ArrayList<>();
    private List<Attendance> attendanceList = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();
    private List<Penalty> penalties = new ArrayList<>();

    public Artisan(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerSale(Product product, int quantity, float unitPrice) {
        Sale sale = new Sale(product, quantity, unitPrice, new Date(), this);
        sales.add(sale);
        product.updateStock(product.getStock() - quantity);
    }

    public void updateStock(Product product, int newStock) {
        product.updateStock(newStock);
    }

    public void markAttendace(Date date) {
        attendanceList.add(new Attendance(date, true));
    }

    public void applyPenalty(Date date) {
        penalties.add(new Penalty(date, 10.0f));
    }

    public float calculateMonthlyIncome() {
        float income = 0;
        for (Sale sale : sales) {
            income += sale.calculateTotalPrice();
        }
        return income;
    }

    public float calculatePenaltyTotal() {
        float total = 0;
        for (Penalty p : penalties) {
            total += p.getAmount();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
  }

  