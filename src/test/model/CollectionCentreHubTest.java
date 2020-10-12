package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void setUp(){
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
    void testAddCollectionCentre() {
        testHub.addCollectionCentre(c8);
        assertEquals(8, testHub.hubSize());
    }

    @Test
    void testFilterCity() {
        List<CollectionCentre> van = testHub.filterCity("Vancouver");
        assertEquals(3, van.size());
    }

    @Test
    void testFilterHealthAuthority(){
        List<CollectionCentre> island = testHub.filterHealthAuthority(HealthAuthority.ISLAND);
        assertEquals(2, island.size());
    }

    @Test
    void filterDriveThroughTrue() {
        List<CollectionCentre> yesDriveThrough = testHub.filterDriveThrough(true);
        assertEquals(1, yesDriveThrough.size());
    }

    @Test
    void filterDriveThroughFalse() {
        List<CollectionCentre> noDriveThrough = testHub.filterDriveThrough(false);
        assertEquals(6, noDriveThrough.size());
    }

    @Test
    void filterAppointmentTrue() {
        List<CollectionCentre> yesAppointment = testHub.filterAppointment(true);
        assertEquals(4, yesAppointment.size());
    }

    @Test
    void filterAppointmentFalse() {
        List<CollectionCentre> noAppointment = testHub.filterAppointment(false);
        assertEquals(3, noAppointment.size());
    }

    @Test
    void filterWeekendTrue() {
        List<CollectionCentre> yesWeekend = testHub.filterWeekend(true);
        assertEquals(4, yesWeekend.size());
    }

    @Test
    void filterWeekendFalse() {
        List<CollectionCentre> noWeekend = testHub.filterWeekend(false);
        assertEquals(3, noWeekend.size());
    }

    @Test
    void filterChildrenTrue() {
        List<CollectionCentre> yesChildren = testHub.filterChildren(true);
        assertEquals(6, yesChildren.size());
    }

    @Test
    void filterChildrenFalse() {
        List<CollectionCentre> noChildren = testHub.filterChildren(false);
        assertEquals(1, noChildren.size());
    }

    @Test
    void filterReferralTrue() {
        List<CollectionCentre> yesReferral = testHub.filterReferral(true);
        assertEquals(0, yesReferral.size());
    }

    @Test
    void filterReferralFalse() {
        List<CollectionCentre> noReferral = testHub.filterReferral(false);
        assertEquals(7, noReferral.size());
    }

}
