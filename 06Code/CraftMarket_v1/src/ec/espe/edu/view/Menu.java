package ec.espe.edu.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.espe.edu.model.Artisan;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.model.User;

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
            System.out.println("1. Attendence");
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
                    System.out.println("==== Sales Record ====");
                    System.out.println("0. Back");
                    int ventaOption = scanner.nextInt();
                    if (ventaOption == 0) {
                        break;
                    }
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
}
