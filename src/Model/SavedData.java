package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SavedData {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    }
}

