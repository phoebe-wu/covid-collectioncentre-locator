package ui;

import model.CollectionCentre;
import model.CollectionCentreDatabase;
import model.FavouritesList;
import model.HealthAuthority;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ui.Main.initializeAllCollectionCentres;

public class MainGUI extends JPanel implements ListSelectionListener {

    private static final Dimension SCROLL_PANEL_DIMENSION = new Dimension(750, 900);
    private static final String JSON_STORE = "./data/FavouritesList.json";
    private final DefaultListModel databaseListModel;
    private final DefaultListModel favouritesListModel;
    private final DefaultListModel filteredListModel;
    private CollectionCentreDatabase collectionCentreDatabase;
    private CollectionCentreDatabase filtered;
    private FavouritesList favouritesList;
    private JList<CollectionCentre> databaseJList;
    private JList favouritesJList;
    private JList filteredJList;
    private JScrollPane scrollPane;
    private JPanel verificationImagePanel;
    private JLabel verificationLabel;
    private JPanel errorImagePanel;
    private JLabel errorLabel;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    public MainGUI() {
        super(new BorderLayout());

        databaseListModel = new DefaultListModel<CollectionCentre>();
        favouritesListModel = new DefaultListModel<>();
        filteredListModel = new DefaultListModel<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        favouritesList = new FavouritesList("Your Favourites");


        initializeDatabase();
        initializeFavourites();
        initializeFiltered();


        createListPanel();
        createFavouritesPanel();
        createSidePanel();
    }

    // EFFECTS: initializes database fields and adds collection centres to collectionCentreDatabase & databaseListModel
    private void initializeDatabase() {
        collectionCentreDatabase = new CollectionCentreDatabase();
        filtered = new CollectionCentreDatabase();

        initializeAllCollectionCentres(collectionCentreDatabase);
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            databaseListModel.addElement(cleanResults(c));
        }

