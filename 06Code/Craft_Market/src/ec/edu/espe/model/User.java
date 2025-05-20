
package ec.edu.espe.model;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class User {
    private String username;
    private String passwordHash;
    private Artisan artisan;

    public User(String username, String password, Artisan artisan) {
        this.username = username;
        setPassword(password);
        this.artisan = artisan;
    }

    public boolean checkPassword(String password) {
        return this.passwordHash.equals(String.valueOf(password.hashCode()));
    }

    public void setPassword(String newPassword) {
        this.passwordHash = String.valueOf(newPassword.hashCode());
    }

    public Artisan getArtisan() {
        return artisan;
    }
}
