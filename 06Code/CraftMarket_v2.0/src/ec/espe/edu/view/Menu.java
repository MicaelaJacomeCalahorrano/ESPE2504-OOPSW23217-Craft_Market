package ec.espe.edu.view;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.Inventory;
import ec.espe.edu.model.MontlyReport;
import ec.espe.edu.model.Product;
import ec.espe.edu.model.SalesReport;
import ec.espe.edu.model.SearchReport;
import ec.espe.edu.model.User;
import ec.espe.edu.utils.CsvProduct;
import ec.espe.edu.utils.SalesUtils;

/**
 *
 * @author Jorge Fuentes CraftMarket DCCO ESPE
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
            System.out.println("1. Marcar asistencia");
            System.out.println("2. Ver historial de asistencia");
            System.out.println("3. Calcular penalizacion");
            System.out.println("0. Regresar");
            System.out.print("Selecciona una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    loggedUser.markAttendance();
                    System.out.println("Asistencia marcada po: " + loggedUser.getUser());

                case 2:
                    System.out.println("--- Historial de asistencia ---");
                    ArrayList<Attendance> history = Attendance.getAttendanceHistory(loggedUser.getUser());
                    if (history.isEmpty()) {
                        System.out.println("No se encontro un historial de asistencia.");
                    } else {
                        for (Attendance attendance : history) {
                            System.out.println(attendance.getDate() + " - Present: " + attendance.isPresent());
                        }
                    }
                    break;

                case 3:
                    float penalty = Attendance.calculatePenalty(loggedUser.getUser());
                    System.out.println("Total de penalizacion" + loggedUser.getUser() + ": $" + penalty);
                    break;

                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (option != 0);
    }

    private static void showProductMenu(User loggedUser) throws IOException {

        Artisan artisan = loggedUser.getArtisan();

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("---PRODUCTOS---\n");
            System.out.println("1. Añadir productos");
            System.out.println("2.Editar productos");
            System.out.println("3. Borrar productos");
            System.out.println("0. Volver al menu principal");
            System.out.println("Escoja una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Product.addProduct(scanner, loggedUser.getArtisan().getName());

                    break;

                case 2:
                    showEditProductSubMenu();
                    break;

                case 3:
                    Product.deleteProduct();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("IOpcion no valida.");
            }
        } while (option != 0);
    }

    public static void showEditProductSubMenu() {
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
            scanner.nextLine();

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

    private static void showSalesMenu(User loggedUser) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- VENTAS ---");
            System.out.println("1. Registrar venta Diaria");
            System.out.println("2. Buscar reporte por fecha");
            System.out.println("3. Reportes menusales");
            System.out.println("0. Regresar");
            System.out.print("Elige una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    inventory.showGeneralInventory();
                    System.out.print("Ingrese el ID del producto que desea vender: ");
                    String productId = scanner.nextLine();

                    System.out.print("Ingrese la cantidad vendida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    SalesReport.registerSale(inventory, productId, quantity);
                    break;

                case 2:
                    SearchReport.InsertDate();
                    break;
                
                case 3:
                    MontlyReport.generateMonthlyReport();
                    break;

                case 0:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        } while (option != 0);
    }

    //public Menu(Inventory inventory) {
     //   this.inventory = inventory;

    //}

    private static void showTheInventoryMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("\n----- Inventario -----");
            System.out.println("1. Ver el inventario general");
            System.out.println("2. Ver el invetario personal");
            System.out.println("0. Volver al menu principal");
            System.out.println("Selecciona una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
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