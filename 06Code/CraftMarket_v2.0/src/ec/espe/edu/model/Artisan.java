package ec.espe.edu.model;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Chicaiza CraftMarket DCCO ESPE
 */
public class Artisan {
    private int id;
    private String name;
    private List<Product> products;

    public Artisan(String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    Artisan(int id, String artisanName) {
                                                                   
    }

    public String getName() {
        return name;
    }

}
