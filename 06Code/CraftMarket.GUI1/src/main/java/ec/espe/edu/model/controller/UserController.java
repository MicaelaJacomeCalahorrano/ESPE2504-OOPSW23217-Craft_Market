package ec.espe.edu.model.controller;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import org.bson.Document;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class UserController {
   // Reutiliza conexión establecida con Singleton
    private static final MongoDatabase db = MongoConnection.getDatabase();
    private static final MongoCollection<Document> collection = db.getCollection("Artisan");

    public static boolean login(String username, String password) {
        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();
        return result != null;
    }

    public static String getArtisanName(String username, String password) {
        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();

        if (result != null) {
            Document artisanDoc = result.get("artisan", Document.class);
            return artisanDoc != null ? artisanDoc.getString("name") : "Artesano desconocido";
        }
        return "Artesano desconocido";
    }
}
