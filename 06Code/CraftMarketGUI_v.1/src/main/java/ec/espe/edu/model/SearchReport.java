package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.espe.edu.utils.Connection;
import java.time.LocalDate;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SearchReport {

    public static void printReportByDate(LocalDate date) {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("sales");
        MongoCursor<Document> cursor = collection.find(new Document("saleDate", date.toString())).iterator();

        System.out.println("----- Reporte de ventas de " + date + " -----");

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.print(doc.getString("productName") + " // ");
                System.out.print(doc.getDouble("unitPrice") + " // ");
                System.out.print(doc.getInteger("quantity") + " // ");
                System.out.print(doc.getDouble("total") + " // ");
                System.out.println(doc.getString("artisanName"));
            }
        } finally {
            cursor.close();
        }
    }

}
