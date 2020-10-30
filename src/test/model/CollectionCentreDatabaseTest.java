package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CollectionCentreDatabaseTest {
    CollectionCentreDatabase database;
    CollectionCentre c1;
    CollectionCentre c2;
    CollectionCentre c3;
    CollectionCentre c4;
    CollectionCentre c5;
    CollectionCentre c6;
    CollectionCentre c7;
    CollectionCentre c8;
    CollectionCentre c9;
    CollectionCentre c10;

    @BeforeEach
    void setUp() {
        database = new CollectionCentreDatabase();

        c1 = new CollectionCentre("Oceanside Health Centre", "489 Alberni Hwy", "Parksville",
                "1-8448901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c2 = new CollectionCentre("Peninsula Health Unit", "2170 Mt.Newton X Rd", "Saanichton",
                "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c3 = new CollectionCentre("BC Children & Women Campus", "4500 Oak Street", "Vancouver",
                "604-875-2154", HealthAuthority.PROVINCIAL,
                true, true, false, true, false);
        c4 = new CollectionCentre("Vancouver Community College", "North Parking Lot #865",
                "Vancouver", null, HealthAuthority.COASTAL, false, true,
                true, true, false);
        c5 = new CollectionCentre("Ridge Meadows", "153-11762 Laity Street", "Maple Ridge",
                null, HealthAuthority.FRASER, false, false, false,
                true, false);
        c6 = new CollectionCentre("Fort St.John Health Unit", "10116 110 Ave", "Fort St.John",
                "1-844-645-7811", HealthAuthority.INTERIOR, true, false, false,
                true, false);
        c7 = new CollectionCentre("DTES COVID-19 Testing Site", "429 Alexander Street", "Vancouver",
                "778-886-4081", HealthAuthority.COASTAL, false, false, false,
                false, false);
        c8 = new CollectionCentre("City Centre UPCC", "1290 Hornby Street",
                "Vancouver", "unavailable", HealthAuthority.COASTAL, false, true,
                false, true, false);
        c9 = new CollectionCentre("St. Vincent", "4875 Heather Street",
                "Vancouver", "unavailable", HealthAuthority.COASTAL, false, true,
                true, false, false);
        c10 = new CollectionCentre("Atlin Health Centre", "164 Third St", "Atlin",
                "1-844-645-7811", HealthAuthority.NORTHERN, true, false, false,
                true, true);

        database.addCollectionCentre(c1);
        database.addCollectionCentre(c2);
        database.addCollectionCentre(c3);
        database.addCollectionCentre(c4);
        database.addCollectionCentre(c5);
        database.addCollectionCentre(c6);
        database.addCollectionCentre(c7);
        database.addCollectionCentre(c8);
        database.addCollectionCentre(c9);

    }

    @Test
    void testConstructor() { assertEquals(9, database.size()); }

    @Test
    void testCentreDatabaseGetter() {
        assertEquals(9, database.getCentres().size());
    }

    @Test
    void testAddCollectionCentre() {
        database.addCollectionCentre(c10);
        assertEquals(10, database.size());
    }

    @Test
    void testFilterCityHub() {
        CollectionCentreDatabase van = database.filterCity("Vancouver");
        assertEquals(5, van.size());
    }

    @Test
    void testFilterCityHubTwoWordName() {
        CollectionCentreDatabase mr = database.filterCity("Maple Ridge");
        assertEquals(1, mr.size());
    }


    @Test
    void testFilterHealthAuthorityHub() {
        CollectionCentreDatabase island = database.filterHealthAuthority(HealthAuthority.ISLAND);
        assertEquals(2, island.size());
    }


    @Test
    void testFilterDriveThroughTrue() {
        CollectionCentreDatabase yesDriveThrough = database.filterDriveThrough(true);
        assertEquals(2, yesDriveThrough.size());
    }

    @Test
    void testFilterDriveThroughFalse() {
        CollectionCentreDatabase noDriveThrough = database.filterDriveThrough(false);
        assertEquals(7, noDriveThrough.size());
    }

    @Test
    void testFilterAppointmentTrue() {
        CollectionCentreDatabase yesAppointment = database.filterAppointment(true);
        assertEquals(4, yesAppointment.size());
    }

    @Test
    void testFilterAppointmentFalse() {
        CollectionCentreDatabase noAppointment = database.filterAppointment(false);
        assertEquals(5, noAppointment.size());
    }

    @Test
    void testFilterWeekendTrue() {
        CollectionCentreDatabase yesWeekend = database.filterWeekend(true);
        assertEquals(6, yesWeekend.size());
    }

    @Test
    void testFilterWeekendFalse() {
        CollectionCentreDatabase noWeekend = database.filterWeekend(false);
        assertEquals(3, noWeekend.size());
    }

    @Test
    void testFilterChildrenTrue() {
        CollectionCentreDatabase yesChildren = database.filterChildren(true);
        assertEquals(7, yesChildren.size());
    }

    @Test
    void testFilterChildrenFalse() {
        CollectionCentreDatabase noChildren = database.filterChildren(false);
        assertEquals(2, noChildren.size());
    }

    @Test
    void testFilterReferralTrue() {
        CollectionCentreDatabase yesReferral = database.filterReferral(true);
        assertEquals(0, yesReferral.size());
    }

    @Test
    void testFilterReferralFalse() {
        CollectionCentreDatabase noReferral = database.filterReferral(false);
        assertEquals(9, noReferral.size());
    }

    @Test
    void testMultipleFilter() {
        CollectionCentreDatabase filtered = database.filterCity("Vancouver");
        assertEquals(5, filtered.centres.size());

        filtered = filtered.filterAppointment(false);
        assertEquals(4, filtered.centres.size());

        filtered = filtered.filterDriveThrough(true);
        assertEquals(2, filtered.centres.size());
    }

}
