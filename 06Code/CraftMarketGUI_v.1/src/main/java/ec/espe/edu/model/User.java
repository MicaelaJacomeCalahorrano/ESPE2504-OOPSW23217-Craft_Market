package ec.espe.edu.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ec.espe.edu.utils.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class User {
    private String user;
    private String password;
    private Artisan artisan;

    public User(String user, String password, Artisan artisan) {
        this.user = user;
        this.password = password;
        this.artisan = artisan;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Artisan getArtisan() {
        return artisan;
    }

    public void setArtisan(Artisan artisan) {
        this.artisan = artisan;
    }

    public static User login(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUser().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido " + user.getUser());
                return user;
            }
        }
        System.out.println("Credenciales incorrectas.");
        return null;
    }

   public void markAttendance() {
    Attendance.markPresent(this.user);
}

    public Document toDocument() {
        Document artisanDoc = new Document("name", artisan.getName());
        return new Document("username", user)
                .append("password", password)
                .append("artisan", artisanDoc);
    }

    public static ArrayList<User> loadAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("users");

        FindIterable<Document> documents = collection.find();

        for (Document doc : documents) {
            String username = doc.getString("username");
            String password = doc.getString("password");

            Document artisanDoc = (Document) doc.get("artisan");
            String artisanName = artisanDoc.getString("name");
            Artisan artisan = new Artisan(artisanName);

            users.add(new User(username, password, artisan));
        }

        return users;
    }
  
}
