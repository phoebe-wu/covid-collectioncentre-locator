package model;

import exceptions.CentreAlreadyAddedException;
import exceptions.CentreDoesNotExistException;
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
        favouritesList = new FavouritesList("My Favourites List");
        c1 = new CollectionCentre("Oceanside Health Centre", "489 Alberni Hwy", "Parksville",
                "1-8448901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c2 = new CollectionCentre("Peninsula Health Unit", "2170 Mt.Newton X Rd", "Saanichton",
                "1-844-901-8442", HealthAuthority.ISLAND,
                true, true, false, true, false);
        c3 = new CollectionCentre("BC Children & Women Campus", "4500 Oak Street", "Vancouver",
                "604-875-2154", HealthAuthority.PROVINCIAL,
                true, true, false, true, false);

        favouritesList.getCentres().add(c1);
    }

    @Test
    void testGetters() {
        assertEquals("My Favourites List", favouritesList.getName());
        assertEquals(1, favouritesList.getCentres().size());
    }

    @Test
    void testAddCollectionCentreNoException() {
        try {
            favouritesList.addCollectionCentre(c2);
        } catch (CentreAlreadyAddedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals(2, favouritesList.length());
    }

    @Test
    void testAddCollectionCentreException() {
        try {
            favouritesList.addCollectionCentre(c1);
            fail("Exception should have been thrown");
        } catch (CentreAlreadyAddedException e) {
            // pass
        }
        assertEquals(1, favouritesList.length());
    }

    @Test
    void testRemoveCollectionCentreException() {
        try {
            favouritesList.removeCollectionCentre(c2);
            fail("Exception should have been thrown");
        } catch (CentreDoesNotExistException e) {
            // pass
        }
        assertEquals(1, favouritesList.length());
    }

    @Test
    void testRemoveCollectionCentreNoException() {
        try {
            favouritesList.removeCollectionCentre(c1);
        } catch (CentreDoesNotExistException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testSizeMessageEmpty() {
        try {
            favouritesList.removeCollectionCentre(c1);
        } catch (CentreDoesNotExistException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("There are no collection centres in your favourites list.", favouritesList.sizeMessage());
    }

    @Test
    void testSizeMessageOne() {
        assertEquals("There is 1 collection centre in your favourites list.", favouritesList.sizeMessage());
    }

    @Test
    void testSizeMessageMany() {
        try {
            favouritesList.addCollectionCentre(c2);
        } catch (CentreAlreadyAddedException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("There are 2 collection centres in your favourites list.", favouritesList.sizeMessage());
    }

    @Test
    void testLength() {
        assertEquals(1, favouritesList.length());
    }

}
