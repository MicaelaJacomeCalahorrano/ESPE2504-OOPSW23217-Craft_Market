package ec.espe.edu.model.controller;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import org.bson.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class UserController {
      public static boolean login(String username, String password) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> collection = db.getCollection("Artisan");

        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();

        return result != null;
    }

    public static String getArtisanName(String username, String password) {
        MongoDatabase db = MongoConnection.connect();
        MongoCollection<Document> collection = db.getCollection("Artisan");

        Document query = new Document("username", username).append("password", password);
        Document result = collection.find(query).first();

        if (result != null) {
            Document artisanDoc = result.get("artisan", Document.class);
            return artisanDoc.getString("name");
        }
        return null;
    }
}
