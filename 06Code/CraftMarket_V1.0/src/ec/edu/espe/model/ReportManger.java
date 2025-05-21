package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class ReportManger {

    public class ReportManager {

        private Map<Date, DailyReport> dailyReports = new HashMap<>();
        private Map<String, MonthlyReport> monthlyReports = new HashMap<>();

        public void registerSale(Sale sale) {
            Date date = sale.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String key = month + "-" + year;

            dailyReports.putIfAbsent(date, new DailyReport(date));
            monthlyReports.putIfAbsent(key, new MonthlyReport(month, year));

            dailyReports.get(date).addSale(sale);
            monthlyReports.get(key).addSale(sale);
        }

        public DailyReport getReportByDate(Date date) {
            return dailyReports.get(date);
        }

        public MonthlyReport getReportByMonth(int month, int year) {
            return monthlyReports.get(month + "-" + year);
        }

        public List<Product> getGeneralInventory() {
            Set<Product> inventory = new HashSet<>();
            for (DailyReport dr : dailyReports.values()) {
                for (Sale sale : dr.getSales()) {
                    inventory.add(sale.getProduct());
                }
            }
            return new ArrayList<>(inventory);
        }
    }
}
