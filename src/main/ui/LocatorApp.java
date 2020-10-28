package ui;

import model.CollectionCentre;
import model.CollectionCentreHub;
import model.FavouritesList;
import model.HealthAuthority;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.Main.initializeAllCollectionCentres;

// Adapted from TellerApp and FitGymLife
// Data persistence methods adapted from JsonSterilizationDemo
// Represents a Covid-19 collection centre locator application
public class LocatorApp {
    private static final String JSON_STORE = "./data/FavouritesList.json";
    private Scanner input;
    private CollectionCentreHub database;
    private CollectionCentreHub secondaryDatabase;
    private List<CollectionCentre> result;
    private FavouritesList favList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the locator application
    public LocatorApp() throws FileNotFoundException {
        runLocatorApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLocatorApp() {
        boolean runProgram = true;
        String command = null;

        initializeApp();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (runProgram) {
            sortingMenu();
            command = input.next();

            if (command.equals("q")) {
                runProgram = false;
            } else if (command.equals("v")) {
                System.out.println(favList.sizeMessage());
                System.out.println(cleanResults(favList.getCentres()));
            } else {
                processSortCommand(command);
            }

        }
        System.out.println("Goodbye! Stay Safe! Wear a Mask!");
    }

    // MODIFIES: this
    // EFFECTS: initializes all collection centres into database
    private void initializeApp() {
        database = new CollectionCentreHub();
        initializeAllCollectionCentres(database);
        input = new Scanner(System.in);
        result = new ArrayList<>();
        favList = new FavouritesList("Favourites List");
    }


    // EFFECTS: displays menu of sorting options to user
    private void sortingMenu() {
        System.out.println("\nHow would you like to sort collection centres by?");
        System.out.println("\tc -> City");
        System.out.println("\th -> Health Authority");
        System.out.println("\nFor your list of favourited collection centres:");
        System.out.println("\tv -> View Favourites List");
        System.out.println("\ts -> Save Favourites List to file");
        System.out.println("\tl -> Load Favourites List from file");
        System.out.println("\n Press 'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command to search
    private void processSortCommand(String command) {
        if (command.equals("c")) {
            doCityFilter();
            furtherFilterResultsMenu();
            command = input.next();
            processFilterCommand(command);
        } else if (command.equals("h")) {
            doHealthAuthorityFilter();
            furtherFilterResultsMenu();
            command = input.next();
            processFilterCommand(command);
        } else if (command.equals("s")) {
            saveFavouritesList();
        } else if (command.equals("l")) {
            loadFavouritesList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: secondaryDatabase, result
    // EFFECTS: generates collection centres to the ones located only in the entered city and stores in
    //          secondaryDatabase for if user wants to filter again after OR
    //          result for if user wants to view results right after
    private void doCityFilter() {
        System.out.println("Enter city you want to search for collection centres in");
        String city = input.next();
        secondaryDatabase = database.filterCityHub(city.replaceAll("\\s+", ""));
        result = database.filterCityList(city.replaceAll("\\s+", ""));
    }

    // MODIFIES: secondaryDatabase, result
    // EFFECTS: generates collection centres to the ones located only in the entered health authority and stores in
    //          secondaryDatabase for if user wants to filter again after OR
    //          result for if user wants to view results right after
    private void doHealthAuthorityFilter() {
        System.out.println("Enter the health authority you want to search for collection centres in");
        String ha = input.next();
        if (ha.equalsIgnoreCase("island")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.ISLAND);
            result = database.filterHealthAuthorityList(HealthAuthority.ISLAND);
        } else if (ha.equalsIgnoreCase("coastal")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.COASTAL);
            result = database.filterHealthAuthorityList(HealthAuthority.COASTAL);
        } else if (ha.equalsIgnoreCase("provincial")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.PROVINCIAL);
            result = database.filterHealthAuthorityList(HealthAuthority.PROVINCIAL);
        } else if (ha.equalsIgnoreCase("northern")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.NORTHERN);
            result = database.filterHealthAuthorityList(HealthAuthority.NORTHERN);
        } else if (ha.equalsIgnoreCase("fraser")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.FRASER);
            result = database.filterHealthAuthorityList(HealthAuthority.FRASER);
        } else if (ha.equalsIgnoreCase("interior")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.INTERIOR);
            result = database.filterHealthAuthorityList(HealthAuthority.INTERIOR);
        } else {
            System.out.println("Please enter 'island', 'coastal', 'provincial', 'northern', 'fraser', or 'interior'");
        }
    }


    // EFFECTS: displays filter results menu to user to filter location results by appointment, referral, children,
    //          open on weekends, or drive-through
    private void furtherFilterResultsMenu() {
        System.out.println("Would you like to further filter your results?");
        System.out.println("\tyes -> To further filter results");
        System.out.println("\tno -> To view your results");
    }

    // EFFECTS: processes user command if they want to further filter their results
    private void processFilterCommand(String command) {
        if (command.equals("no")) {
            criteriaMessage();
            System.out.println(cleanResults(result));
            addToFavouritesMenu();
            command = input.next();
            processFavouritesCommand(command);
        } else if (command.equals("yes")) {
            furtherFilterCriteriaMenu();
            command = input.next();
            processFurtherFilterCriteriaCommand(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays menu of filtering options to user
    private void furtherFilterCriteriaMenu() {
        System.out.println("How would you like to further filter your results?");
        System.out.println("\ta -> Appointment Requirement");
        System.out.println("\tr -> Referral Requirement");
        System.out.println("\tc -> Takes all children between the ages of 0-16");
        System.out.println("\td -> Offers Drive-through testing");
        System.out.println("\tw -> Open on at least one day of the weekend");
    }

    // EFFECTS: processes further filtering user command
    private void processFurtherFilterCriteriaCommand(String command) {
        if (command.equals("a")) {
            booleanFilterMenu();
            command = input.next();
            doAppointmentFilter(command);
        } else if (command.equals("r")) {
            booleanFilterMenu();
            command = input.next();
            doReferralFilter(command);
        } else if (command.equals("c")) {
            booleanFilterMenu();
            command = input.next();
            doChildrenFilter(command);
        } else if (command.equals("d")) {
            booleanFilterMenu();
            command = input.next();
            doDriveThroughFilter(command);
        } else if (command.equals("w")) {
            booleanFilterMenu();
            command = input.next();
            doWeekendFilter(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays true or false boolean filtering menu to user
    //          e.g. true -> needs appointment
    //          e.g. false -> doesn't need referral
    private void booleanFilterMenu() {
        System.out.println("true");
        System.out.println("false");
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that require or don't require appointments
    private void doAppointmentFilter(String command) {
        result = secondaryDatabase.filterAppointment(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that require or don't require referral
    private void doReferralFilter(String command) {
        result = secondaryDatabase.filterReferral(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that are or are not open on weekends
    private void doWeekendFilter(String command) {
        result = secondaryDatabase.filterWeekend(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that offer or don't offer drive-through testing
    private void doDriveThroughFilter(String command) {
        result = secondaryDatabase.filterDriveThrough(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that do or do not take all children between 0 and 17 y/o
    private void doChildrenFilter(String command) {
        result = secondaryDatabase.filterChildren(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }


    // EFFECTS: asks user if they would like add the list of filtered collection centres to their favourite list
    private void addToFavouritesMenu() {
        System.out.println("Would you like to add the results to your favourites list?");
        System.out.println("\tyes");
        System.out.println("\tno");
    }

    // EFFECTS: processes favourites user command
    private void processFavouritesCommand(String command) {
        if (command.equals("yes")) {
            for (CollectionCentre c : result) {
                favList.addCollectionCentre(c);
            }
            System.out.println("Successfully added to your favourites list!");
        } else if (command.equals("no")) {
            System.out.println("Results were not added");
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: returns a clean list of results including only a collection centre's name, address + city, and phone
    //          number if available
    private List<String> cleanResults(List<CollectionCentre> results) {
        List<String> clean = new ArrayList<>();

        for (CollectionCentre c : results) {
            clean.add(c.name + "   " + c.address + ", " + c.city + "  Phone: " + c.phone);
        }
        return clean;
    }

    // EFFECTS: returns a message indicating the number of collection centres that match the criteria
    //          Empty: "No collection centres match your criteria."
    //              1: "1 collection centre matches your criteria."
    //             >1: "x collection centres match your criteria."
    private void criteriaMessage() {
        if (result.isEmpty()) {
            System.out.println("No collection centres match your criteria.");
        } else {
            if (result.size() == 1) {
                System.out.println("1 collection centre matches your criteria.");
            } else {
                System.out.println(result.size() + " collection centres match your criteria.");
            }
        }
    }

    // EFFECTS: saves favourites list to file
    private void saveFavouritesList() {
        try {
            jsonWriter.open();
            jsonWriter.write(favList);
            jsonWriter.close();
            System.out.println("Saved " + favList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favourites list from file
    private void loadFavouritesList() {
        try {
            favList = jsonReader.read();
            System.out.println("Loaded " + favList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
