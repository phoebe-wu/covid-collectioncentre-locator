package persistence;

import model.CollectionCentre;
import model.FavouritesList;
import model.HealthAuthority;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads favourites list from JSON data stored in file
// Adapted from JsonReader class from JsonSterilizationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads favourite list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FavouritesList read() throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavouritesList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses favourites list from JSON object and returns it
    private FavouritesList parseFavouritesList(JSONObject jsonObject) {
        FavouritesList myList = new FavouritesList("Favourites List");
        addCollectionCentres(myList, jsonObject);
        return myList;
    }

    // MODIFIES: this
    // EFFECTS: parses collection centres from JSON object and adds them to favourites list
    private void addCollectionCentres(FavouritesList myList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("centres");
        for (Object json : jsonArray) {
            JSONObject nextCC = (JSONObject) json;
            addCollectionCentre(myList, nextCC);
        }
    }

    // MODIFIES: this
    // EFFECTS: parses collection centre from JSON object and adds it to favourites list
    private void addCollectionCentre(FavouritesList myList, JSONObject nextCC) {
        String name = nextCC.getString("name");
        String address = nextCC.getString("address");
        String city = nextCC.getString("city");
        String phone = nextCC.getString("phone");
        HealthAuthority ha = HealthAuthority.valueOf(nextCC.getString("ha"));
        boolean a = nextCC.getBoolean("a");
        boolean w = nextCC.getBoolean("w");
        boolean dt = nextCC.getBoolean("dt");
        boolean c = nextCC.getBoolean("c");
        boolean r = nextCC.getBoolean("r");
        CollectionCentre cc = new CollectionCentre(name, address, city, phone, ha, a, w, dt, c, r);
        myList.addCollectionCentre(cc);
    }


}
