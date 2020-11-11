package ui;

import model.CollectionCentre;
import model.CollectionCentreDatabase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static ui.Main.initializeAllCollectionCentres;

public class ResultsGUI extends JPanel implements ListSelectionListener {

    private JList results;
    private DefaultListModel listModel;
    private CollectionCentreDatabase database;
    private LocatorGUI locatorGUI;


    public ResultsGUI(LocatorGUI locatorGUI) {
        super(new BorderLayout());

        this.locatorGUI = locatorGUI;
        initializeDatabase();
        createResultsPanel();
        createFavouritesPanel();
        createSearchPanel();

        JToggleButton appointmentToggle = new JToggleButton("Appointment");
        add(appointmentToggle, BorderLayout.EAST);

    }

    private void createSearchPanel() {
        JPanel sidePanel = new JPanel();
        String[] cities = {"100 Mile House", "Abbotsford", "Alert Bay", "Ashcroft", "Atlin", "Bamfield", "Bella Bella",
                "Bella Coola", "Burnaby", "Burns Lake", "Campbell River", "Castlegar", "Chetwynd", "Chilliwack",
                "Clearwater", "Coquitlam", "Courtenay", "Cranbook", "Creston", "Dawson Creek", "Dease Lake", "Delta",
                "Ditidaht", "Duncan", "Enderby", "Fort Nelson", "Fort St.John", "Fraser Lake", "Gold River", "Golden",
                "Grand Forks", "Hazelton", "Hope", "Houston", "Hudson's Hope", "Hupacasath", "Huu ay aht", "Invermere",
                "Kamloops", "Kewlona", "Kitimat", "Langley", "Mackenzie", "Maple Ridge", "McBride", "Merritt",
                "Mission", "Nanaimo", "Nelson", "North Vancouver", "Pemberton", "Penticton", "Port Alberni",
                "Port Alice", "Port Hardy", "Port McNeill", "Powell River", "Prince George", "Prince Rupert",
                "Queen Charlotte", "Quesnel", "Revelstoke", "Richmond", "Saanichton", "Salmo", "Salmon Arm",
                "Salt Spring Island", "Sechelt", "Smithers", "Sointula", "Sparwood", "Squamish", "Stewart", "Surrey",
                "Terrace", "Tofino", "Trail", "Tseshaht", "Tumbler Ridge", "Valemount", "Vancouver", "Vanderhoof",
                "Vernon", "Victoria", "Village of Masset", "Whistler", "White Rock", "Williams Lake"};
        JComboBox citySearchBox = new JComboBox(cities);
        sidePanel.add(citySearchBox, BorderLayout.PAGE_START);
        citySearchBox.setEditable(true);

        String[] healthAuthorities = {"Fraser", "Vancouver Coastal", "Northern", "Vancouver Island", "Interior",
                "Provincial Health Services"};
        JComboBox healthAuthoritySearchBox = new JComboBox(healthAuthorities);
        healthAuthoritySearchBox.setSelectedIndex(5);
        sidePanel.add(healthAuthoritySearchBox, BorderLayout.PAGE_END);

        add(sidePanel, BorderLayout.EAST);
    }

    private void createResultsPanel() {
        results = new JList(listModel);
        results.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        results.setSelectedIndex(0);
        results.addListSelectionListener(this);
        results.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(results);
        add(listScrollPane, BorderLayout.CENTER);
    }

    private void initializeDatabase() {
        database = new CollectionCentreDatabase();
        initializeAllCollectionCentres(database);
        listModel = new DefaultListModel();
        for (CollectionCentre c : database.getCentres()) {
            listModel.addElement(cleanResults(c));
        }
    }

    private void createFavouritesPanel() {
        // Create Favourite Buttons
        JPanel favouritesPanel = new JPanel();

        JButton addToFavouritesButton = new JButton("Add to Favourites List");
        favouritesPanel.add(addToFavouritesButton, BorderLayout.PAGE_START);
        JButton viewFavouritesButton = new JButton("View Favourites List");
        favouritesPanel.add(viewFavouritesButton, BorderLayout.PAGE_END);

        add(favouritesPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: creates a clean result to display to the user
    private String cleanResults(CollectionCentre c) {
        return c.name + "   " + c.address + ", " + c.city + "  Phone: " + c.phone;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (results.getSelectedIndex() == -1) {
                //No selection, disable fire button.


            } else {
                //Selection, enable the fire button.

            }
        }
    }

}

