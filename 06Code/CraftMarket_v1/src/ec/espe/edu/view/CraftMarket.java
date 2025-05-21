
package ec.espe.edu.view;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.User;

import java.util.ArrayList;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class CraftMarket {

    /**
     * @param args 
     */
    public static void main(String[] args) {
         Artisan artisan1 = new Artisan(1, "Alexis");
        Artisan artisan2 = new Artisan(2, "Maria");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("alexis", "password123", artisan1));
        users.add(new User("maria", "hello123", artisan2));

        User loggedUser = User.login(users);
        Menu.MostrarMenu(loggedUser);
    }

}
