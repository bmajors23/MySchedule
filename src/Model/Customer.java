package Model;

import java.sql.Time;

/** Customer Class
 *
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhoneNumber;
    private int divisionID;

    /** Customer Constructor
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerZip
     * @param customerPhoneNumber
     * @param divisionID
     */
    public Customer(int customerID, String customerName, String customerAddress, String customerZip, String customerPhoneNumber, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName  the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerAddres
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to be set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the customer Zip
     */
    public String getCustomerZip() {
        return customerZip;
    }

    /**
     * @param customerZip the customerZip to be set
     */
    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    /**
     * @return the customer phone number
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @param customerPhoneNumber the customer phone number to be set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * @return the customer Id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to be set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID the division id to be set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}



