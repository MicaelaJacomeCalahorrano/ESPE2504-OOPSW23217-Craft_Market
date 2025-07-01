package ec.espe.edu.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Connection {
   private static final String CONNECTION_STRING = "mongodb+srv://isaac:isaac@cluster0.xaitfht.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    static {
        try {
            MongoClientURI uri = new MongoClientURI(CONNECTION_STRING);
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("Craft_Market");
            System.out.println("Connected to MongoDB Atlas correctly.");
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static MongoCollection<Document> getUsersCollection() {
        return database.getCollection("users");
    }

    public static MongoCollection<Document> getProductsCollection() {
        return database.getCollection("products");
    }

    public static MongoCollection<Document> getSalesCollection() {
        return database.getCollection("sales");
    }

    public static MongoCollection<Document> getAttendanceCollection() {
        return database.getCollection("attendance");
    }

    public static MongoCollection<Document> getMonthlyReportsCollection() {
        return database.getCollection("monthlyReports");
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
}
