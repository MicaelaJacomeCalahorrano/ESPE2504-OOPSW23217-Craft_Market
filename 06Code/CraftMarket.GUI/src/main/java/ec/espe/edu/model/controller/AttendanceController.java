/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.model.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class AttendanceController {
 private static final MongoDatabase db = MongoConnection.getDatabase();
    private static final MongoCollection<Document> attendanceCollection = db.getCollection("Attendance");
    public AttendanceController() {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
    }
     public RegisterAttendanceResult registerAttendance(String artisanName, String dateString, boolean confirmed) {
        if (artisanName == null || artisanName.trim().isEmpty()) {
            return new RegisterAttendanceResult(false, "El nombre del artesano no puede estar vacío.");
        }
        if (dateString == null || dateString.trim().isEmpty()) {
            return new RegisterAttendanceResult(false, "La fecha no puede estar vacía.");
        }

        try {
            // Se asume el formato dd/MM/yyyy para la entrada
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(dateString);

            Document attendanceDoc = new Document("artisanName", artisanName)
                                        .append("date", date)
                                        .append("confirmed", confirmed);

            attendanceCollection.insertOne(attendanceDoc);
            return new RegisterAttendanceResult(true, "Asistencia registrada exitosamente.");

        } catch (ParseException e) {
            return new RegisterAttendanceResult(false, "Formato de fecha inválido. Use dd/MM/yyyy.");
        } catch (Exception e) {
            System.err.println("Error al registrar asistencia en el controlador: " + e.getMessage());
            e.printStackTrace();
            return new RegisterAttendanceResult(false, "Error al registrar asistencia: " + e.getMessage());
        }
    }
      public static class RegisterAttendanceResult {
        private final boolean success;
        private final String message;

        public RegisterAttendanceResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
