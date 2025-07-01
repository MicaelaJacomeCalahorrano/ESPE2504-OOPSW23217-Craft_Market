/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.model;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class User {
    private String username;
    private String password;
    private Artisan artisan;

    public User() {
    }

    public User(String username, String password, Artisan artisan) {
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
}
