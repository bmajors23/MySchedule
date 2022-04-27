package Model;

import Helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class SavedData {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() {

        try {
            String sql = "SELECT * FROM client_schedule.customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerZip = rs.getString("Postal_Code");
                String customerPhoneNumber = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                Customer C = new Customer(customerId, customerName, customerAddress, customerZip, customerPhoneNumber, divisionID);
                allCustomers.add(C);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return allCustomers;
    }

    public static ObservableList<Appointment> getAllAppointments() {

        try {
            String sql = "SELECT * FROM client_schedule.appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp startDateAndTime = rs.getTimestamp("Start");
                Timestamp endDateAndTime = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointment A = new Appointment(appointmentID, title, description, location, type, startDateAndTime, endDateAndTime, customerID, userID, contactID);
                allAppointments.add(A);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return allAppointments;
    }

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }
}

