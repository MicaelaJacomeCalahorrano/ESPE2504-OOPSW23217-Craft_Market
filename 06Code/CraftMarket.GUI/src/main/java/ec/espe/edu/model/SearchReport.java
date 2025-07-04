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

        System.out.println("\n----- Reporte de ventas del " + date + " -----");

        try (MongoCursor<Document> cursor = collection.find(new Document("saleDate", date.toString())).iterator()) {
            boolean found = false;

            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String productName = doc.getString("productName");
                Double unitPrice = doc.getDouble("unitPrice");
                Integer quantity = doc.getInteger("quantity");
                Double total = doc.getDouble("total");
                String artisanName = doc.getString("artisanName");

                if (productName == null || unitPrice == null || quantity == null || total == null || artisanName == null) {
                    continue;
                }

                System.out.println(
                        "Producto: " + productName +
                        " | Precio: $" + unitPrice +
                        " | Cantidad: " + quantity +
                        " | Total: $" + total +
                        " | Artesano: " + artisanName
                );
                found = true;
            }

            if (!found) {
                System.out.println("No se encontraron ventas para esta fecha.");
            }

        } catch (Exception e) {
            System.err.println("[SearchReport] Error al generar el reporte: " + e.getMessage());
        }
    }
}
