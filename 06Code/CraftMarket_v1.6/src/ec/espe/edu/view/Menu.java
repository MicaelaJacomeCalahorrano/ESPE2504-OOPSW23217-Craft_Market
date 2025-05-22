package ec.espe.edu.view;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.Inventory;
import ec.espe.edu.model.Product;
import ec.espe.edu.model.SalesReport;
import ec.espe.edu.model.User;
import ec.espe.edu.utils.CsvProduct;

import ec.espe.edu.utils.CsvUtilsStock;
import ec.espe.edu.utils.CsvUtilsPrice;
import ec.espe.edu.utils.CsvUtilsID;
import ec.espe.edu.utils.DeleteProducts;

import ec.espe.edu.utils.SalesUtils;


/**
 *
 * @author jorge
 */
public class Menu {
    private static Inventory inventory = new Inventory();

    public static void MostrarMenu(User loggedUser) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n====Menu====");
            System.out.println("User: " + loggedUser.getUser());
            System.out.println("1. Asistencia");
            System.out.println("2. Registro de ventas");
            System.out.println("3. Inventario");
            System.out.println("4. Productos");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    showAttendanceMenu(loggedUser);
                    break;

                case 2:
                    showSalesMenu(loggedUser);
                    break;

                case 3:
                    showTheInventoryMenu();
                    break;
                
                case 4:
                    showProductMenu(loggedUser);
                    break;
                    
                case 5:
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (option != 5);
    }

    private static void showAttendanceMenu(User loggedUser) {
        List<Attendance> attendanceList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- ATTENDANCE MENU ---");
            System.out.println("1. Mark attendance");
            System.out.println("2. View attendance history");
            System.out.println("3. Calculate penalty");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    loggedUser.markAttendance();
                    System.out.println("Attendance marked for user: " + loggedUser.getUser());

                case 2:
                    System.out.println("--- Attendance History ---");
                    ArrayList<Attendance> history = Attendance.getAttendanceHistory(loggedUser.getUser());
                    if (history.isEmpty()) {
                        System.out.println("No attendance records found.");
                    } else {
                        for (Attendance attendance : history) {
                            System.out.println(attendance.getDate() + " - Present: " + attendance.isPresent());
                        }
                    }
                    break;

                case 3:
                    float penalty = Attendance.calculatePenalty(loggedUser.getUser());
                    System.out.println("Total penalty for " + loggedUser.getUser() + ": $" + penalty);
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (option != 0);
    }
    
    




     private static void showProductMenu(User loggedUser) throws IOException{

     Artisan artisan = loggedUser.getArtisan();

     Scanner scanner = new Scanner(System.in);
     int option;
     do {
         System.out.println("---PRODUCT MANAGEMENT---\n");
         System.out.println("1. Add Product");
         System.out.println("2.Edit Product");
         System.out.println("3. Delet Product");
         System.out.println("0. Back");
         System.out.println("Choose an option: ");
         option = scanner.nextInt();
         scanner.nextLine();

         switch (option){
             case 1:
                 Product.addProduct(scanner, loggedUser.getArtisan().getName());

             break;

             case 2:
                System.out.print("Enter the product ID to edit: ");
                String productIdToEdit = scanner.nextLine();
                showEditProductSubMenu(productIdToEdit);
             break;

             case 3:
                Product.deleteProduct();
             break;


             case 0:
             break;

             default:
                 System.out.println("Invalid option.");
         }
     } while (option !=0);
 }

 public static void showEditProductSubMenu(String productNameToEdit) {
     Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el ID del producto que desea editar: ");
    String productId = scanner.nextLine();

    int choice;
    do {
        System.out.println("--- Editar Producto ---");
        System.out.println("1. Editar Precio");
        System.out.println("2. Editar Stock");
        System.out.println("0. Volver");
        System.out.print("Selecciona una opción: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        switch (choice) {
            case 1:
                Product.editProductPrice(scanner, productId);
                break;
            case 2:
                Product.editProductStock(scanner, productId);
                break;
            case 0:
                System.out.println("Regresando...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    } while (choice != 0);
 }
 private static void showSalesMenu(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- SALES MENU ---");
            System.out.println("1. Registrar venta Diaria");
            System.out.println("2. Ver historial de ventas");
            System.out.println("0. Regresar");
            System.out.print("Elige una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Ingrese nombre del producto: ");
                    String product = scanner.nextLine();

                    System.out.print("Ingrese precio del producto: ");
                    float price = scanner.nextFloat();

                    System.out.print("Ingrese cantidad vendida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); 

                    float total = price * quantity;

                    System.out.print("Ingrese nombre del artesano: ");
                    String artisan = scanner.nextLine();

                    SalesReport sale = new SalesReport(product, price, quantity, total, artisan);
                    SalesUtils.saveSale(sale);

                    System.out.println("Venta registrada correctamente.");
                    break;

                case 2:
                    System.out.println("--- HISTORIAL DE VENTAS ---");
                    List<ec.espe.edu.model.SalesReport> sales = ec.espe.edu.utils.SalesUtils.loadSales();
                    if (sales.isEmpty()) {
                        System.out.println("No hay ventas registradas.");
                    } else {
                        for (ec.espe.edu.model.SalesReport s : sales) {
                            System.out.println(s.getDisplayString());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        } while (option != 0);
    }
 public Menu(Inventory inventory){
        this.inventory= inventory;
        
    }
    private static void showTheInventoryMenu() throws IOException{
        Scanner scanner = new Scanner(System.in);
        try {
            inventory.openProductsFromCVS("products.csv");  // ✅ Cargar datos desde el CSV
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo de productos: " + e.getMessage());
        }
        int option = -1;
        while(option !=0){
            System.out.println("\n----- Inventario -----");
            System.out.println("1. Ver el inventario general");
            System.out.println("2. Ver el invetario personal");
            System.out.println("0. Volver al menu principal");
            System.out.println("Selecciona una opcion: ");
            option = scanner.nextInt();
            
            switch(option){
                case 1:
                    inventory.showGeneralInventory();
                    break;
                case 2:
                    System.out.println("Ingrese su nombre: ");
                    String artisanName = scanner.nextLine();
                    Artisan artisan = new Artisan(artisanName);
                    inventory.showPersonalInventory(artisan);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            
        }
        }
    }
}