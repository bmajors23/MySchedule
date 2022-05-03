package Helper;

import Model.Country;
import Model.Customer;
import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountriesQuery {


    public static void select(int countryID) throws SQLException {
        String sql = "SELECT * FROM Countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryIDFK = rs.getInt("Country_ID");
            String country = rs.getString("Country");
        }
    }

    public static ObservableList<Country> populateCountryTable() throws SQLException {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryIDFK = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country C = new Country(countryIDFK, countryName);
            allCountries.add(C);
        }
        return allCountries;
    }

}