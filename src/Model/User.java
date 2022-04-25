package Model;


import java.sql.Time;

public class User {
    private int userID;
    private String username;
    private String password;
    private Time createDate;
    private String createdBy;
    private Time lastUpdate;
    private String updatedBy;

    public User(int userID, String username, String password, Time createDate, String createdBy, Time lastUpdate, String updatedBy) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.updatedBy = updatedBy;
    }

    public int swapUserNameAndID(String name) {
        int userID = 0;
        for (User user : SavedData.getAllUsers()) {
            if (user.getUsername() == name) {
                userID = user.getUserID();
            }
        }
        return userID;
    }

    public String swapUserIDAndName(int ID) {
        String userName = null;
        for (User user : SavedData.getAllUsers()) {
            if (user.getUserID() == ID) {
                userName = user.getUsername();
            }
        }
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
