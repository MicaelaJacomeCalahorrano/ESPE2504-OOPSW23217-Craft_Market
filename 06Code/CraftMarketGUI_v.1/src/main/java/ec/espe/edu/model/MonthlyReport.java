
package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.espe.edu.utils.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public static List<MonthlyReport> generateMonthlyReport() {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("sales");
        List<MonthlyReport> reports = new ArrayList<>();
        
        MongoCursor<Document> cursor = collection.find().iterator();

        class Key {
            String artisan;
            int year;
            int month;
            Key(String artisan, int y, int m) { artisan=artisan; year=y; month=m; }
            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Key)) return false;
                Key k = (Key)o;
                return artisan.equals(k.artisan) && year == k.year && month == k.month;
            }
            @Override
            public int hashCode() {
                return artisan.hashCode() + year*31 + month*17;
            }
        }

        java.util.Map<String, Float> totalsMap = new java.util.HashMap<>();
        java.util.Map<String, MonthlyReport> reportsMap = new java.util.HashMap<>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String artisanName = doc.getString("artisanName");
                String saleDateStr = doc.getString("saleDate"); // yyyy-MM-dd
                float total = doc.getDouble("total").floatValue();

                LocalDate saleDate = LocalDate.parse(saleDateStr);
                int year = saleDate.getYear();
                int month = saleDate.getMonthValue();

                String key = artisanName + "-" + year + "-" + month;
                totalsMap.put(key, totalsMap.getOrDefault(key, 0f) + total);
            }
        } finally {
            cursor.close();
        }

        for (String key : totalsMap.keySet()) {
            String[] parts = key.split("-");
            String artisanName = parts[0];
            int year = Integer.parseInt(parts[1]);
            int month = Integer.parseInt(parts[2]);
            float totalSales = totalsMap.get(key);

            reports.add(new MonthlyReport(artisanName, month, year, totalSales));
        }

        System.out.println("Reporte mensual de ventas:");
        for (MonthlyReport r : reports) {
            System.out.println("Artesano: " + r.getArtisanName() + " - " + r.getMonth() + "/" + r.getYear() + " - Total: $" + r.getTotalSales());
        }

        return reports;
    }
}
