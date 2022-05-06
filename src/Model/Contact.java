package Model;

/** Contact class
 *
 */
public class Contact {
    private int ID;
    private String name;
    private String email;

    /** Contact constructor
     * @param ID
     * @param name
     * @param email
     */
    public Contact(int ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    /**
     * @return method to override toString and return the name
     */
    @Override
    public String toString() {
        return name;
    }

//    public int swapContactNameAndID(String name) {
//        int contactID = 0;
//        for (Contact contact : SavedData.getAllContacts()) {
//            if (contact.getName() == name) {
//                contactID = contact.getID();
//            }
//        }
//        return contactID;
//    }
//
//    public String swapContactIDAndName(int ID) {
//        String contactName = null;
//        for (Contact contact : SavedData.getAllContacts()) {
//            if (contact.getID() == ID) {
//                contactName = contact.getName();
//            }
//        }
//        return contactName;
//    }

    /**
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the id to be set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
