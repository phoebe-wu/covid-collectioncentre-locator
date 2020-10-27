package persistence;

import model.CollectionCentre;
import model.FavouritesList;
import model.HealthAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Adapted from JsonWriterClass in JsonSterilizationDemo
public class JsonWriterTest extends JsonTest {
    FavouritesList faves;

    @BeforeEach
    void setUp() {
        faves = new FavouritesList("Favourites List");
    }

    @Test
    void testWriterInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail("IOException expected");
        } catch (FileNotFoundException e) {
            // Expected
        }
    }

    @Test
    void testWriterEmptyFavouritesList() {
    JsonWriter writer = new JsonWriter("./data/testWriterEmptyFavouritesList.json");
        try {
            writer.open();
            writer.write(faves);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFavouritesList.json");
            assertEquals("Favourites List", faves.getName());
            assertEquals(0,faves.getCentres().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFavouritesList() {
        try {
            faves.addCollectionCentre(new CollectionCentre("REACH UPCC", "1145 Commercial Drive",
                    "Vancouver", "604-216-3138", HealthAuthority.COASTAL, true, true,
                    false, true, false));
            faves.addCollectionCentre(new CollectionCentre("Pemberton Health Centre",
                    "1403 Pemberton Portafe Road", "Pemberton", "604-894-6633 or 604-894-6939",
                    HealthAuthority.COASTAL, false, false, false, true,
                    false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFavouritesList.json");
            writer.open();
            writer.write(faves);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFavouritesList.json");
            faves = reader.read();
            assertEquals("Favourites List", faves.getName());
            List<CollectionCentre> centres = faves.getCentres();
            assertEquals(2, faves.getCentres().size());
            checkCollectionCentre("REACH UPCC", "1145 Commercial Drive",
                    "Vancouver", "604-216-3138", HealthAuthority.COASTAL, true, true,
                    false, true, false, centres.get(0));
            checkCollectionCentre("Pemberton Health Centre", "1403 Pemberton Portafe Road",
                    "Pemberton", "604-894-6633 or 604-894-6939", HealthAuthority.COASTAL, false,
                    false, false, true, false, centres.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
