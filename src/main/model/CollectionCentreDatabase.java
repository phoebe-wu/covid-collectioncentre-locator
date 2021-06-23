package model;

import java.util.LinkedList;
import java.util.List;

//Represents a database of all Collection Centres in British Columbia
public class CollectionCentreDatabase {
    List<CollectionCentre> centres;

    // EFFECTS: constructs an empty list of CollectionCentres
    public CollectionCentreDatabase() {
        centres = new LinkedList<>();
    }

    // getter
    public List<CollectionCentre> getCentres() {
        return centres;
    }

    // MODIFIES: this
    // EFFECTS: adds a CollectionCentre to CollectionCentreDatabase
    public void addCollectionCentre(CollectionCentre c) {
        centres.add(c);
    }

    // EFFECTS: returns the size of CollectionCentreDatabase
    public int size() {
        return centres.size();
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers in that city
    public CollectionCentreDatabase filterCity(String city) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.city.replaceAll("\\s+", "").equalsIgnoreCase(city.replaceAll(
                    "\\s+", ""))) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers from that health authority
    public CollectionCentreDatabase filterHealthAuthority(HealthAuthority ha) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.healthAuthority.equals(ha)) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers that do drive through testing
    public CollectionCentreDatabase filterDriveThrough(Boolean b) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.driveThrough == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers that require appointments
    public CollectionCentreDatabase filterAppointment(Boolean b) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.needAppointment == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers that are open on weekends
    public CollectionCentreDatabase filterWeekend(Boolean b) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.weekends == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers that take all children aged 0-16
    public CollectionCentreDatabase filterChildren(Boolean b) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.children == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }

    // REQUIRES: CollectionCentreDatabase is not empty
    // EFFECTS: returns a CollectionCentreDatabase of collection centers that need referral
    public CollectionCentreDatabase filterReferral(Boolean b) {
        CollectionCentreDatabase result = new CollectionCentreDatabase();

        for (CollectionCentre c : centres) {
            if (c.referral == b) {
                result.addCollectionCentre(c);
            }
        }
        return result;
    }


}
