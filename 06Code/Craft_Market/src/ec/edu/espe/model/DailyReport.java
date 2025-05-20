package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class DailyReport {

    private List<Sale>salesOfTheDay  = new ArrayList<>();
    private Date date;

    public DailyReport(Date date) {
        this.date = date;
    }

    public void addSale(Sale sale) {
        salesOfTheDay.add(sale);
    }

    public List<Sale> getSales() {
        return salesOfTheDay;
    }

    public float calculateTotalDayIncome() {
        float total = 0;
        for (Sale s : salesOfTheDay) {
            total += s.calculateTotalPrice();
        }
        return total;
    }

    public Map<Artisan, Float> getIncomeByArtisan() {
        Map<Artisan, Float> map = new HashMap<>();
        for (Sale sale : salesOfTheDay) {
            map.put(sale.getSeller(), map.getOrDefault(sale.getSeller(),0f)+ sale.calculateTotalPrice());
        }
        return map;
    }

    public String getReportDetails() {
        return "DAY TOTAL IS : " + calculateTotalDayIncome();
    }

}
