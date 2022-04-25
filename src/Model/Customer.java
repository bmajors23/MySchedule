package Model;

import java.sql.Time;

public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private int customerZip;
    private String customerPhoneNumber;
    private Time createDate;
    private String createdBy;
    private Time lastUpdate;
    private String updatedBy;
    private int divisonID;

    public Customer(int customerID, String customerName, String customerAddress, int customerZip, String customerPhoneNumber, Time createDate, String createdBy, Time lastUpdate, String updatedBy, int divisonID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerPhoneNumber = customerPhoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.updatedBy = updatedBy;
        this.divisonID = divisonID;
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

    public int getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(int customerZip) {
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

    public Time getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Time createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Time getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Time lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getDivisonID() {
        return divisonID;
    }

    public void setDivisonID(int divisonID) {
        this.divisonID = divisonID;
    }
}



