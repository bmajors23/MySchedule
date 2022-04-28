package Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class AppointmentsQuery {

    public static int insert(String title, String description, String location, String type, Timestamp startDateAndTime, Timestamp endDateAndTime, int customerID, int userID) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID) VALUES(?, ?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startDateAndTime);
        ps.setTimestamp(6, endDateAndTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(String title, String description, String location, String type, Timestamp startDateAndTime, Timestamp endDateAndTime, int customerID, int userID) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, startDateAndTime);
        ps.setTimestamp(6, endDateAndTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
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

    public static void selectAll() throws SQLException {
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
            System.out.print(appointmentIDFK + " | ");
            System.out.print(title + " | ");
            System.out.print(description + " | ");
            System.out.print(location + " | ");
            System.out.print(type + " | ");
            System.out.print(startDateAndTime + " | ");
            System.out.print(endDateAndTime + " | ");
            System.out.print(customerID + " | ");
            System.out.print(userID + "\n");
        }
    }
}
