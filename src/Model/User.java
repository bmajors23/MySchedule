package Model;


import java.sql.Time;

/** User class
 *
 */
public class User {
    private int userID;
    private String username;
    private String password;

    /** User constructor
     * @param userID
     * @param username
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param updatedBy
     */
    public User(int userID, String username, String password, Time createDate, String createdBy, Time lastUpdate, String updatedBy) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

//    public int swapUserNameAndID(String name) {
//        int userID = 0;
//        for (User user : SavedData.getAllUsers()) {
//            if (user.getUsername() == name) {
//                userID = user.getUserID();
//            }
//        }
//        return userID;
//    }

//    public String swapUserIDAndName(int ID) {
//        String userName = null;
//        for (User user : SavedData.getAllUsers()) {
//            if (user.getUserID() == ID) {
//                userName = user.getUsername();
//            }
//        }
//        return userName;
//    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the user id to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
