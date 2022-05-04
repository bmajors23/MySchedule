package Helper;

import Model.Contact;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactsQuery {

    public static ObservableList<Contact> populateContactTable() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contacts";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactIDFK = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact C = new Contact(contactIDFK, contactName, email);
            allContacts.add(C);
        }
        return allContacts;
    }

    public static ObservableList<Contact> lookupContact(int contactID) throws SQLException {
        ObservableList<Contact> selectedContact = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactIDFK = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact C = new Contact(contactIDFK, contactName, email);
            selectedContact.add(C);
        }
        return selectedContact;
    }
}


