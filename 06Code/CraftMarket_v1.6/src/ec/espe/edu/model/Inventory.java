
package ec.espe.edu.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Fuentes
 */
public class Inventory {
    private List<Product> allProducts = new ArrayList<>();
    public void openProductsFromCVS(String fileRoute) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileRoute))){
            String line;
            while((line=br.readLine()) != null){
                String[] parts=line.split(";");
                if(parts.length < 4) continue;
                
                String productName = parts[0];
                float price = Float.parseFloat(parts[1]);
                int stock = Integer.parseInt(parts [2]);
                String artisanName = parts[3];
                
                Artisan artisan = new Artisan(artisanName);
                Product product = new Product(productName, price, stock, artisanName);
                allProducts.add(product);
                 
            }
        }catch(IOException e){
            System.out.println("Error al leer el archivo..." + e.getMessage());
        }
    }
    public List<Product> getAllProducts() {
        return allProducts;
    }
    public List<Product> getProductsByArtisan(Artisan artisan){
        return allProducts.stream().filter(p -> p.getOwner().equalsIgnoreCase(artisan.getName()))
                .collect(Collectors.toList());
    }
    public void showGeneralInventory(){
        System.out.println("\n----- Inventario General -----");
        if(allProducts.isEmpty()){
            System.out.println("No hay productos registrados");
            return;
        }
        
        for(Product p : allProducts){
            System.out.println("/ Producto: " + p.getName() + "/ Precio: " + p.getUnitPrice() + "/ Dueño: " + p.getOwner());
        }
    }
    public void showPersonalInventory(Artisan artisan) {
        System.out.println("\n--- MI INVENTARIO ---");
        List<Product> productos = getProductsByArtisan(artisan);
        if (productos.isEmpty()) {
            System.out.println("No tienes productos registrados.");
            return;
        }
        for (Product p : productos) {
            System.out.println(" | Nombre: " + p.getName() + " | Precio: " + p.getUnitPrice() + " | Stock: " + p.getStock());
        }
    }
}
