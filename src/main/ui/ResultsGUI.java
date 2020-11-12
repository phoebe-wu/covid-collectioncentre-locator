package ui;

import model.CollectionCentre;
import model.CollectionCentreDatabase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static ui.Main.initializeAllCollectionCentres;

public class ResultsGUI extends JPanel implements ListSelectionListener {

    private CollectionCentreDatabase collectionCentreDatabase;
    private LocatorGUI locatorGUI;

    private JList databaseJList;
    private JList favouritesJList;
    private DefaultListModel databaseListModel;
    private DefaultListModel favouritesListModel;
    private List selectedItems;

    private JScrollPane scrollPane;


    public ResultsGUI(LocatorGUI locatorGUI) {
        super(new BorderLayout());

        this.locatorGUI = locatorGUI;
        selectedItems = new ArrayList();
        favouritesListModel = new DefaultListModel();

        initializeDatabase();
        updateFavourites();

        createListPanel();
        createFavouritesPanel();
        createSearchPanel();
    }

    // EFFECTS: initializes database fields and adds collection centres to collectionCentreDatabase & databaseListModel
    private void initializeDatabase() {
        collectionCentreDatabase = new CollectionCentreDatabase();
        databaseListModel = new DefaultListModel();

        initializeAllCollectionCentres(collectionCentreDatabase);
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            databaseListModel.addElement(cleanResults(c));
        }

        databaseJList = new JList(databaseListModel);
        databaseJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        databaseJList.setSelectedIndex(0);
        databaseJList.addListSelectionListener(this);
        databaseJList.setVisibleRowCount(5);
    }

    // EFFECTS: initializes favourites list fields
    private void updateFavourites() {
        favouritesJList = new JList(favouritesListModel);
        favouritesJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        favouritesJList.setSelectedIndex(0);
        favouritesJList.addListSelectionListener(this);
        favouritesJList.setVisibleRowCount(5);
    }

    // MODIFIES: this
    // EFFECTS: Creates and populates scroll panel
    private void createListPanel() {
        scrollPane = new JScrollPane(databaseJList);
        add(scrollPane, BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds combo boxes for city search and health authority search
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
        citySearchBox.setEditable(true);

        String[] healthAuthorities = {"Fraser", "Vancouver Coastal", "Northern", "Vancouver Island", "Interior",
                "Provincial Health Services"};
        JComboBox healthAuthoritySearchBox = new JComboBox(healthAuthorities);
        healthAuthoritySearchBox.setSelectedIndex(5);

        sidePanel.add(citySearchBox, BorderLayout.PAGE_START);
        sidePanel.add(healthAuthoritySearchBox, BorderLayout.PAGE_END);
        add(sidePanel, BorderLayout.EAST);
    }


    // MODIFIES: this
    // EFFECTS: creates addToFavourites and ViewFavourites buttons
    private void createFavouritesPanel() {
        JPanel favouritesPanel = new JPanel();

        JButton addToFavouritesButton = new JButton("Add to Favourites List");
        favouritesPanel.add(addToFavouritesButton, BorderLayout.PAGE_START);
        addToFavouritesButton.addActionListener(new AddToFavouritesListener());

        JButton viewFavouritesButton = new JButton("View Favourites List");
        favouritesPanel.add(viewFavouritesButton, BorderLayout.PAGE_END);
        viewFavouritesButton.addActionListener(new ViewFavouritesButtonListener());

        JButton removeFromFavouritesButton = new JButton("Remove from Favourites List");
        favouritesPanel.add(removeFromFavouritesButton);
        removeFromFavouritesButton.addActionListener(new RemoveFromFavouritesListener());

        add(favouritesPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: creates a clean result to display to the user
    private String cleanResults(CollectionCentre c) {
        return c.name + "   " + c.address + ", " + c.city + "  Phone: " + c.phone;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO: HUH?
    }

    private class AddToFavouritesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedItems = databaseJList.getSelectedValuesList();
            for (Object c : selectedItems) {
                if (!favouritesListModel.contains(c)) {
                    favouritesListModel.addElement(c);
                }
            }
        }
    }

    private class ViewFavouritesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int count = 0;
            count += 1;
            System.out.println(count);
            if (count % 2 == 0) {
                scrollPane.setViewportView(databaseJList);
            } else {
                updateFavourites();
                scrollPane.setViewportView(favouritesJList);
            }
        }

    }

    private class RemoveFromFavouritesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedItems = favouritesJList.getSelectedValuesList();
            for (Object c : selectedItems) {
                favouritesListModel.removeElement(c);
            }
        }
    }
}

