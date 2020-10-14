package model;

import java.util.ArrayList;
import java.util.List;

public class FavouritesList {

    public String name;
    private List<CollectionCentre> centres;

    public FavouritesList(String name) {
        this.name = name;
        centres = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public List<CollectionCentre> getCentres() {
        return centres;
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


}
