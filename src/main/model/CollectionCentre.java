package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Covid-19 Collection Center in BC
// Data persistence methods adapted from JsonSterilizationDemo
public class CollectionCentre implements Writable  {
    public String name;
    public String address;
    public String city;
    public String phone; // "unavailable" if there is no phone number
    HealthAuthority healthAuthority;
    boolean needAppointment;
    boolean weekends; // true if center is open on Saturday, Sunday, or both
    boolean driveThrough;
    boolean children; // true if center accepts all children aged 0 - 16, otherwise false
    boolean referral;


    // EFFECTS: constructs a CollectionCenter with given name, address, city, and phone number with its indications on
    //          whether it requires appointments, open on weekends, offers drive through testing, tests children, and
    //          requires referral.
    public CollectionCentre(String name, String address, String city, String phone, HealthAuthority ha,
                            boolean appointment, boolean weekends, boolean driveThrough, boolean children,
                            boolean referral) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        healthAuthority = ha;
        needAppointment = appointment;
        this.weekends = weekends;
        this.driveThrough = driveThrough;
        this.children = children;
        this.referral = referral;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public HealthAuthority getHealthAuthority() {
        return healthAuthority;
    }

    public boolean isNeedAppointment() {
        return needAppointment;
    }

    public boolean isWeekends() {
        return weekends;
    }

    public boolean isDriveThrough() {
        return driveThrough;
    }

    public boolean isChildren() {
        return children;
    }

    public boolean isReferral() {
        return referral;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("address", address);
        json.put("city", city);
        json.put("phone", phone);
        json.put("ha", healthAuthority);
        json.put("a", needAppointment);
        json.put("w", weekends);
        json.put("dt", driveThrough);
        json.put("c", children);
        json.put("r", referral);
        return json;
    }
}
