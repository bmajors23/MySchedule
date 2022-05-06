package Model;

import java.sql.Time;

/** Firstleveldivision class
 *
 */
public class FirstLevelDivision {
    private int divisionID;
    private String divisionName;
    private int countryID;

    /** First level division constructor
     * @param divisionID
     * @param divisionName
     * @param countryID
     */
    public FirstLevelDivision(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /** Over ride method toString to make sure to return divisionName
     * @return
     */
    @Override
    public String toString() {
        return divisionName;
    }

    /**
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID the division id to set
     */
    public void setDivisionID(int divisionID) {
        divisionID = divisionID;
    }

    /**
     * @return the division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName the division name to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return the country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID the country id to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
