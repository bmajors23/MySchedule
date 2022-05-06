package Model;


/** The country classs
 *
 */
public class Country {
    private int countryID;
    private String countryName;

    /** The country constructor
     * @param countryID
     * @param countryName
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /** Override method of toString so it returns countryName
     * @return countryName
     */
    @Override
    public String toString() {
        return countryName;
    }

    /**
     * @return the country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID the country id to be set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryname to be set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
