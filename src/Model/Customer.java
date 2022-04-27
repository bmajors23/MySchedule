package Model;

import java.sql.Time;

public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerZip;
    private String customerPhoneNumber;
    private int divisionID;

    public Customer(int customerID, String customerName, String customerAddress, String customerZip, String customerPhoneNumber, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getDivisonID() {
        return divisionID;
    }

    public void setDivisonID(int divisonID) {
        this.divisionID = divisonID;
    }
}



