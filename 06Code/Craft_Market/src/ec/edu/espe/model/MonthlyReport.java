package ec.edu.espe.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */

import java.util.*;

public class MonthlyReport {

    private int month;
    private int year;
    private List<Sale> salesOfTheMonth;

    public MonthlyReport(int month, int year) {
        this.month = month;
        this.year = year;
        this.salesOfTheMonth = new ArrayList<>();
    }

    public void addSale(Sale sale) {
        salesOfTheMonth.add(sale);
    }

    public float calculateTotalMonthIncome() {
        float total = 0;
        for (Sale s : salesOfTheMonth) {
            total += s.calculateTotalPrice();
        }
        return total;
    }

    public Map<Artisan, Float> getIncomeByArtisan() {
        Map<Artisan, Float> map= new HashMap<>();
        for (Sale s : salesOfTheMonth) {
            map.put(s.getSeller(),map.getOrDefault(s.getSeller(),0f)+s.calculateTotalPrice());
        }
        return map;
    }

    public Map<Product, Integer> getUnitsSoldByProduct() {
        Map<Product, Integer> map = new HashMap<>();
        for (Sale s : salesOfTheMonth) {
            map.put(s.getProduct(), map.getOrDefault(s.getProduct(),0)+s.getQuantitySold());
        }
        return map;
    }
    public Artisan getTopSeller() {
        return getIncomeByArtisan().entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse(null);
    }
    public Product getBestSellingProduct() {
        return getUnitsSoldByProduct().entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse(null);
    }
    public String getReportSummary(){
        return "Report "+calculateTotalMonthIncome();
    }
 }

   
