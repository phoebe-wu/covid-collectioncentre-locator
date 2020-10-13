package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FavouritesListTest {
    FavouritesList favouritesList;
    CollectionCentre c1;
    CollectionCentre c2;
    CollectionCentre c3;

    @BeforeEach
    void setUp() {
        favouritesList = new FavouritesList("Will Visit");
        c1 = new CollectionCentre("Oceanside Health Centre", "489 Alberni Hwy", "Parksville",
                "1-8448901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c2 = new CollectionCentre("Peninsula Health Unit", "2170 Mt.Newton X Rd", "Saanichton",
                "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c3 = new CollectionCentre("BC Children & Women Campus", "4500 Oak Street", "Vancouver",
                "604-875-2154", HealthAuthority.PROVINCIAL,
                true, true, false, true, false);

        favouritesList.addCollectionCentre(c1);
    }

    @Test
    void testGetters() {
        assertEquals("Will Visit", favouritesList.getName());
        assertEquals(1, favouritesList.getCentres().size());
    }

    @Test
    void testAddCollectionCentre() {
        favouritesList.addCollectionCentre(c2);
        assertEquals(2, favouritesList.length());
    }

    @Test
    void testAddCollectionCentreAlreadyExists() {
        favouritesList.addCollectionCentre(c1);
        assertEquals(1, favouritesList.length());
    }

    @Test
    void testRemoveCollectionCentre() {
        favouritesList.removeCollectionCenter(c2);
        assertEquals(1, favouritesList.length());
    }

    @Test
    void testSizeMessageEmpty() {
        favouritesList.removeCollectionCenter(c1);
        assertEquals("There are no collection centres in your list", favouritesList.sizeMessage());
    }

    @Test
    void testSizeMessageOne() {
        assertEquals("There is 1 collection centre in your list", favouritesList.sizeMessage());
    }

    @Test
    void testSizeMessageMany() {
        favouritesList.addCollectionCentre(c2);
        assertEquals("There are 2 collection centres in your list", favouritesList.sizeMessage());
    }

    @Test
    void testLength() {
        assertEquals(1, favouritesList.length());
    }

}
