package model;

import java.util.List;

public class FavouritesList {

    public String name;
    private List<CollectionCentre> centers;

    public FavouritesList(String name) {
        //stub
    }

    // REQUIRES: c is not already within centers
    // MODIFIES: this
    // EFFECTS: adds collection center to list
    public void addCollectionCenter(CollectionCentre c) {
        // stub
    }

    // REQUIRES: c is within centers
    // MODIFIES: this
    // EFFECTS: removes collection center from list
    public void removeCollectionCenter(CollectionCentre c) {
        //stub
    }

    // EFFECTS: produces a message indicating size of list
    public String sizeMessage() {
        return null; //stub
    }


}
