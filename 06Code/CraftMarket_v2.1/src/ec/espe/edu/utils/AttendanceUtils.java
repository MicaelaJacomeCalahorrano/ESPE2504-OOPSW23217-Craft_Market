
package ec.espe.edu.utils;

import ec.espe.edu.model.Attendance;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceUtils {

    private static final String FILE_NAME = "attendance.csv";

    public static void saveAttendanceRecord(Attendance attendance) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append mode
            fw.write(attendance.toCSV() + "\n");
            System.out.println("Asistencia guardada correctamente por " + attendance.getUsername());
        } catch (IOException e) {
            System.out.println("Error al guardar asistencia " + e.getMessage());
        }
    }
}
