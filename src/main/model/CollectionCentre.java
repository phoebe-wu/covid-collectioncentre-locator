package model;

import java.util.List;

// Represents a Covid-19 Collection Center in BC
public class CollectionCentre {
    public String name;
    public String address;
    public String city;
    public String phone; // null if there is no phone number
    HealthAuthority healthAuthority;
    boolean needAppointment;
    boolean weekends; // true if center is open on Saturday, Sunday, or both
    boolean driveThrough;
    boolean children; // true if center accepts all children aged 0 - 16, otherwise false
    boolean referral;


    // EFFECTS: constructs a CollectionCenter with given name, address, city, and phone number with its indications on
    // whether it requires appointments, open on weekends, offers drive through testing, tests children, and
    // requires referral.
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

    public HealthAuthority getHealthAuthority() {
        return healthAuthority;
    }
}
