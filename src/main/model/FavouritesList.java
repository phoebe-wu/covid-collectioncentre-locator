package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of the users favourited collection centres that can be referenced later
// Data persistence methods adapted from WorkRoom class in JsonSterilizationDemo
public class FavouritesList implements Writable {
    public String name;
    private List<CollectionCentre> centres;

    // EFFECTS: constructs a Favourites List with given name and an empty list of collection centres
    public FavouritesList(String name) {
        this.name = name;
        centres = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    // EFFECTS: returns an unmodifiable list of centres in this favourites list
    public List<CollectionCentre> getCentres() {
        return Collections.unmodifiableList(centres);
    }

    // REQUIRES: c is not already within centres
    // MODIFIES: this
    // EFFECTS: adds collection centre to list
    public void addCollectionCentre(CollectionCentre c) {
        if (!centres.contains(c)) {
            centres.add(c);
        }
    }

    // REQUIRES: c is within centers
    // MODIFIES: this
    // EFFECTS: removes collection center from list
    public void removeCollectionCenter(CollectionCentre c) {
        if (centres.contains(c)) {
            centres.remove(c);
        }
    }

    // EFFECTS: produces a message indicating size of list
    //          Empty: "There are no collection centres in your list"
    //          1: "There is 1 collection centre in your list"
    //          >1: "There are x collection centres in your list"
    public String sizeMessage() {
        if (centres.isEmpty()) {
            return "There are no collection centres in your list.";
        } else {
            if (centres.size() == 1) {
                return "There is 1 collection centre in your list.";
            } else {
                return "There are " + centres.size() + " collection centres in your list.";
            }
        }
    }

    // EFFECTS: returns the size of the favourites list
    public int length() {
        return centres.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("centres", collectionCentresToJson());
        return json;
    }

    // EFFECTS: returns collection centres in this favourites list as a JSON array
    private JSONArray collectionCentresToJson() {
        JSONArray jsonArray = new JSONArray();
        for (CollectionCentre c : centres) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }


}
