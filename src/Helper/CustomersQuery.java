package Helper;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomersQuery {

    public static int insert(String customerName, String customerAddress, String customerZip, String customerPhoneNumber, int divisionID) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerZip);
        ps.setString(4, customerPhoneNumber);
        ps.setInt(5, divisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int customer_ID, String customerName, String customerAddress, String customerZip, String customerPhoneNumber, int divisionID) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, customerZip);
        ps.setString(4, customerPhoneNumber);
        ps.setInt(5, divisionID);
        ps.setInt(6, customer_ID);
        return ps.executeUpdate();
    }

    public static int delete(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select(int customerID) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerIDFK = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerZip = rs.getString("Postal_Code");
            String customerPhoneNumber = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
        }
    }

    public static boolean selectExists(int customerID) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static ObservableList<Customer> populateCustomerTable() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Customers";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerIDFK = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerZip = rs.getString("Postal_Code");
            String customerPhoneNumber = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            Customer C = new Customer(customerIDFK, customerName, customerAddress, customerZip, customerPhoneNumber, divisionID);
            allCustomers.add(C);
        }
        return allCustomers;
    }

    public static int returnLastCustomerID() throws SQLException {
        int customerID = 0;
        String sql = "SELECT Customer_ID FROM CUSTOMERS ORDER BY Customer_ID DESC LIMIT 1";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            customerID = rs.getInt("Customer_ID");
        }
        return customerID;
    }

}
