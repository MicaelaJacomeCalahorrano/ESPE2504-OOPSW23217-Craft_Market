
package ec.espe.edu.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Michael Chicaiza CraftMarket DCCO ESPE
 */
public class Attendance {
    private String username;
    private LocalDate date;
    private boolean present;

    public Attendance(String username, LocalDate date, boolean present) {
        this.username = username;
        this.date = date;
        this.present = present;
    }

    public static ArrayList<Attendance> getAttendanceHistory(String username) {
        ArrayList<Attendance> history = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("attendance.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3 && fields[0].equals(username)) {
                    LocalDate date = LocalDate.parse(fields[1]);
                    boolean present = Boolean.parseBoolean(fields[2]);
                    history.add(new Attendance(username, date, present));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading attendance history: " + e.getMessage());
        }

        return history;
    }
   public static float calculatePenalty(String username) {
    int missedDays = 0;
    LocalDate today = LocalDate.now();
    LocalDate startOfMonth = today.withDayOfMonth(1);

    
    ArrayList<Attendance> userAttendance = getAttendanceHistory(username);

   
    Map<LocalDate, Boolean> attendanceMap = new HashMap<>();

    for (Attendance record : userAttendance) {
        LocalDate date = record.getDate();
        if (!attendanceMap.containsKey(date)) {
            attendanceMap.put(date, record.isPresent());
        }
    }

    for (LocalDate date = startOfMonth; !date.isAfter(today); date = date.plusDays(1)) {
        
        int dayOfWeek = date.getDayOfWeek().getValue(); 
        if (dayOfWeek <= 6) {
            Boolean present = attendanceMap.get(date);
            if (present == null || !present) {
                
                missedDays++;
                System.out.println(" Ausente el: " + date);
            }
        }
    }

    float penalty = missedDays * 5.0f;
    System.out.println("Total días ausentes: " + missedDays);
    return penalty;
}




    public String getUsername() {
        return username;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    public String toCSV() {
        return username + "," + date.toString() + "," + present;
    }
}
