
package ec.espe.edu.utils;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ec.espe.edu.model.Artisan;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Login {
        private String username;
    private String password;
    private Artisan artisan;

    public Login(String username, String password, Artisan artisan) {
        this.username = username;
        this.password = password;
        this.artisan = artisan;
    }

    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Artisan getArtisan() {
        return artisan;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArtisan(Artisan artisan) {
        this.artisan = artisan;
    }

    public static ArrayList<Login> loadAllUsers() {
        ArrayList<Login> users = new ArrayList<>();
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("users");

        FindIterable<Document> documents = collection.find();

        for (Document doc : documents) {
            String username = doc.getString("username");
            String password = doc.getString("password");

            Document artisanDoc = (Document) doc.get("artisan");
            String artisanName = artisanDoc != null ? artisanDoc.getString("name") : "No Artisan";
            Artisan artisan = new Artisan(artisanName);

            users.add(new Login(username, password, artisan));
        }

        return users;
    }
}
