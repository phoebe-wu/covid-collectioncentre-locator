package persistence;

import model.CollectionCentre;
import model.HealthAuthority;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonTest class in JsonSterilizationDemo
public class JsonTest {
    protected void checkCollectionCentre(String name, String address, String city, String phone, HealthAuthority ha,
                                         boolean appointment, boolean weekends, boolean driveThrough, boolean children,
                                         boolean referral, CollectionCentre collectionCentre) {
        assertEquals(name, collectionCentre.getName());
        assertEquals(address, collectionCentre.getAddress());
        assertEquals(city, collectionCentre.getCity());
        assertEquals(phone, collectionCentre.getPhone());
        assertEquals(ha, collectionCentre.getHealthAuthority());
        assertEquals(appointment, collectionCentre.isNeedAppointment());
        assertEquals(weekends, collectionCentre.isWeekends());
        assertEquals(driveThrough, collectionCentre.isDriveThrough());
        assertEquals(children, collectionCentre.isChildren());
        assertEquals(referral, collectionCentre.isReferral());
    }
}
