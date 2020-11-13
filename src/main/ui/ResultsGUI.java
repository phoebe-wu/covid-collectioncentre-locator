package ui;

import model.CollectionCentre;
import model.CollectionCentreDatabase;
import model.HealthAuthority;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ui.Main.initializeAllCollectionCentres;

public class ResultsGUI extends JPanel implements ListSelectionListener {

    private static final Dimension SCROLL_PANEL_DIMENSION = new Dimension(750,900);
    private final DefaultListModel<Object> databaseListModel;
    private final DefaultListModel<Object> favouritesListModel;
    private final DefaultListModel<Object> filteredListModel;
    private CollectionCentreDatabase collectionCentreDatabase;
    private JList<Object> databaseJList;
    private JList<Object> favouritesJList;
    private JList<Object> filteredJList;
    private JScrollPane scrollPane;
    private ImageIcon greenCheckImage;
    private JLabel imageAsLabel;
    JPanel imagePanel;



    public ResultsGUI() {
        super(new BorderLayout());

        databaseListModel = new DefaultListModel<>();
        favouritesListModel = new DefaultListModel<>();
        filteredListModel = new DefaultListModel<>();

        initializeDatabase();
        initializeFavourites();
        initializeFiltered();
        loadImagePanel();

        createListPanel();
        createFavouritesPanel();
        createSidePanel();
    }

