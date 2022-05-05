package Helper;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public abstract class AppointmentsQuery {

    public static void dialogBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.showAndWait();
    }

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

    public static int delete(int appointmentID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, appointmentID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

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
//            System.out.print(appointmentIDFK + " | ");
//            System.out.print(title + " | ");
//            System.out.print(description + " | ");
//            System.out.print(location + " | ");
//            System.out.print(type + " | ");
//            System.out.print(startDateAndTime + " | ");
//            System.out.print(endDateAndTime + " | ");
//            System.out.print(customerID + " | ");
//            System.out.print(userID + "\n");

        }
    }

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


    public static boolean associatedAppointment(int customerID) throws SQLException {
        String sql = "SELECT * FROM Appointments WHERE Customer_ID = '" + customerID + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        return !rs.next();
    }

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

    public static ObservableList<Appointment> filterByWeek(LocalDate date) throws SQLException {
        ObservableList<Appointment> allAppointmentsByWeek = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Appointments WHERE Start BETWEEN '" + date + "' and '" + date.plusWeeks(1) + "'";
        System.out.println(sql);
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

    public static boolean overlappingAppointment(Timestamp start, Timestamp end) throws SQLException {
        Timestamp UTCStart =  Helper.toUTC(start);
        Timestamp UTCEnd = Helper.toUTC(end);
        String sql = "SELECT * FROM Appointments WHERE (Start BETWEEN '" + UTCStart + "' AND '" + UTCEnd + "') OR (End BETWEEN '" + UTCStart + "' AND '" + UTCEnd + "')";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static void appointmentSoon(Timestamp start) throws SQLException {
        Timestamp UTCStart = Helper.toUTC(start);
        String sql = "SELECT * FROM Appointments WHERE Start BETWEEN '" + UTCStart + "' AND '" + UTCStart.toLocalDateTime().plusMinutes(15) + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println(rs);
        if (rs.next()) {
            dialogBox("You have an appointment soon!", "Appointment Alert!", "Appointment ID: " + rs.getInt("Appointment_ID") + "\nDate and Time: " + rs.getTimestamp("Start"));
        } else {
            dialogBox("You have no upcoming appointments.", "Appointment Info", "No appointments");
        }
    }

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
        System.out.println(monthNum);
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
    }

