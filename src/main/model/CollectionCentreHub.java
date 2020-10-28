package model;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Represents a database of all Collection Centres in British Columbia
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
    // EFFECTS: returns a CollectionCentreHub of collection centers in that city for further filtering
    public CollectionCentreHub filterCityHub(String city) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.city.replaceAll("\\s+", "").equalsIgnoreCase(city.replaceAll(
                    "\\s+", ""))) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers in that city
    public List<CollectionCentre> filterCityList(String city) {
        List<CollectionCentre> result = new ArrayList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.city.replaceAll("\\s+", "").equalsIgnoreCase(city.replaceAll(
                    "\\s+", ""))) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a CollectionCentreHub of collection centers from that health authority for further filtering
    public CollectionCentreHub filterHealthAuthorityHub(HealthAuthority ha) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.healthAuthority.equals(ha)) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers from that health authority
    public List<CollectionCentre> filterHealthAuthorityList(HealthAuthority ha) {
        List<CollectionCentre> result = new ArrayList<>();

        for (CollectionCentre c : collectionCentreHub) {
            if (c.healthAuthority.equals(ha)) {
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
