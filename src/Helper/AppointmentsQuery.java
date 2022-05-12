package Helper;

import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

/** AppointmentsQuery. This class serves as a query to perform sql statements on the sql database
 *
 */
public abstract class AppointmentsQuery {

    /** This method will be called whenever we want to create a dialog box to convey information to the user
     * @param infoMessage
     * @param titleBar
     * @param headerMessage
     */
    public static void dialogBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

    /** This method will insert a new appointment into the sql database
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateAndTime
     * @param endDateAndTime
     * @param customerID
     * @param userID
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static int insert(String title, String description, String location, String type, Timestamp startDateAndTime, Timestamp endDateAndTime, int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startDateAndTime);
        ps.setTimestamp(6, endDateAndTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        ps.setInt(9, contactID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method will update an existing appointment in the sql database
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
     * @return
     * @throws SQLException
     */
    public static int update(int appointmentID, String title, String description, String location, String type, Timestamp startDateAndTime, Timestamp endDateAndTime, int customerID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? , Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startDateAndTime);
        ps.setTimestamp(6, endDateAndTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        ps.setInt(9, contactID);
        ps.setInt(10, appointmentID);
        return ps.executeUpdate();
    }

    /** This method will delete an item from the appointments in the sql database
     * @param appointmentID
     * @return
     * @throws SQLException
     */
    public static int delete(int appointmentID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** This method will select a specific item from the appointments sql database
     * @param appointmentID
     * @throws SQLException
     */
    public static void select(int appointmentID) throws SQLException {
        String sql = "SELECT * FROM Appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentIDFK = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startDateAndTime = rs.getTimestamp("Start");
            Timestamp endDateAndTime = rs.getTimestamp("End");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

        }
    }

    /** This method will populate the tableview in the appointments menu with data from the sql database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> populateAppointmentTable() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentIDFK = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startDateAndTime = rs.getTimestamp("Start");
            Timestamp endDateAndTime = rs.getTimestamp("End");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentIDFK, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
            allAppointments.add(A);
        }
        return allAppointments;
    }


    /** This method will determine if the customer has an associated appointment so we can determine if we are able to delete the customer record or not
     * @param customerID
     * @return
     * @throws SQLException
     */
    public static boolean associatedAppointment(int customerID) throws SQLException {
        String sql = "SELECT * FROM Appointments WHERE Customer_ID = '" + customerID + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return !rs.next();
    }

    /** This method allows the user to filter appointment data by month
     * @param date
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> filterByMonth(LocalDate date) throws SQLException {
        ObservableList<Appointment> allAppointmentsByMonth = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments WHERE Start BETWEEN '" + date + "' and '" + date.plusMonths(1) + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentIDFK = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startDateAndTime = rs.getTimestamp("Start");
            Timestamp endDateAndTime = rs.getTimestamp("End");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentIDFK, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
            allAppointmentsByMonth.add(A);
        }
        return allAppointmentsByMonth;
    }

    /** This method allows the user to filter appointment data by week
     * @param date
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> filterByWeek(LocalDate date) throws SQLException {
        ObservableList<Appointment> allAppointmentsByWeek = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments WHERE Start BETWEEN '" + date + "' and '" + date.plusWeeks(1) + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentIDFK = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startDateAndTime = rs.getTimestamp("Start");
            Timestamp endDateAndTime = rs.getTimestamp("End");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentIDFK, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
            allAppointmentsByWeek.add(A);
        }
        return allAppointmentsByWeek;
    }

    /** This method determines if there is an overlapping appointment
     * @param start
     * @param end
     * @return
     * @throws SQLException
     */
    public static boolean overlappingAppointment(int CustomerID, Timestamp start, Timestamp end) throws SQLException {
        Timestamp UTCStart =  Helper.toUTC(start);
        Timestamp UTCEnd = Helper.toUTC(end);
        String sql = "SELECT * FROM Appointments WHERE Customer_ID = '" + CustomerID + "' AND ((Start BETWEEN '" + UTCStart + "' AND '" + UTCEnd + "') OR (End BETWEEN '" + UTCStart + "' AND '" + UTCEnd + "'))";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    /** This method determines if upon login, the user has an appointment scheduled within the next 15 minutes
     * @param start
     * @throws SQLException
     */
    public static void appointmentSoon(Timestamp start) throws SQLException {
        Timestamp UTCStart = Helper.toUTC(start);
        String sql = "SELECT * FROM Appointments WHERE Start BETWEEN '" + UTCStart + "' AND '" + UTCStart.toLocalDateTime().plusMinutes(15) + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            dialogBox("You have an appointment soon!", "Appointment Alert!", "Appointment ID: " + rs.getInt("Appointment_ID") + "\nDate and Time: " + rs.getTimestamp("Start"));
        } else {
            dialogBox("You have no upcoming appointments.", "Appointment Info", "No appointments");
        }
    }

