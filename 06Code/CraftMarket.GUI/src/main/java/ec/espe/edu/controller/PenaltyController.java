package ec.espe.edu.model.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.*;

public class PenaltyController {
    private static final double PENALTY_PER_DAY = 5.00;

    public static class PenaltyResult {
        public final List<Date> absentDates;
        public final int totalAbsentDays;
        public final double totalPenalty;

        public PenaltyResult(List<Date> absentDates, int totalAbsentDays, double totalPenalty) {
            this.absentDates = absentDates;
            this.totalAbsentDays = totalAbsentDays;
            this.totalPenalty = totalPenalty;
        }
    }

    public static PenaltyResult calculatePenalty(String artisanName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Date> absentDates = new ArrayList<>();

        try {
            MongoDatabase db = MongoConnection.connect();
            MongoCollection<Document> attendanceCollection = db.getCollection("Attendance");

            Calendar cal = Calendar.getInstance();
            int currentMonth = cal.get(Calendar.MONTH);
            int currentYear = cal.get(Calendar.YEAR);

            Set<String> businessDaysFormatted = new HashSet<>();
            List<Date> businessDays = new ArrayList<>();

            Calendar monthCal = Calendar.getInstance();
            monthCal.set(currentYear, currentMonth, 1);
            monthCal.set(Calendar.HOUR_OF_DAY, 0);
            monthCal.set(Calendar.MINUTE, 0);
            monthCal.set(Calendar.SECOND, 0);
            monthCal.set(Calendar.MILLISECOND, 0);

            while (monthCal.get(Calendar.MONTH) == currentMonth) {
                int dayOfWeek = monthCal.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
                    businessDaysFormatted.add(dateFormat.format(monthCal.getTime()));
                    businessDays.add(monthCal.getTime());
                }
                monthCal.add(Calendar.DAY_OF_MONTH, 1);
            }

            Set<String> confirmedDatesFormatted = new HashSet<>();
            Document query = new Document("artisanName", artisanName).append("confirmed", true);
            FindIterable<Document> documents = attendanceCollection.find(query);
            for (Document doc : documents) {
                Date attendanceDate = doc.getDate("date");
                if (attendanceDate != null) {
                    Calendar attCal = Calendar.getInstance();
                    attCal.setTime(attendanceDate);
                    if (attCal.get(Calendar.MONTH) == currentMonth && attCal.get(Calendar.YEAR) == currentYear) {
                        confirmedDatesFormatted.add(dateFormat.format(attendanceDate));
                    }
                }
            }

            for (Date businessDay : businessDays) {
                String formatted = dateFormat.format(businessDay);
                if (!confirmedDatesFormatted.contains(formatted)) {
                    absentDates.add(businessDay);
                }
            }

            int totalAbsent = absentDates.size();
            double totalPenalty = totalAbsent * PENALTY_PER_DAY;
            return new PenaltyResult(absentDates, totalAbsent, totalPenalty);

        } catch (Exception e) {
            e.printStackTrace();
            return new PenaltyResult(new ArrayList<>(), 0, 0.0);
        }
    }
}