        databaseJList = new JList<CollectionCentre>(databaseListModel);
        databaseJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        databaseJList.setSelectedIndex(0);
        databaseJList.addListSelectionListener(this);
        databaseJList.setVisibleRowCount(5);
    }

    // EFFECTS: initializes favourites list fields
    private void initializeFavourites() {
        for (CollectionCentre c : favouritesList.getCentres()) {
            if (!favouritesListModel.contains(c)) {
                favouritesListModel.addElement(cleanResults(c));
            }
        }
        favouritesJList = new JList<>(favouritesListModel);
        favouritesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        favouritesJList.setSelectedIndex(0);
        favouritesJList.addListSelectionListener(this);
        favouritesJList.setVisibleRowCount(5);
    }

    // EFFECTS: initializes filtered list fields
    private void initializeFiltered() {
        filteredJList = new JList<>(filteredListModel);
        filteredJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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

        createVerificationImagePanel();
        createErrorImagePanel();

        sidePanel.add(verificationImagePanel);
        sidePanel.add(errorImagePanel);
        sidePanel.add(createCityFilterPanel());
        sidePanel.add(createHealthAuthoritiesFilterPanel());
        sidePanel.add(createTogglePanel(), LEFT_ALIGNMENT);

        add(sidePanel);
    }

    // EFFECTS: creates a panel with an image
    private void createVerificationImagePanel() {
        ImageIcon greenCheckImage = new ImageIcon("images/greenCheckmark.png");
        Image image = greenCheckImage.getImage();
        Image resized = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        greenCheckImage = new ImageIcon(resized);
        verificationImagePanel = new JPanel();
        verificationImagePanel.add(new JLabel(greenCheckImage));
        verificationLabel = new JLabel();
        verificationImagePanel.add(verificationLabel);
        verificationImagePanel.setMaximumSize(new Dimension(300, 50));
        verificationImagePanel.setVisible(false);
    }

    // EFFECTS: creates a panel with an image and error message
    private void createErrorImagePanel() {
        ImageIcon redX = new ImageIcon("images/redx.png");
        Image image = redX.getImage();
        Image resized = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        redX = new ImageIcon(resized);
        errorImagePanel = new JPanel();
        errorImagePanel.add(new JLabel(redX));
        errorLabel = new JLabel();
        errorImagePanel.add(errorLabel);
        errorImagePanel.setMaximumSize(new Dimension(300, 50));
        errorImagePanel.setVisible(false);
    }

    // EFFECTS: creates a panel with toggle switches
    private JPanel createTogglePanel() {
        JPanel togglePanel = new JPanel();
        togglePanel.setLayout(new BoxLayout(togglePanel, BoxLayout.Y_AXIS));
        JRadioButton appointmentButton = new JRadioButton("Appointment Not Required");
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (appointmentButton.isSelected()) {
                    if (filtered.getCentres().isEmpty()) {
                        filtered = collectionCentreDatabase.filterAppointment(false);
                    }
                    filtered = filtered.filterAppointment(false);
                }
                presentFilteredResults(filtered);
            }
        });
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

        togglePanel.add(appointmentButton);
        togglePanel.add(weekendsButton);
        togglePanel.add(driveThroughButton);
        togglePanel.add(childrenButton);
        togglePanel.add(referralButton);
        togglePanel.add(resetButton);
        togglePanel.setMaximumSize(new Dimension(200, 300));

        return togglePanel;
    }

    // EFFECTS: creates combo box to filter by city
    private JPanel createCityFilterPanel() {
        JComboBox<Object> citySearchBox = new JComboBox<>();
        addCityList(citySearchBox);
        citySearchBox.setEditable(true);
        citySearchBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = Objects.requireNonNull(citySearchBox.getSelectedItem()).toString();
                filtered = collectionCentreDatabase.filterCity(city);
                presentFilteredResults(filtered);
            }
        });
        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("City: "), BorderLayout.PAGE_START);
        cityPanel.add(citySearchBox, BorderLayout.PAGE_END);
        cityPanel.setMaximumSize(new Dimension(300, 50));
        return cityPanel;
    }

    // MODIFIES: this
    // EFFECTS: adds city names from database to combo box options
    private void addCityList(JComboBox<Object> citySearchBox) {
        List<String> cities = new ArrayList<>();
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            cities.add(c.getCity());
        }
        Collections.sort(cities);
        for (Object c : cities) {
            citySearchBox.addItem(c);
        }
    }

    // EFFECTS: creates combo box to filter by health authorities
    private JPanel createHealthAuthoritiesFilterPanel() {
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
        JPanel healthAuthorityPanel = new JPanel();
        healthAuthorityPanel.add(new JLabel("Health Authority: "), BorderLayout.PAGE_START);
        healthAuthorityPanel.add(healthAuthoritySearchBox, BorderLayout.PAGE_END);
        healthAuthorityPanel.setMaximumSize(new Dimension(300, 100));
        return healthAuthorityPanel;
    }

    // MODIFIES: filteredListModel
    // EFFECTS: generates collection centres to the ones located only in the entered health authority and stores in
    private void healthAuthorityFilter(String choice) {

        switch (choice) {
            case "Fraser":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.FRASER);
                break;
            case "Vancouver Coastal":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.COASTAL);
                break;
            case "Northern":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.NORTHERN);
                break;
            case "Vancouver Island":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.ISLAND);
                break;
            case "Interior":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.INTERIOR);
                break;
            case "Provincial Health Services":
                filtered = collectionCentreDatabase.filterHealthAuthority(HealthAuthority.PROVINCIAL);
        }
        presentFilteredResults(filtered);
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
        saveFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(favouritesList);
                    jsonWriter.close();
                    showImage("Saved Favourites List!");
                } catch (FileNotFoundException e) {
                    showErrorImage("Could not save Favourites List");
                }
            }
        });
        JButton loadFavouritesButton = new JButton("Load Favourites List");
        loadFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    favouritesList = jsonReader.read();
                    showImage("Loaded Favourites List!");
                } catch (IOException e) {
                    showErrorImage("Could not load Favourites List");
                }
            }
        });

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
                int[] selectedItems = favouritesJList.getSelectedIndices();
                for (int i : selectedItems) {
                    CollectionCentre c = favouritesList.getCentres().get(i);
                    favouritesList.removeCollectionCenter(c);
                    favouritesListModel.removeElement(c);
                    showImage("Successfully Removed!");
                }
            }
        });
        return removeFromFavouritesButton;
    }

    // EFFECTS: creates a button that allows users to add selected items to their favourites list
    private JButton createAddToFavouritesButton() {
        JButton addToFavouritesButton = new JButton("Add to Favourites List");
        addToFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFromJList(databaseJList, collectionCentreDatabase);
                addFromJList(filteredJList, filtered);
            }
            private void addFromJList(JList<CollectionCentre> databaseJList, CollectionCentreDatabase collectionCentreDatabase) {
                int[] selectedItems = databaseJList.getSelectedIndices();
                for (int i : selectedItems) {
                    CollectionCentre c = collectionCentreDatabase.getCentres().get(i);
                    if (!favouritesList.getCentres().contains(c)) {
                        favouritesList.addCollectionCentre(c);
                        showImage("Successfully added to Favourites!");
                    } else {
                        showErrorImage("Already in Favourites");
                    }
                }
            }
        });
        return addToFavouritesButton;
    }

    private void showErrorImage(String message) {
        errorLabel.setText(message);
        errorImagePanel.setVisible(true);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorImagePanel.setVisible(false);
            }
        });
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: shows imagePanel for brief second
    private void showImage(String message) {
        verificationLabel.setText(message);
        verificationImagePanel.setVisible(true);
        Timer timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificationImagePanel.setVisible(false);
            }
        });
        timer.start();
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
                    updateFavourites();
                    favouritesJList.setModel(favouritesListModel);
                    viewFavouritesButton.setText("Back to Database");
                    scrollPane.setViewportView(favouritesJList);
                }
            }
        });
        return viewFavouritesButton;
    }

    private void updateFavourites() {
        for (CollectionCentre c : favouritesList.getCentres()) {
            String s = cleanResults(c);
            if (!favouritesListModel.contains(s)) {
                favouritesListModel.addElement(s);
            }
        }
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

