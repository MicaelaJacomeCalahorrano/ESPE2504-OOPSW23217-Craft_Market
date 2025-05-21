package ec.espe.edu.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.SalesReport;
import ec.espe.edu.model.User;
import ec.espe.edu.utils.SalesUtils;

/**
 *
 * @author jorge
 */
public class Menu {

    public static void MostrarMenu(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n====Menu====");
            System.out.println("User: " + loggedUser.getUser());
            System.out.println("1. Asistencia");
            System.out.println("2. Sales Record");
            System.out.println("3. Attendance");
            System.out.println("4. Exit");
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
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (option != 4);
    }

    private static void showAttendanceMenu(User loggedUser) {
        List<Attendance> attendanceList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- ATTENDANCE MENU ---");
            System.out.println("1. Registrar Asistencia");
            System.out.println("2. Ver historial de asistencia");
            System.out.println("3. Calcular penalizacion");
            System.out.println("0. Regrsar");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    loggedUser.markAttendance();
                    System.out.println("Asistencia registrada por: " + loggedUser.getUser());
                    break;
                case 2:
                    System.out.println("--- Historial de asistencia ---");
                    ArrayList<Attendance> history = Attendance.getAttendanceHistory(loggedUser.getUser());
                    if (history.isEmpty()) {
                        System.out.println("No se encontraron registros de asistencia");
                    } else {
                        for (Attendance attendance : history) {
                            System.out.println(attendance.getDate() + " - Present: " + attendance.isPresent());
                        }
                    }
                    break;

                case 3:
                    float penalty = Attendance.calculatePenalty(loggedUser.getUser());
                    System.out.println("Penalizacion total " + loggedUser.getUser() + ": $" + penalty);
                    break;

                case 0:
                    System.out.println("Regrsando al menu principal...");
                    break;

                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
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
}
