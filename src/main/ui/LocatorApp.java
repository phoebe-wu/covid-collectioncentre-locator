package ui;

import model.CollectionCentre;
import model.CollectionCentreDatabase;
import model.FavouritesList;
import model.HealthAuthority;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.Main.initializeAllCollectionCentres;

// Adapted from TellerApp and FitGymLife
// Data persistence methods adapted from JsonSterilizationDemo
// Represents a Covid-19 collection centre locator application
public class LocatorApp extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;
    private static final String JSON_STORE = "./data/FavouritesList.json";
    private Scanner input;
    private CollectionCentreDatabase database;
    private CollectionCentreDatabase filtered;
    private FavouritesList favList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the locator application
    public LocatorApp() throws FileNotFoundException {
        super("BC Covid-19 Collection Centre Locator");
        runLocatorApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLocatorApp() {
        boolean runProgram = true;
        String command = null;

        initializeApp();
        initializeGraphics();
        initializeInteraction();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        while (runProgram) {
            initialLocationSortingMenu();
            command = input.next();

            if (command.equals("q")) {
                runProgram = false;
            } else if (command.equals("v")) {
                System.out.println(favList.sizeMessage());
                System.out.println(cleanResults(favList.getCentres()));
            } else {
                processLocationSortCommand(command);
            }

        }
        System.out.println("Goodbye! Stay Safe! Wear a Mask!");
    }

    // MODIFIES: this
    // EFFECTS: initializes all collection centres into database
    private void initializeApp() {
        database = new CollectionCentreDatabase();
        initializeAllCollectionCentres(database);
        input = new Scanner(System.in);
        filtered = new CollectionCentreDatabase();
        favList = new FavouritesList("Favourites List");
    }

    // MODIFIES: this
    // EFFECTS: initializes a MouseListener to be used in the JFrame
    private void initializeInteraction() {
    }

    // MODIFIES: this
    // EFFECTS: initializes the JFrame window where the LocatorApp will operate, and populates the tools
    //          that will be used to search and filter collection centres
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: displays menu of sorting options to user
    private void initialLocationSortingMenu() {
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
    private void processLocationSortCommand(String command) {
        if (command.equalsIgnoreCase("c")) {
            doCityFilter();
            optionToFurtherFilterMenu();
            command = input.next();
            processOptionToFilterCommand(command);
        } else if (command.equalsIgnoreCase("h")) {
            doHealthAuthorityFilter();
            optionToFurtherFilterMenu();
            command = input.next();
            processOptionToFilterCommand(command);
        } else if (command.equalsIgnoreCase("s")) {
            saveFavouritesList();
        } else if (command.equalsIgnoreCase("l")) {
            loadFavouritesList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: filtered
    // EFFECTS: generates collection centres to the ones located only in the entered city
    private void doCityFilter() {
        System.out.println("Enter city you want to search for collection centres in");
        String city = input.next();
        filtered = database.filterCity(city);
    }

    // MODIFIES: filtered
    // EFFECTS: generates collection centres to the ones located only in the entered health authority and stores in
    private void doHealthAuthorityFilter() {
        System.out.println("Enter the health authority you want to search for collection centres in");
        String ha = input.next();
        if (ha.equalsIgnoreCase("island")) {
            filtered = database.filterHealthAuthority(HealthAuthority.ISLAND);
        } else if (ha.equalsIgnoreCase("coastal")) {
            filtered = database.filterHealthAuthority(HealthAuthority.COASTAL);
        } else if (ha.equalsIgnoreCase("provincial")) {
            filtered = database.filterHealthAuthority(HealthAuthority.PROVINCIAL);
        } else if (ha.equalsIgnoreCase("northern")) {
            filtered = database.filterHealthAuthority(HealthAuthority.NORTHERN);
        } else if (ha.equalsIgnoreCase("fraser")) {
            filtered = database.filterHealthAuthority(HealthAuthority.FRASER);
        } else if (ha.equalsIgnoreCase("interior")) {
            filtered = database.filterHealthAuthority(HealthAuthority.INTERIOR);
        } else {
            System.out.println("Please enter 'island', 'coastal', 'provincial', 'northern', 'fraser', or 'interior'");
        }
    }


    // EFFECTS: displays filter results menu to user to filter location results by appointment, referral, children,
    //          open on weekends, or drive-through
    private void optionToFurtherFilterMenu() {
        System.out.println("Would you like to further filter your results?");
        System.out.println("\tyes -> To further filter results");
        System.out.println("\tno -> To view your results");
    }

    // EFFECTS: processes user command if they want to further filter their results
    private void processOptionToFilterCommand(String command) {
        if (command.equalsIgnoreCase("no")) {
            criteriaMessage();
            System.out.println(cleanResults(filtered.getCentres()));
            addToFavouritesMenu();
            command = input.next();
            processFavouritesCommand(command);
        } else if (command.equalsIgnoreCase("yes")) {
            filterByCriteriaMenu();
            command = input.next();
            processFilterByCriteriaCommand(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays menu of filtering options to user
    private void filterByCriteriaMenu() {
        System.out.println("How would you like to further filter your results?");
        System.out.println("\ta -> Appointment Requirement");
        System.out.println("\tr -> Referral Requirement");
        System.out.println("\tc -> Takes all children between the ages of 0-16");
        System.out.println("\td -> Offers Drive-through testing");
        System.out.println("\tw -> Open on at least one day of the weekend");
    }

    // EFFECTS: processes further filtering user command
    private void processFilterByCriteriaCommand(String command) {
        if (command.equalsIgnoreCase("a")) {
            booleanFilterMenu();
            command = input.next();
            doAppointmentFilter(command);
        } else if (command.equalsIgnoreCase("r")) {
            booleanFilterMenu();
            command = input.next();
            doReferralFilter(command);
        } else if (command.equalsIgnoreCase("c")) {
            booleanFilterMenu();
            command = input.next();
            doChildrenFilter(command);
        } else if (command.equalsIgnoreCase("d")) {
            booleanFilterMenu();
            command = input.next();
            doDriveThroughFilter(command);
        } else if (command.equalsIgnoreCase("w")) {
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
        filtered = filtered.filterAppointment(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(filtered.getCentres()));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that require or don't require referral
    private void doReferralFilter(String command) {
        filtered = filtered.filterReferral(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(filtered.getCentres()));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that are or are not open on weekends
    private void doWeekendFilter(String command) {
        filtered = filtered.filterWeekend(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(filtered.getCentres()));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that offer or don't offer drive-through testing
    private void doDriveThroughFilter(String command) {
        filtered = filtered.filterDriveThrough(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(filtered.getCentres()));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to centres that do or do not take all children between 0 and 17 y/o
    private void doChildrenFilter(String command) {
        filtered = filtered.filterChildren(Boolean.parseBoolean(command));
        criteriaMessage();
        System.out.println(cleanResults(filtered.getCentres()));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }


    // EFFECTS: asks user if they would like add the list of filtered collection centres to their favourite list
    private void addToFavouritesMenu() {
        System.out.println("Would you like to:");
        System.out.println("\t f -> filter your results again");
        System.out.println("Add the results to your favourites list?");
        System.out.println("\tyes -> add your results to your favourites");
        System.out.println("\tno -> restart your search");
    }

    // EFFECTS: processes favourites user command
    private void processFavouritesCommand(String command) {
        if (command.equalsIgnoreCase("yes")) {
            for (CollectionCentre c : filtered.getCentres()) {
                favList.addCollectionCentre(c);
            }
            System.out.println("Successfully added centres to your favourites list!");
        } else if (command.equalsIgnoreCase("no")) {
            System.out.println("Results were not added");
        } else if (command.equalsIgnoreCase("f")) {
            filterByCriteriaMenu();
            command = input.next();
            processFilterByCriteriaCommand(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: returns a clean list of results including only a collection centre's name, address + city, and phone
    //          number
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
        if (filtered.getCentres().isEmpty()) {
            System.out.println("No collection centres match your criteria.");
        } else if (filtered.getCentres().size() == 1) {
            System.out.println("1 collection centre matches your criteria.");
        } else {
            System.out.println(filtered.getCentres().size() + " collection centres match your criteria.");
        }
    }

    // MODIFIES: this
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
