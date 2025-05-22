package ec.espe.edu.view;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.DailyReport;
import ec.espe.edu.model.Inventory;
import ec.espe.edu.model.MonthlyReport;
import ec.espe.edu.model.SalesReport;
import ec.espe.edu.model.User;

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

    private static Inventory inventory;

    public static void MostrarMenu(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n====Menu====");
            System.out.println("User: " + loggedUser.getUser());
            System.out.println("1. Attendence");
            System.out.println("2. Sales Record");
            System.out.println("3. Attendance");
            System.out.println("4. Product Management");
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
                    System.out.println("==== Attendance ====");
                    System.out.println("0. Back");
                    int asistenciaOption = scanner.nextInt();
                    if (asistenciaOption == 0) {
                        break;
                    }
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

    public static void showProducts() {
        try (Scanner fileScanner = new Scanner(new File("product_stocks.csv"))) {
            System.out.println("Productos registrados:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                if (data.length < 3) { // Verifica que la línea tenga ID, Nombre y Stock
                    System.out.println("Error: línea corrupta en CSV -> " + line);
                    continue; // Salta esta línea y sigue con la siguiente
                }

                System.out.println("ID: " + data[0] + " | Nombre: " + data[1] + " | Stock: " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    private static void showProductMenu(User loggedUser) {

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

            switch (option) {
                case 1:
                    System.out.println("Enter product ID: ");
                    String productId = scanner.nextLine();

                    System.out.println("Enter product name: ");
                    String productName = scanner.nextLine();

                    System.out.println("Enter initial stock");
                    int stock = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter price: ");
                    float price = Float.parseFloat(scanner.nextLine());

                    CsvUtilsID.save(productId, productName);
                    CsvUtilsStock.save(productId, stock);
                    CsvUtilsPrice.save(productId, price);
                    break;

                case 2:

                    showProducts();

                    System.out.print("Enter product name to edit: ");
                    String productNameToEdit = scanner.nextLine();
                    showEditProductSubMenu(productNameToEdit, artisan);
                    break;

                case 3:
                    System.out.print("Enter product ID to delete: ");
                    String productIdToDelete = scanner.nextLine();

                    DeleteProducts.deleteProduct(productIdToDelete);

                    System.out.println("Product deleted successfully.");
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    public static void showEditProductSubMenu(String productNameToEdit, Artisan artisan) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("---Edit Product---");
            System.out.println("1. Edit Price");
            System.out.println("2. Edit Stock");
            System.out.println("3. Edit Id");
            System.out.println("0. Back");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();

                    System.out.print("Enter new price: ");
                    float newPrice = Float.parseFloat(scanner.nextLine());
                    CsvUtilsPrice.save(productName, newPrice);
                    break;

                case 2:

                    productName = scanner.nextLine();

                    System.out.print("Enter new stock: ");
                    int newStock = Integer.parseInt(scanner.nextLine());
                    CsvUtilsStock.save(productName, newStock);
                    break;

                case 3:
                    System.out.print("Enter old ID: ");
                    String oldId = scanner.nextLine();

                    System.out.print("Enter new ID: ");
                    String newId = scanner.nextLine();
                    CsvUtilsID.save(oldId, newId);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("invalid option.");
            }
        } while (option != 0);
    }

    private static void showSalesMenu(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- SALES MENU ---");
            System.out.println("1. Registrar venta Diaria");
            System.out.println("2. Ver historial de ventas");
            System.out.println("3. Reporte Diario ");
            System.out.println("4. Reporte Mensual");
            System.out.println("0. Regresar");
            System.out.print("Elige una opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese nombre del producto: ");
                    String product = scanner.nextLine();

                    System.out.print("Ingrese precio del producto: ");
                    String priceInput = scanner.nextLine().replace(",", ".");
                    float price = Float.parseFloat(priceInput);

                    System.out.print("Ingrese cantidad vendida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    float total = price * quantity;

                    System.out.print("Ingrese nombre del artesano: ");
                    String artisan = scanner.nextLine();

                    System.out.print("Ingrese el dia: ");
                    String day = scanner.nextLine();
                    if (day.length() == 1) {
                        day = "0" + day;
                    }

                    System.out.print("Ingrese el mes : ");
                    String month = scanner.nextLine();
                    if (month.length() == 1) {
                        month = "0" + month;
                    }

                    System.out.print("Ingrese el año: ");
                    String year = scanner.nextLine();

                    String date = year + "-" + month + "-" + day;

                    SalesReport sale = new SalesReport(product, price, quantity, total, artisan, date);
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
                case 3:
                    System.out.print("Ingrese el dia : ");
                    String repday = scanner.nextLine();
                    if (repday.length() == 1) {
                        repday = "0" + repday;
                    }

                    System.out.print("Ingrese el mes : ");
                    String monthD = scanner.nextLine();
                    if (monthD.length() == 1) {
                        monthD = "0" + monthD;
                    }

                    System.out.print("Ingrese el año : ");
                    String yearD = scanner.nextLine();

                    String dateToCheck = yearD + "-" + monthD + "-" + repday;
                    List<SalesReport> dailySales = DailyReport.filterByDay(SalesUtils.loadSales(), dateToCheck);
                    if (dailySales.isEmpty()) {
                        System.out.println("No Se registarn ventas ");
                    } else {
                        dailySales.forEach(s -> System.out.println(s.getDisplayString()));
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el año : ");
                    String myear = scanner.nextLine();

                    System.out.print("Ingrese el mes : ");
                    String rawMonth = scanner.nextLine();

                    if (rawMonth.length() == 1) {
                        rawMonth = "0" + rawMonth;
                    }

                    String monthToCheck = myear + "-" + rawMonth;

                    List<SalesReport> monthlySales = MonthlyReport.filterByMonth(SalesUtils.loadSales(), monthToCheck);
                    if (monthlySales.isEmpty()) {
                        System.out.println("No hay Registros del mes ");
                    } else {
                        monthlySales.forEach(s -> System.out.println(s.getDisplayString()));
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

    public Menu(Inventory inventory) {
        this.inventory = inventory;

    }

    private static void showTheInventoryMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("\n----- Inventario -----");
            System.out.println("1. Ver el inventario general");
            System.out.println("2. Ver el invetario personal");
            System.out.println("0. Volver al menu principal");
            System.out.println("Selecciona una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    inventory.openProductsFromCVS("productos.csv");
                    inventory.showGeneralInventory();
                    break;
                case 2:
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
