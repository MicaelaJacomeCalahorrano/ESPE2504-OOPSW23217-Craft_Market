
package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class MonthlyReport {
  private String artisanName;
    private int month;
    private int year;
    private float totalSales;

    public MonthlyReport(String artisanName, int month, int year, float totalSales) {
        this.artisanName = artisanName;
        this.month = month;
        this.year = year;
        this.totalSales = totalSales;
    }

    // --- Getters ---
    public String getArtisanName() {
        return artisanName;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public float getTotalSales() {
        return totalSales;
    }

    /**
     * Genera el reporte mensual por artesano agrupado por mes y año
     * @return 
     */
    public static List<MonthlyReport> generateMonthlyReport() {
        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> collection = db.getCollection("sales");
        List<MonthlyReport> reports = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();
        Map<String, Float> totalsMap = new HashMap<>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String artisanName = doc.getString("artisanName");
                String saleDateStr = doc.getString("saleDate"); // yyyy-MM-dd

                // Manejo seguro de 'total' por posibles Integer/Double
                float total;
                Object totalObj = doc.get("total");
                if (totalObj instanceof Double) {
                    total = ((Double) totalObj).floatValue();
                } else if (totalObj instanceof Integer) {
                    total = ((Integer) totalObj).floatValue();
                } else {
                    total = 0.0f;
                }

                LocalDate saleDate = LocalDate.parse(saleDateStr);
                int year = saleDate.getYear();
                int month = saleDate.getMonthValue();

                String key = artisanName + "-" + year + "-" + month;
                totalsMap.put(key, totalsMap.getOrDefault(key, 0f) + total);
            }
        } finally {
            cursor.close();
        }

        // Crear objetos MonthlyReport a partir del mapa
        for (String key : totalsMap.keySet()) {
            String[] parts = key.split("-");
            String artisanName = parts[0];
            int year = Integer.parseInt(parts[1]);
            int month = Integer.parseInt(parts[2]);
            float totalSales = totalsMap.get(key);

            reports.add(new MonthlyReport(artisanName, month, year, totalSales));
        }

        System.out.println("\n--- Reporte mensual de ventas generado correctamente ---");
        for (MonthlyReport r : reports) {
            System.out.println("Artesano: " + r.getArtisanName()
                    + " | Mes/Año: " + r.getMonth() + "/" + r.getYear()
                    + " | Total: $" + r.getTotalSales());
        }

        return reports;
    }
}
