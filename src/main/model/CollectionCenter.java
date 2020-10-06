package model;

// Represents a Covid-19 Collection Center in BC
public class CollectionCenter {
    String name;
    String address;
    String city;
    String phone;
    boolean needAppointment;
    boolean weekends;
    boolean driveThrough;
    boolean children;

    // EFFECTS: constructs a CollectionCenter with given name, address, city, and phone number that
    // does not require appointments, closed on weekends, doesn't offer drive through testing, and tests children
    public void CollectionCenter(String name, String address, String city, String phone) {
            //stub
    }

    // getters
    public String getName() {
        return null; //stub
    }

    public String getAddress() {
        return null;  //stub
    }

    public String getCity() {
        return null; //stub
    }

    public String getPhone() {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: sets the collection center to require appointments
    public void setAppointment() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: sets the collection center to be open on weekends
    public void setWeekends(){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: sets the collection center to offer drive-through testing
    public void setDriveThrough(){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: sets the collection center to not take children
    public void setChildren (){
        //stub
    }


}
