package ui;

import model.CollectionCentre;
import model.CollectionCentreHub;
import model.FavouritesList;
import model.HealthAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.Main.initializeAllCollectionCentres;

// Adapted from TellerApp and FitGymLife
// Collection centre locator application
public class CollectionCentreLocatorApp {
    private Scanner input;
    private CollectionCentreHub database;
    private CollectionCentreHub secondaryDatabase;
    private List<CollectionCentre> result;
    private FavouritesList favList;

    // EFFECTS: runs the locator application
    public CollectionCentreLocatorApp() {
        runLocatorApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLocatorApp() {
        boolean runProgram = true;
        String command = null;

        initializeApp();

        while (runProgram) {
            sortingMenu();
            command = input.next();

            if (command.equals("quit")) {
                runProgram = false;
            } else {
                processSearchCommand(command);
            }

            furtherFilterResultsOption();
            command = input.next();

            if (command.equals("quit")) {
                runProgram = false;
            } else {
                processFilterCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes all collection centres into database
    private void initializeApp() {
        database = new CollectionCentreHub();
        initializeAllCollectionCentres(database);
        input = new Scanner(System.in);
        result = new ArrayList<>();
        favList = new FavouritesList("Favourite's List");
    }


    // EFFECTS: displays menu of sorting options to user
    private void sortingMenu() {
        System.out.println("\nHow would you like to sort collection centres by:");
        System.out.println("\tcity -> City");
        System.out.println("\thealth -> Health Authority");
        System.out.println("\tfave -> View Favourites List");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command to search
    private void processSearchCommand(String command) {
        if (command.equals("city")) {
            doCityFilter();
        } else if (command.equals("health")) {
            doHealthAuthorityFilter();
        } else if (command.equals("fave")) {
            System.out.println(cleanResults(favList.getCentres()));
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
        secondaryDatabase = database.filterCityHub(city);
        result = database.filterCityList(city);
    }

    // MODIFIES: secondaryDatabase, result
    // EFFECTS: generates collection centres to the ones located only in the entered health authority and stores in
    //          secondaryDatabase for if user wants to filter again after OR
    //          result for if user wants to view results right after
    private void doHealthAuthorityFilter() {
        System.out.println("Enter the health authority you want to search for collection centres in");
        String ha = input.next();
        if (ha.equals("island")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.ISLAND);
            result = database.filterHealthAuthorityList(HealthAuthority.ISLAND);
        } else if (ha.equals("coastal")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.COASTAL);
            result = database.filterHealthAuthorityList(HealthAuthority.COASTAL);
        } else if (ha.equals("provincial")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.PROVINCIAL);
            result = database.filterHealthAuthorityList(HealthAuthority.PROVINCIAL);
        } else if (ha.equals("northern")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.NORTHERN);
            result = database.filterHealthAuthorityList(HealthAuthority.NORTHERN);
        } else if (ha.equals("fraser")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.FRASER);
            result = database.filterHealthAuthorityList(HealthAuthority.FRASER);
        } else if (ha.equals("interior")) {
            secondaryDatabase = database.filterHealthAuthorityHub(HealthAuthority.INTERIOR);
            result = database.filterHealthAuthorityList(HealthAuthority.INTERIOR);
        } else {
            System.out.println("Please enter 'island', 'coastal', 'provincial', 'northern', 'fraser', or 'interior'");
        }
    }


    // EFFECTS: displays filter results menu to user to filter location results by appointment, referral, children,
    //          open on weekends, or drive-through
    private void furtherFilterResultsOption() {
        System.out.println("Would you like to further filter your results");
        System.out.println("\tyes-> To further filter results");
        System.out.println("\tno -> View results");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes user command if they want to further filter their results
    private void processFilterCommand(String command) {
        if (command.equals("no")) {
            System.out.println(cleanResults(result));
            addToFavouritesMenu();
            command = input.next();
            processFavouritesCommand(command);
        } else if (command.equals("yes")) {
            furtherFilterMenu();
            command = input.next();
            processFurtherFilterCommand(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays menu of filtering options to user
    private void furtherFilterMenu() {
        System.out.println("How would you like to further filter your results?");
        System.out.println("\ta -> Appointment Requirement");
        System.out.println("\tr -> Referral Requirement");
        System.out.println("\tc -> Takes all children between the ages of 0-16");
        System.out.println("\td -> Offers Drive-through testing");
        System.out.println("\tw -> Open on at least one day of the weekend");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes further filtering user command
    private void processFurtherFilterCommand(String command) {
        if (command.equals("a")) {
            furtherBooleanFilterMenu();
            command = input.next();
            processAppointmentCommand(command);
        } else if (command.equals("r")) {
            furtherBooleanFilterMenu();
            command = input.next();
            processReferralCommand(command);
        } else if (command.equals("c")) {
            furtherBooleanFilterMenu();
            command = input.next();
            processChildrenCommand(command);
        } else if (command.equals("d")) {
            furtherBooleanFilterMenu();
            command = input.next();
            processDriveThroughCommand(command);
        } else if (command.equals("w")) {
            furtherBooleanFilterMenu();
            command = input.next();
            processWeekendCommand(command);
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays true or false boolean filtering menu to user
    //          e.g. true -> needs appointment
    //          e.g. false -> doesn't need referral
    private void furtherBooleanFilterMenu() {
        System.out.println("true");
        System.out.println("false");
    }

    // MODIFIES: result
    // EFFECTS: filters results from last sorting to
    private void processAppointmentCommand(String command) {
        result = secondaryDatabase.filterAppointment(Boolean.parseBoolean(command));
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    private void processReferralCommand(String command) {
        result = secondaryDatabase.filterReferral(Boolean.parseBoolean(command));
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    private void processWeekendCommand(String command) {
        result = secondaryDatabase.filterWeekend(Boolean.parseBoolean(command));
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    private void processDriveThroughCommand(String command) {
        result = secondaryDatabase.filterDriveThrough(Boolean.parseBoolean(command));
        System.out.println(cleanResults(result));
        addToFavouritesMenu();
        command = input.next();
        processFavouritesCommand(command);
    }

    private void processChildrenCommand(String command) {
        result = secondaryDatabase.filterChildren(Boolean.parseBoolean(command));
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
        System.out.println("\tq -> quit");
    }

    private void processFavouritesCommand(String command) {
        if (command.equals("yes")) {
            for (CollectionCentre c : result) {
                favList.addCollectionCentre(c);
            }
            System.out.println("Added to your favourite's list!");
        } else if (command.equals("no")) {
            System.out.println("Results were not added");
            ;
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: returns a clean list of results including only a collection centre's name, address + city, and phone
//          number if available
    public List<String> cleanResults(List<CollectionCentre> results) {
        List<String> clean = new ArrayList<>();

        for (CollectionCentre c : results) {
            clean.add(c.name + "   " + c.address + ", " + c.city + "  " + c.phone);
        }

        return clean;
    }

}
