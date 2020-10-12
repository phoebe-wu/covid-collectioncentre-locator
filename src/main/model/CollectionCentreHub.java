package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Represents a database of Collection Centres
public class CollectionCentreHub {
    List<CollectionCentre> collectionCentreHub;

    // EFFECTS: constructs an empty list of CollectionCentres
    public CollectionCentreHub() {
        collectionCentreHub = new LinkedList<>();
    }

    // EFFECTS: adds a CollectionCentre to CollectionCentreHub
    public void addCollectionCentre(CollectionCentre c) {
        collectionCentreHub.add(c);
    }

    // EFFECTS: returns the size of CollectionCentreHub
    public int hubSize() {
        return collectionCentreHub.size();
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers in that city
    public List<CollectionCentre> filterCity(String city) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.city == city) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers from that health authority
    public List<CollectionCentre> filterHealthAuthority(HealthAuthority ha) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.healthAuthority == ha) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that do drive through testing
    public List<CollectionCentre> filterDriveThrough(Boolean b) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.driveThrough == b) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that require appointments
    public List<CollectionCentre> filterAppointment(Boolean b) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.needAppointment == b) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that are open on weekends
    public List<CollectionCentre> filterWeekend(Boolean b) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.weekends == b) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that take all children aged 0-16
    public List<CollectionCentre> filterChildren(Boolean b) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.children == b) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that need referral
    public List<CollectionCentre> filterReferral(Boolean b) {
        List<CollectionCentre> result = new LinkedList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.referral == b) {
                result.add(c);
            }
        }
        return result;
    }

}
