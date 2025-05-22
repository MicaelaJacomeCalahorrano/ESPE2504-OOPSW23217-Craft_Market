package ec.espe.edu.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class DailyReport {

    public static List<SalesReport> filterByDay(List<SalesReport> sales, String date) {
        return sales.stream()
                .filter(sale -> sale.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
