
package ec.espe.edu.model;
import ec.espe.edu.model.Attendance;
import ec.espe.edu.utils.AttendanceUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author Michael Chicaiza CraftMarket DCCO ESPE
 */
public class User {
    private String user;
    private String password;
    private Artisan artisan;
    private List<Attendance> attendanceList;

    public User(String user, String password, Artisan artisan) {
        this.user = user;
        this.password = password;
        this.artisan = artisan;
          this.attendanceList = new ArrayList<>();
    }

    public Artisan getArtisan() {
        return artisan;
    }

    public boolean verifyUser(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public static User login(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("====Iniciar Sesion  ====");
            System.out.print("Usuario: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Clave: ");
            String enteredPassword = scanner.nextLine();

            for (User user : users) {
                if (user.getUser().equals(enteredUsername) && user.verifyUser(enteredPassword)) {
                    System.out.println("Inicio de secion exitosa: " + user.getUser());
                    return user; 
                }
            }
            System.out.println("Usuario o clave incorrecta vuelva a intentarlo \n");
        }
    }
    
 public void markAttendance() {
    LocalDate today = LocalDate.now();
    Attendance attendance = new Attendance(this.user, today, true); // true = presente
    AttendanceUtils.saveAttendanceRecord(attendance);
}

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArtisan(Artisan artisan) {
        this.artisan = artisan;
    }
    
}
