
package ec.espe.edu.model;

import java.time.LocalDate;
import java.util.Date;
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

    try (BufferedReader reader = new BufferedReader(new FileReader("attendance.csv"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length == 3 && fields[0].equals(username)) {
                boolean present = Boolean.parseBoolean(fields[2]);
                if (!present) {
                    missedDays++;
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading attendance file: " + e.getMessage());
    }

    return missedDays * 5.0f; // $5 per missed day
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