    private void loadImagePanel() {
        imageAsLabel = new JLabel(greenCheckImage);
        imagePanel = new JPanel();
        imagePanel.add(imageAsLabel);
        String sep = System.getProperty("file.separator");
        greenCheckImage = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep
                + "greenCheckmark.png");


    }

    // EFFECTS: initializes database fields and adds collection centres to collectionCentreDatabase & databaseListModel
    private void initializeDatabase() {
        collectionCentreDatabase = new CollectionCentreDatabase();

        initializeAllCollectionCentres(collectionCentreDatabase);
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            databaseListModel.addElement(cleanResults(c));
        }

        databaseJList = new JList<>(databaseListModel);
        databaseJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        databaseJList.setSelectedIndex(0);
        databaseJList.addListSelectionListener(this);
        databaseJList.setVisibleRowCount(5);
    }

    // EFFECTS: initializes favourites list fields
    private void initializeFavourites() {
        favouritesJList = new JList<>(favouritesListModel);
        favouritesJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        favouritesJList.setSelectedIndex(0);
        favouritesJList.addListSelectionListener(this);
        favouritesJList.setVisibleRowCount(5);
    }

    // EFFECTS: initializes filtered list fields
    private void initializeFiltered() {
        filteredJList = new JList<>(filteredListModel);
        filteredJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        filteredJList.setSelectedIndex(0);
        filteredJList.addListSelectionListener(this);
        filteredJList.setVisibleRowCount(5);
    }

    // MODIFIES: this
    // EFFECTS: Creates and populates scroll panel
    private void createListPanel() {
        scrollPane = new JScrollPane(databaseJList);
        scrollPane.setPreferredSize(SCROLL_PANEL_DIMENSION);
        add(scrollPane, BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds combo boxes for city search and health authority search
    private void createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("City: "), BorderLayout.PAGE_START);
        cityPanel.add(createCityFilterBox(), BorderLayout.PAGE_END);
        cityPanel.setMaximumSize(new Dimension(300, 50));

        JPanel healthAuthorityPanel = new JPanel();
        healthAuthorityPanel.add(new JLabel("Health Authority: "), BorderLayout.PAGE_START);
        healthAuthorityPanel.add(createHealthAuthoritiesFilterBox(), BorderLayout.PAGE_END);
        healthAuthorityPanel.setMaximumSize(new Dimension(300, 100));

        sidePanel.add(cityPanel);
        sidePanel.add(healthAuthorityPanel);
        sidePanel.add(createTogglePanel(), LEFT_ALIGNMENT);

        add(sidePanel);
    }

    private JPanel createTogglePanel() {
        JPanel togglePanel = new JPanel();
        togglePanel.setLayout(new BoxLayout(togglePanel, BoxLayout.Y_AXIS));
        JToggleButton appointmentButton = new JToggleButton("Appointment Required");
        JToggleButton weekendsButton = new JToggleButton("Open on Weekends");
        JToggleButton driveThroughButton = new JToggleButton("Drive-Through Testing");
        JToggleButton childrenButton = new JToggleButton("Accepts Children 0-16");
        JToggleButton referralButton = new JToggleButton("Referral Requirement");
        JButton resetButton = new JButton("Reset Filters");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(databaseJList);
            }
        });

        togglePanel.add(appointmentButton, LEFT_ALIGNMENT);
        togglePanel.add(weekendsButton);
        togglePanel.add(driveThroughButton);
        togglePanel.add(childrenButton);
        togglePanel.add(referralButton);
        togglePanel.add(resetButton);
        togglePanel.setMaximumSize(new Dimension(300, 300));

        return togglePanel;
    }

    // EFFECTS: creates combo box to filter by city
    private JComboBox<Object> createCityFilterBox() {
        JComboBox<Object> citySearchBox = new JComboBox<>();
        List<String> cities = new ArrayList<>();
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            cities.add(c.getCity());
        }
        Collections.sort(cities);
        for (Object c : cities) {
            citySearchBox.addItem(c);
        }
        citySearchBox.setEditable(true);
        citySearchBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CollectionCentreDatabase results = new CollectionCentreDatabase();
                String city = Objects.requireNonNull(citySearchBox.getSelectedItem()).toString();

                results = collectionCentreDatabase.filterCity(city);
                presentFilteredResults(results);
            }
        });
        return citySearchBox;
    }

    // EFFECTS: creates combo box to filter by health authorities
    private JComboBox<String> createHealthAuthoritiesFilterBox() {
        String[] healthAuthorities = {"Fraser", "Vancouver Coastal", "Northern", "Vancouver Island", "Interior",
                "Provincial Health Services"};
        JComboBox<String> healthAuthoritySearchBox = new JComboBox<>(healthAuthorities);
        healthAuthoritySearchBox.setSelectedIndex(5);
        healthAuthoritySearchBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = (String) healthAuthoritySearchBox.getSelectedItem();
                if (choice != null) {
                    healthAuthorityFilter(choice);
                }
            }
        });
        return healthAuthoritySearchBox;
    }

    // MODIFIES: filteredListModel
    // EFFECTS: generates collection centres to the ones located only in the entered health authority and stores in
    private void healthAuthorityFilter(String choice) {
        CollectionCentreDatabase results = new CollectionCentreDatabase();

        switch (choice) {
            case "Fraser":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.FRASER);
                break;
            case "Vancouver Coastal":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.COASTAL);
                break;
            case "Northern":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.NORTHERN);
                break;
            case "Vancouver Island":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.ISLAND);
                break;
            case "Interior":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.INTERIOR);
                break;
            case "Provincial Health Services":
                results = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.PROVINCIAL);
        }
        presentFilteredResults(results);
    }

    // MODIFIES: scrollPane, filteredListModel, filteredJList
    // EFFECTS: presents filtered results to user in scrollPane
    private void presentFilteredResults(CollectionCentreDatabase results) {
        filteredListModel.removeAllElements();
        for (CollectionCentre c : results.getCentres()) {
            filteredListModel.addElement(cleanResults(c));
        }
        initializeFiltered();
        scrollPane.setViewportView(filteredJList);
    }


    // MODIFIES: this
    // EFFECTS: creates and adds JPanel including all favourites buttons
    private void createFavouritesPanel() {
        JPanel favouritesPanel = new JPanel();

        JButton addToFavouritesButton = createAddToFavouritesButton();
        JButton viewFavouritesButton = createViewFavouritesButton();
        JButton removeFromFavouritesButton = createRemoveFromFavouritesButton();
        JButton saveFavouritesButton = new JButton("Save Favourites List");
        JButton loadFavouritesButton = new JButton("Load Favourites List");

        favouritesPanel.add(addToFavouritesButton);
        favouritesPanel.add(removeFromFavouritesButton);
        favouritesPanel.add(viewFavouritesButton);
        favouritesPanel.add(saveFavouritesButton);
        favouritesPanel.add(loadFavouritesButton);

        add(favouritesPanel, BorderLayout.PAGE_END);
    }

    // EFFECTS: creates button to remove selected items from favourites list
    private JButton createRemoveFromFavouritesButton() {
        JButton removeFromFavouritesButton = new JButton("Remove from Favourites List");
        removeFromFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object> selectedItems = favouritesJList.getSelectedValuesList();
                for (Object c : selectedItems) {
                    favouritesListModel.removeElement(c);
                }
            }
        });
        return removeFromFavouritesButton;
    }

    // EFFECTS: creates a button that allows users to add selected items to their favourites list
    private JButton createAddToFavouritesButton() {
        JButton addToFavouritesButton = new JButton("Add to Favourites List");
        addToFavouritesButton.addActionListener(e -> {
            List<Object> selectedItems = databaseJList.getSelectedValuesList();
            for (Object c : selectedItems) {
                if (!favouritesListModel.contains(c)) {
                    favouritesListModel.addElement(c);
                }
            }
            List<Object> selected = filteredJList.getSelectedValuesList();
            for (Object c : selected) {
                if (!favouritesListModel.contains(c)) {
                    favouritesListModel.addElement(c);
                }
            }
        });

        return addToFavouritesButton;
    }

    // EFFECTS: returns a button that allows user to view favourites and switch back to the database
    private JButton createViewFavouritesButton() {
        JButton viewFavouritesButton = new JButton("View Favourites List");
        viewFavouritesButton.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count += 1;
                if (count % 2 == 0) {
                    viewFavouritesButton.setText("View Favourites List");
                    scrollPane.setViewportView(databaseJList);
                } else {
                    initializeFavourites();
                    viewFavouritesButton.setText("Back to Database");
                    scrollPane.setViewportView(favouritesJList);
                }
            }
        });
        return viewFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a clean result to display to the user
    private String cleanResults(CollectionCentre c) {
        return c.name + "   " + c.address + ", " + c.city + "  Phone: " + c.phone;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}

