package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionCentreTest {
    CollectionCentre c1;
    CollectionCentre c2;
    CollectionCentre c3;

    @BeforeEach
    void setUp(){
        c1 = new CollectionCentre("Oceanside Health Centre", "489 Alberni Hwy", "Parksville",
                "1-8448901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c2 = new CollectionCentre("Peninsula Health Unit", "2170 Mt.Newton X Rd", "Saanichton",
                "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c3 = new CollectionCentre("BC Children & Women Campus", "4500 Oak Street", "Vancouver",
                "604-875-2154", HealthAuthority.PROVINCIAL,
                true, true, false, true, false);
    }

    @Test
    void testConstructor() {
        assertEquals("Oceanside Health Centre", c1.getName());
        assertEquals("4500 Oak Street", c3.getAddress());
        assertEquals("Saanichton", c2.getCity());
    }
}