package ec.espe.edu.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class MonthlyReport {

    public static List<SalesReport> filterByMonth(List<SalesReport> sales, String month) {
        return sales.stream()
                .filter(sale -> sale.getDate().startsWith(month))
                .collect(Collectors.toList());
    }
}
