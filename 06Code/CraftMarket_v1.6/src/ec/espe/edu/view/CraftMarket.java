
package ec.espe.edu.view;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.User;
import java.io.IOException;

import java.util.ArrayList;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class CraftMarket {

    /**
     * @param args 
     */
    public static void main(String[] args) throws IOException {
         Artisan artisan1 = new Artisan("Alexis");
        Artisan artisan2 = new Artisan("Maria");
        Artisan artisan3 = new Artisan("Narcisa");
        Artisan artisan4 = new Artisan("Leonor");
        Artisan artisan5 = new Artisan("Katty"); 
        Artisan artisan6 = new Artisan("Alisia");
        Artisan artisan7 = new Artisan("Rosario");
        Artisan artisan8 = new Artisan("Silvia");
        Artisan artisan9 = new Artisan("Magaly");
        Artisan artisan10 = new Artisan("Fanny");

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("lourdes", "password", artisan1));
        users.add(new User("guido", "password", artisan2));
         users.add(new User("narcisa", "password", artisan3));
        users.add(new User("leonor", "password", artisan4));
        users.add(new User("katty", "password", artisan5));
        users.add(new User("alisia", "password", artisan6));
        users.add(new User("rosario", "password", artisan7));
        users.add(new User("silvia", "password", artisan8));
        users.add(new User("magaly", "password", artisan9));
        users.add(new User("fanny", "password", artisan10));


        User loggedUser = User.login(users);
        Menu.MostrarMenu(loggedUser);
    }

}
