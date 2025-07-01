
package ec.espe.edu.controller;

import com.mongodb.client.MongoCollection;
import ec.espe.edu.utils.Connection;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class User {
        public static boolean login(String username, String password) {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("users");
        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();
        return result != null;
    }

    public static String getArtisanName(String username, String password) {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("users");
        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();

        if (result != null && result.containsKey("artisan")) {
            Document artisanDoc = (Document) result.get("artisan");
            return artisanDoc.getString("name");
        }
        return "Artesano desconocido";
    }
}
