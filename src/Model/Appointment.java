package Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

/** Appointment class
 *
 */
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

    /** Constructor for Appointments
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateAndTime
     * @param endDateAndTime
     * @param customerID
     * @param userID
     * @param contactID
     */
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

    /**
     * @return the appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * @param appointmentID the appointmentID to be set
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to be set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID the contact ID to be set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start date and time
     */
    public Timestamp getStartDateAndTime() {
        return startDateAndTime;
    }

    /**
     * The start date and time to be set
     */
    public void setStartDateAndTime() {
        this.startDateAndTime = startDateAndTime;
    }

    /**
     * @return the end date and time
     */
    public Timestamp getEndDateAndTime() {
        return endDateAndTime;
    }

    /**
     * the end date and time to be set
     */
    public void setEndDateAndTime() {
        this.endDateAndTime = endDateAndTime;
    }

    /**
     * @return the customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerId to be set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the user id to be set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
