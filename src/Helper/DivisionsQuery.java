package Helper;

import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class serves as query to perform sql statements on the sql database
 *
 */
public abstract class DivisionsQuery {


    /** This method selects a specific division based on the country ID provided
     * @param countryID
     * @return
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> select(int countryID) throws SQLException {
        ObservableList<FirstLevelDivision> selectedDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryIDFK = rs.getInt("Country_ID");
            FirstLevelDivision D = new FirstLevelDivision(divisionID, division, countryIDFK);
            selectedDivisions.add(D);
        }
        return selectedDivisions;
    }

    /** This method populates the division table with data
     * @return
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> populateDivisionTable() throws SQLException {
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionIDFK = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            FirstLevelDivision D = new FirstLevelDivision(divisionIDFK, divisionName, countryID);
            allDivisions.add(D);

        }
        return allDivisions;
    }

    /** This method looks up a specific division based on a division id provided
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static ObservableList<FirstLevelDivision> lookupDivision(int divisionID) throws SQLException {
        ObservableList<FirstLevelDivision> selectedDivision = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionIDFK = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            FirstLevelDivision D = new FirstLevelDivision(divisionIDFK, divisionName, countryID);
            selectedDivision.add(D);
        }
        return selectedDivision;
    }

}