    /** This method generates the first report from the reports menu
     * @param typeEntered
     * @param month
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> reportOne(String typeEntered, String month) throws SQLException {
        String typeToLower = typeEntered.toLowerCase();
        String monthToLower = month.toLowerCase();
        int monthNum = 0;
        if (monthToLower.equals("january")) {
            monthNum = 1;
        } else if (monthToLower.equals("february")) {
            monthNum = 2;
        } else if (monthToLower.equals("march")) {
            monthNum = 3;
        } else if (monthToLower.equals("april")) {
            monthNum = 4;
        } else if (monthToLower.equals("may")) {
            monthNum = 5;
        } else if (monthToLower.equals("june")) {
            monthNum = 6;
        } else if (monthToLower.equals("july")) {
            monthNum = 7;
        } else if (monthToLower.equals("august")) {
            monthNum = 8;
        } else if (monthToLower.equals("september")) {
            monthNum = 9;
        } else if (monthToLower.equals("october")) {
            monthNum = 10;
        } else if (monthToLower.equals("november")) {
            monthNum = 11;
        } else if (monthToLower.equals("december")) {
            monthNum = 12;
        }
        ObservableList<Appointment> allAppointmentsByTypeAndMonth = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments WHERE Lower(Type) = '" + typeToLower + "' AND EXTRACT(MONTH FROM Start) = " + monthNum + "";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentIDFK = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp startDateAndTime = rs.getTimestamp("Start");
            Timestamp endDateAndTime = rs.getTimestamp("End");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentIDFK, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
            allAppointmentsByTypeAndMonth.add(A);
        }
        return allAppointmentsByTypeAndMonth;
    }

    /** This method generates the second report from the reports menu
     * @param contactName
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> reportTwo(String contactName) throws SQLException {
        Integer contactID = 0;
        String contactNameToLower = contactName.toLowerCase();
        ObservableList<Appointment> allAppointmentsByContactName = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contacts WHERE Contact_Name = '" + contactNameToLower + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            contactID = rs.getInt("Contact_ID");
        }
        String sql2 = "SELECT * FROM Appointments WHERE Contact_ID = " + contactID;
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int appointmentIDFK = rs2.getInt("Appointment_ID");
            String title = rs2.getString("Title");
            String description = rs2.getString("Description");
            String location = rs2.getString("Location");
            String type = rs2.getString("Type");
            Timestamp startDateAndTime = rs2.getTimestamp("Start");
            Timestamp endDateAndTime = rs2.getTimestamp("End");
            int customerID = rs2.getInt("Customer_ID");
            int userID = rs2.getInt("User_ID");
            int contactIDFK = rs2.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentIDFK, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactIDFK);
            allAppointmentsByContactName.add(A);
        }
        return allAppointmentsByContactName;
    }

    /** This method generates the third report from the reports menu
     * @param CustomerName
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointment> reportThree(String CustomerName) throws SQLException {
        Integer customerID = 0;
        String customerNameToLower = CustomerName.toLowerCase();
        ObservableList<Appointment> allAppointmentsByCustomerName = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Customers WHERE Customer_Name = '" + customerNameToLower + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            customerID = rs.getInt("Customer_ID");
        }
        String sql2 = "SELECT * FROM Appointments WHERE Customer_ID = " + customerID;
        PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int appointmentID = rs2.getInt("Appointment_ID");
            String title = rs2.getString("Title");
            String description = rs2.getString("Description");
            String location = rs2.getString("Location");
            String type = rs2.getString("Type");
            Timestamp startDateAndTime = rs2.getTimestamp("Start");
            Timestamp endDateAndTime = rs2.getTimestamp("End");
            int customerIDFK = rs2.getInt("Customer_ID");
            int userID = rs2.getInt("User_ID");
            int contactID = rs2.getInt("Contact_ID");
            Appointment A = new Appointment(appointmentID, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
            allAppointmentsByCustomerName.add(A);
        }
        return allAppointmentsByCustomerName;
    }
    }

