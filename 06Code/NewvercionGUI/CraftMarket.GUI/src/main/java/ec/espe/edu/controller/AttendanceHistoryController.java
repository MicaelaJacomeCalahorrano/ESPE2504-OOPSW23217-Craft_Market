/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.utils.MongoConnection;
import ec.espe.edu.utils.MongoConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class AttendanceHistoryController {

    private final MongoCollection<Document> attendanceCollection;

    public AttendanceHistoryController() {
        MongoDatabase db = MongoConnection.getDatabase();
        attendanceCollection = db.getCollection("Attendance"); 
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING); 
    }

    public List<Document> getArtisanAttendanceHistory(String artisanName) {
        List<Document> history = new ArrayList<>();
        try {
            
            Document query = new Document("artisanName", artisanName);

            
            FindIterable<Document> documents = attendanceCollection.find(query);

            
            for (Document doc : documents) {
                history.add(doc);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el historial de asistencia: " + e.getMessage());
            e.printStackTrace();
            
        }
        return history;
    }

    public boolean printAttendanceHistory() {
        System.out.println("Lógica para imprimir el historial de asistencia.");
        return true;
    }
}
