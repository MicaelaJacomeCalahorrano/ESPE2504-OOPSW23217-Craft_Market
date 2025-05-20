package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class Attendance {
     private Date date;
    private boolean present;

    public Attendance(Date date, boolean present) {
        this.date = date;
        this.present = present;
    }

    public void markPresent() {
        this.present = true;
    }

    public void markAbsent() {
        this.present = false;
    }

    public boolean isPresent() {
        return present;
    }

    public String getDetails() {
        return date.toString() + ": " + (present ? "Presente" : "Ausente");
    }
}


   