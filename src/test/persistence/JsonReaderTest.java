package persistence;

import model.CollectionCentre;
import model.FavouritesList;
import model.HealthAuthority;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonReaderTest class in JsonSterilizationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FavouritesList fl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFavouritesList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFavouritesList.json");
        try {
            FavouritesList fl = reader.read();
            assertEquals("Favourites List", fl.getName());
            assertEquals(0, fl.getCentres().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFavouritesList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFavouritesList.json");
        try {
            FavouritesList fl = reader.read();
            assertEquals("Favourites List", fl.getName());
            List<CollectionCentre> centres = fl.getCentres();
            assertEquals(2, centres.size());
            checkCollectionCentre("South Delta", "4470 Clarence Taylor Crescent", "Delta",
                    "604-952-3851", HealthAuthority.FRASER, false, true, false,
                    true, false, centres.get(0));
            checkCollectionCentre("Tri-Cities COVID-19 Test Collection Centre", "2796 Aberdeen Avenue",
                    "Coquitlam", "unavailable", HealthAuthority.FRASER, false, true,
                    false, false, false, centres.get(1));
        } catch (IOException e) {
            fail("Couldn't read file");
        }
    }
}
