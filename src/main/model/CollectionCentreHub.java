package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Represents a database of all Collection Centres in British Columbia
public class CollectionCentreHub {
    List<CollectionCentre> centres;

    // EFFECTS: constructs an empty list of CollectionCentres
    public CollectionCentreHub() {
        centres = new LinkedList<>();
    }

    // getter
    public List<CollectionCentre> getCentres() {
        return centres;
    }

    // EFFECTS: adds a CollectionCentre to CollectionCentreHub
    public void addCollectionCentre(CollectionCentre c) {
        centres.add(c);
    }

    // EFFECTS: returns the size of CollectionCentreHub
    public int hubSize() {
        return centres.size();
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a CollectionCentreHub of collection centers in that city for further filtering
    public CollectionCentreHub filterCityHub(String city) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
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

        for (CollectionCentre c : centres) {
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

        for (CollectionCentre c : centres) {
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

        for (CollectionCentre c : centres) {
            if (c.healthAuthority.equals(ha)) {
                result.add(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that do drive through testing
    public CollectionCentreHub filterDriveThrough(Boolean b) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
            if (c.driveThrough == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that require appointments
    public CollectionCentreHub filterAppointment(Boolean b) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
            if (c.needAppointment == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that are open on weekends
    public CollectionCentreHub filterWeekend(Boolean b) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
            if (c.weekends == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that take all children aged 0-16
    public CollectionCentreHub filterChildren(Boolean b) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
            if (c.children == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreHub is not empty
    // EFFECTS: returns a list of collection centers that need referral
    public CollectionCentreHub filterReferral(Boolean b) {
        CollectionCentreHub result = new CollectionCentreHub();

        for (CollectionCentre c : centres) {
            if (c.referral == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }


}
