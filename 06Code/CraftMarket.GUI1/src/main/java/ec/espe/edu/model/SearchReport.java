package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.espe.edu.model.utils.MongoConnection;
import java.time.LocalDate;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SearchReport {

    public static void printReportByDate(LocalDate date) {
        MongoCollection<Document> collection = MongoConnection.getDatabase().getCollection("sales");
        MongoCursor<Document> cursor = collection.find(new Document("saleDate", date.toString())).iterator();

        System.out.println("\n----- Reporte de ventas de " + date + " -----");

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String productName = doc.getString("productName");
                Number unitPriceNumber = doc.get("unitPrice", Number.class);
                float unitPrice = unitPriceNumber != null ? unitPriceNumber.floatValue() : 0.0f;

                Integer quantity = doc.getInteger("quantity", 0);

                Number totalNumber = doc.get("total", Number.class);
                float total = totalNumber != null ? totalNumber.floatValue() : 0.0f;

                String artisanName = doc.getString("artisanName");

                System.out.println(productName + " // $" + unitPrice + " // " + quantity + " uds // $" + total + " // " + artisanName);
            }
        } finally {
            cursor.close();
        }
    }

}
