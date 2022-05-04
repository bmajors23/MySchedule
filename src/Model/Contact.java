package Model;

public class Contact {
    private int ID;
    private String name;
    private String email;

    public Contact(int ID, String name, String email) {
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }

    public int swapContactNameAndID(String name) {
        int contactID = 0;
        for (Contact contact : SavedData.getAllContacts()) {
            if (contact.getName() == name) {
                contactID = contact.getID();
            }
        }
        return contactID;
    }

    public String swapContactIDAndName(int ID) {
        String contactName = null;
        for (Contact contact : SavedData.getAllContacts()) {
            if (contact.getID() == ID) {
                contactName = contact.getName();
            }
        }
        return contactName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
