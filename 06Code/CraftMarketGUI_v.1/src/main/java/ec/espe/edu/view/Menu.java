package ec.espe.edu.view;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.Inventory;
import ec.espe.edu.model.Product;
import ec.espe.edu.model.SalesReport;
import ec.espe.edu.model.SearchReport;
import ec.espe.edu.model.User;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
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
            scanner.nextLine();

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
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- ATTENDANCE MENU ---");
            System.out.println("1. Marcar asistencia");
            System.out.println("2. Ver historial de asistencia");
            System.out.println("3. Calcular penalización");
            System.out.println("0. Regresar");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    loggedUser.markAttendance();
                    break;
                case 2:
                    List<Attendance> history = Attendance.getAttendanceHistory(loggedUser.getUser());
                    if (history.isEmpty()) {
                        System.out.println("No se encontró historial de asistencia.");
                    } else {
                        System.out.println("--- Historial de asistencia ---");
                        for (Attendance a : history) {
                            System.out.println(a.getDate() + " - Presente: " + a.isPresent());
                        }
                    }
                    break;
                case 3:
                    float penalty = Attendance.calculatePenalty(loggedUser.getUser());
                    System.out.println("Total de penalización para " + loggedUser.getUser() + ": $" + penalty);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void showProductMenu(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--- PRODUCTOS ---");
            System.out.println("1. Añadir producto");
            System.out.println("2. Editar producto");
            System.out.println("3. Borrar producto");
            System.out.println("4. Mostrar productos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Escoja una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                 
                    System.out.print("Ingrese ID del producto: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese nombre del producto: ");
                    String name = scanner.nextLine();
                    System.out.print("Ingrese precio unitario: ");
                    float price = scanner.nextFloat();
                    System.out.print("Ingrese stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer

                    Product newProduct = new Product(id, name, price, stock, loggedUser.getArtisan().getName());
                    Product.addProduct(newProduct);
                    System.out.println("Producto añadido correctamente.");
                    break;

                case 2:
                    showEditProductSubMenu();
                    break;

                case 3:
                    System.out.print("Ingrese el ID del producto a eliminar: ");
                    String deleteId = scanner.nextLine();
                    Product.deleteProduct(deleteId);
                    System.out.println("Producto eliminado si existía.");
                    break;

                case 4:
                    List<Product> products = Product.getAllProducts();
                    System.out.println("--- Productos ---");
                    for (Product p : products) {
                        System.out.println("ID: " + p.getId() + " | Nombre: " + p.getName() + " | Precio: " + p.getUnitPrice() + " | Stock: " + p.getStock() + " | Propietario: " + p.getOwner());
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void showEditProductSubMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto que desea editar: ");
        String productId = scanner.nextLine();

        int choice;
        do {
            System.out.println("\n--- Editar Producto ---");
            System.out.println("1. Editar Precio");
            System.out.println("2. Editar Stock");
            System.out.println("0. Volver");
            System.out.print("Selecciona una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese nuevo precio: ");
                    float newPrice = scanner.nextFloat();
                    scanner.nextLine();
                    Product.updateProductPrice(productId, newPrice);
                    System.out.println("Precio actualizado.");
                    break;
                case 2:
                    System.out.print("Ingrese nuevo stock: ");
                    int newStock = scanner.nextInt();
                    scanner.nextLine();
                    Product.updateProductStock(productId, newStock);
                    System.out.println("Stock actualizado.");
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
            System.out.println("\n--- VENTAS ---");
            System.out.println("1. Registrar venta diaria");
            System.out.println("2. Buscar reporte por fecha");
            System.out.println("3. Reportes mensuales");
            System.out.println("0. Regresar");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    inventory.showGeneralInventory();
                    System.out.print("Ingrese el ID del producto: ");
                    String productId = scanner.nextLine();
                    System.out.print("Cantidad vendida: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    // Buscar el producto para obtener datos y registrar la venta
                    Product product = Product.findById(productId);
                    if (product == null) {
                        System.out.println("Producto no encontrado.");
                    } else if (product.getStock() < quantity) {
                        System.out.println("Stock insuficiente.");
                    } else {
                        float total = product.getUnitPrice() * quantity;
                        SalesReport sale = new SalesReport(product.getName(), product.getUnitPrice(), quantity, total, product.getOwner());
                        SalesReport.registerSale(sale);

                        // Actualizar stock
                        Product.updateProductStock(productId, product.getStock() - quantity);
                        System.out.println("Venta registrada correctamente.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la fecha a buscar (yyyy-mm-dd): ");
                    String inputDate = scanner.nextLine();
                    try {
                        LocalDate date = LocalDate.parse(inputDate);
                        SearchReport.printReportByDate(date);
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido.");
                    }
                    break;

                case 3:
                    // Aquí debes implementar o llamar a tu método de reportes mensuales
                    System.out.println("Función de reportes mensuales no implementada.");
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private static void showTheInventoryMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n----- Inventario -----");
            System.out.println("1. Ver inventario general");
            System.out.println("2. Ver inventario personal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    inventory.showGeneralInventory();
                    break;
                case 2:
                    System.out.print("Nombre del artesano: ");
                    String artisanName = scanner.nextLine();
                    inventory.showPersonalInventory(new Artisan(artisanName));
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }
}
