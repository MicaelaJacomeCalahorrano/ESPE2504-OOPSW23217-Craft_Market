package ec.espe.edu.model;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
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


    // Basic constructor
    public Artisan(String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    Artisan(int id, String artisanName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String getName(){
        return name;
    }

    
    public void addProductFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Add New Product ===");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter unit price: ");
        float price = scanner.nextFloat();

        System.out.print("Enter stock quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); 

        Product product = new Product(name, price, stock, this.name);
        products.add(product);

        System.out.println("Product added successfully!");
         try (FileWriter writer = new FileWriter("products.csv", true)) {
        writer.append(name).append(",")
              .append(String.valueOf(price)).append(",")
              .append(String.valueOf(stock)).append(",")
              .append(this.name).append("\n");
    } catch (IOException e) {
        System.out.println(" Error saving to CSV: " + e.getMessage());
    }
    }
        public void registerSale() {
        Scanner scanner = new Scanner(System.in);

        if (products.isEmpty()) {
            System.out.println(" No products available. Add some products first.");
            return;
        }

        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + ". " + products.get(i).getDetails());
        }

        System.out.print("Choose product index: ");
        int index = scanner.nextInt();

        if (index < 0 || index >= products.size()) {
            System.out.println(" Invalid product index.");
            return;
        }

        Product product = products.get(index);

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();

        if (product.getStock() < quantity) {
            System.out.println(" Not enough stock.");
            return;
        }

        float totalSale = quantity * product.getUnitPrice();
        product.updateStock(product.getStock() - quantity);
        System.out.println(" Sale registered! Total: $" + totalSale);

        try (FileWriter writer = new FileWriter("sales.csv", true)) {
            writer.append(product.getName()).append(",")
                  .append(String.valueOf(product.getUnitPrice())).append(",")
                  .append(String.valueOf(quantity)).append(",")
                  .append(String.valueOf(totalSale)).append(",")
                  .append(this.name).append("\n");
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }
    public void ArtisanMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("===== ARTISAN MENU: " + name + " =====");

             System.out.println("1. Add Product");
            System.out.println("2. Register sale");
            System.out.println(". Update stock");
            System.out.println("3. Update price");
            System.out.println("4. Mark attendance");
            System.out.println("5. Apply penalty");
            System.out.println("6. Calculate monthly income");
            System.out.println("7. Calculate total penalties");
            System.out.println("0. Return to main menu");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Add Product ");
                     addProductFromInput(); 
                    break;
                case 2:
                    System.out.println("Register sale");
                    registerSale();
                    break;
                case 3:
                    System.out.println("Update price (to be implemented)");
                    break;
                case 4:
                    System.out.println("Mark attendance (to be implemented)");
                    break;
                case 5:
                    System.out.println("Apply penalty (to be implemented)");
                    break;
                case 6:
                    System.out.println("Calculate monthly income (to be implemented)");
                    break;
                case 7:
                    System.out.println("Calculate total penalties (to be implemented)");
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }
}
