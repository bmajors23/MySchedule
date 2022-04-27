package Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp startDateAndTime;
    private Timestamp endDateAndTime;
    private int customerID;
    private int userID;
    private int contactID;

    public Appointment(int appointmentID, String title, String description, String location, String type, Timestamp startDateAndTime, Timestamp endDateAndTime, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime() {
        this.startDateAndTime = startDateAndTime;
    }

    public Timestamp getEndDateAndTime() {
        return endDateAndTime;
    }

    public void setEndDateAndTime() {
        this.endDateAndTime = endDateAndTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
