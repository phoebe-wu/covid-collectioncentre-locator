package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class CollectionCentreHubTest {
    CollectionCentreHub testHub;
    CollectionCentre c1;
    CollectionCentre c2;
    CollectionCentre c3;
    CollectionCentre c4;
    CollectionCentre c5;
    CollectionCentre c6;
    CollectionCentre c7;
    CollectionCentre c8;

    @BeforeEach
    void setUp() {
        testHub = new CollectionCentreHub();

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
        c8 = new CollectionCentre("Atlin Health Centre", "164 Third St", "Atlin",
                "1-844-645-7811", HealthAuthority.NORTHERN, true, false, false,
                true, true);

        testHub.addCollectionCentre(c1);
        testHub.addCollectionCentre(c2);
        testHub.addCollectionCentre(c3);
        testHub.addCollectionCentre(c4);
        testHub.addCollectionCentre(c5);
        testHub.addCollectionCentre(c6);
        testHub.addCollectionCentre(c7);

    }

    @Test
    void testConstructor() {
        assertEquals(7, testHub.hubSize());
    }

    @Test
    void testCentreDatabaseGetter() {
        testHub.getCentres();
        assertEquals(7, testHub.centres.size());
    }

    @Test
    void testAddCollectionCentre() {
        testHub.addCollectionCentre(c8);
        assertEquals(8, testHub.hubSize());
    }

    @Test
    void testFilterCityHub() {
        CollectionCentreHub van = testHub.filterCityHub("Vancouver");
        assertEquals(3, van.hubSize());
    }

    @Test
    void testFilterCityList() {
        List<CollectionCentre> van = testHub.filterCityList("Vancouver");
        assertEquals(3, van.size());
    }

    @Test
    void testFilterCityHubTwoWordName() {
        CollectionCentreHub mr = testHub.filterCityHub("Maple Ridge");
        assertEquals(1, mr.hubSize());
    }

    @Test
    void testFilterCityListTwoWordName() {
        List<CollectionCentre> mr = testHub.filterCityList("Maple Ridge");
        assertEquals(1, mr.size());
    }

    @Test
    void testFilterCityListTwoWordNoSpace() {
        List<CollectionCentre> mr = testHub.filterCityList("MapleRidge");
        assertEquals(1, mr.size());
    }

    @Test
    void testFilterHealthAuthorityHub() {
        CollectionCentreHub island = testHub.filterHealthAuthorityHub(HealthAuthority.ISLAND);
        assertEquals(2, island.hubSize());
    }

    @Test
    void testFilterHealthAuthorityList() {
        List<CollectionCentre> island = testHub.filterHealthAuthorityList(HealthAuthority.ISLAND);
        assertEquals(2, island.size());
    }

    @Test
    void testFilterDriveThroughTrue() {
        CollectionCentreHub yesDriveThrough = testHub.filterDriveThrough(true);
        assertEquals(1, yesDriveThrough.hubSize());
    }

    @Test
    void testFilterDriveThroughFalse() {
        CollectionCentreHub noDriveThrough = testHub.filterDriveThrough(false);
        assertEquals(6, noDriveThrough.hubSize());
    }

    @Test
    void testFilterAppointmentTrue() {
        CollectionCentreHub yesAppointment = testHub.filterAppointment(true);
        assertEquals(4, yesAppointment.hubSize());
    }

    @Test
    void testFilterAppointmentFalse() {
        CollectionCentreHub noAppointment = testHub.filterAppointment(false);
        assertEquals(3, noAppointment.hubSize());
    }

    @Test
    void testFilterWeekendTrue() {
        CollectionCentreHub yesWeekend = testHub.filterWeekend(true);
        assertEquals(4, yesWeekend.hubSize());
    }

    @Test
    void testFilterWeekendFalse() {
        CollectionCentreHub noWeekend = testHub.filterWeekend(false);
        assertEquals(3, noWeekend.hubSize());
    }

    @Test
    void testFilterChildrenTrue() {
        CollectionCentreHub yesChildren = testHub.filterChildren(true);
        assertEquals(6, yesChildren.hubSize());
    }

    @Test
    void testFilterChildrenFalse() {
        CollectionCentreHub noChildren = testHub.filterChildren(false);
        assertEquals(1, noChildren.hubSize());
    }

    @Test
    void testFilterReferralTrue() {
        CollectionCentreHub yesReferral = testHub.filterReferral(true);
        assertEquals(0, yesReferral.hubSize());
    }

    @Test
    void testFilterReferralFalse() {
        CollectionCentreHub noReferral = testHub.filterReferral(false);
        assertEquals(7, noReferral.hubSize());
    }

}
