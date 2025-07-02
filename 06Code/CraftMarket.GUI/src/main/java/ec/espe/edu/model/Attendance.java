package ec.espe.edu.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Attendance {
    private String username;
    private LocalDate date;
    private boolean present;

    private static final MongoDatabase db = MongoConnection.getDatabase();
    private static final MongoCollection<Document> collection = db.getCollection("attendance");

 
    public Attendance(String username, LocalDate date, boolean present) {
        this.username = username;
        this.date = date;
        this.present = present;
    }

    public static void markPresent(String username) {
        Attendance attendance = new Attendance(username, LocalDate.now(), true);
        saveAttendance(attendance);
        System.out.println("Asistencia registrada para: " + username + " en " + LocalDate.now());
    }

    public static void saveAttendance(Attendance attendance) {
        Document doc = new Document()
                .append("username", attendance.getUsername())
                .append("date", attendance.getDate().toString())
                .append("present", attendance.isPresent());
        collection.insertOne(doc);
    }

    public static ArrayList<Attendance> getAttendanceHistory(String username) {
        ArrayList<Attendance> history = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find(new Document("username", username)).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                LocalDate date = LocalDate.parse(doc.getString("date"));
                boolean present = doc.getBoolean("present");
                history.add(new Attendance(username, date, present));
            }
        } finally {
            cursor.close();
        }
        return history;
    }

    public static float calculatePenalty(String username) {
        ArrayList<Attendance> attendanceList = getAttendanceHistory(username);
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);

        Map<LocalDate, Boolean> attendanceMap = new HashMap<>();
        for (Attendance a : attendanceList) {
            attendanceMap.put(a.getDate(), a.isPresent());
        }

        int missedDays = 0;
        for (LocalDate date = startOfMonth; !date.isAfter(today); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            if (dayOfWeek <= 7) { 
                Boolean present = attendanceMap.get(date);
                if (present == null || !present) {
                    missedDays++;
                }
            }
        }

        return missedDays * 5.0f;
    }

    public static void printHistory(String username) {
        ArrayList<Attendance> history = getAttendanceHistory(username);
        if (history.isEmpty()) {
            System.out.println("No hay historial de asistencia para " + username);
        } else {
            System.out.println("--- Historial de asistencia de " + username + " ---");
            for (Attendance attendance : history) {
                System.out.println(attendance.getDate() + " - Presente: " + attendance.isPresent());
            }
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPresent(boolean present) {
        this.present = present;
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


}
