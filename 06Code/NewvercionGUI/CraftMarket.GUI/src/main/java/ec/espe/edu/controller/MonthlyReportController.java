package ec.espe.edu.controller;

import java.util.Map;

/**
 *
 * @author jorge
 */
public class MonthlyReportController {
    
    public static float calcularTotalMensual(Map<String, Float> artisanSalesMap) {
        float total = 0f;
        for (Float venta : artisanSalesMap.values()) {
            if (venta != null) {
                total += venta;
            }
        }
        return total;
    }
    
}
