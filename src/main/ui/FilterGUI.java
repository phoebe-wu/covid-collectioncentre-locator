package ui;

import exceptions.CentreAlreadyAddedException;
import exceptions.CentreDoesNotExistException;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ui.Main.initializeAllCollectionCentres;

// Represents and shows a GUI that allows the user to view and filter Collection Centres
// Adapted from C3 Traffic Lights Lecture Lab
public class FilterGUI extends JPanel implements ListSelectionListener {

    private static final Dimension SCROLL_PANEL_DIMENSION = new Dimension(800, 900);
    private static final String JSON_STORE = "./data/FavouritesList.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;
    private DefaultListModel databaseListModel;
    private DefaultListModel favouritesListModel;
    private DefaultListModel filteredListModel;
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
    private JLabel criteriaMessage;
    private JCheckBox driveThroughBox;
    private JCheckBox appointmentBox;
    private JCheckBox referralBox;
    private JCheckBox childrenBox;
    private JCheckBox weekendBox;
    private JCheckBox weekdayBox;


    public FilterGUI() {
        super(new BorderLayout());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeDatabase();
        initializeFavourites();
        initializeFiltered();

        createListPanel();
        createFavouritesPanel();
        createSidePanel();
    }

    // MODIFIES: this
    // EFFECTS: adds CollectionCentres to collectionCentreDatabase, databaseListModel, databaseJList
    private void initializeDatabase() {
        collectionCentreDatabase = new CollectionCentreDatabase();
        databaseListModel = new DefaultListModel<CollectionCentre>();

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


    // EFFECTS: initializes favouritesList, favouritesListModel, favouritesJList
    private void initializeFavourites() {
        favouritesList = new FavouritesList("Your Favourites");
        favouritesListModel = new DefaultListModel<>();
        favouritesJList = new JList<>(favouritesListModel);
        favouritesJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        favouritesJList.setSelectedIndex(0);
        favouritesJList.addListSelectionListener(this);
        favouritesJList.setVisibleRowCount(5);
    }


    // EFFECTS: initializes filtered, filteredListModel, filteredJList
    private void initializeFiltered() {
        filtered = new CollectionCentreDatabase();
        filteredListModel = new DefaultListModel<>();
        filteredJList = new JList<>(filteredListModel);
        filteredJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        filteredJList.setSelectedIndex(0);
        filteredJList.addListSelectionListener(this);
        filteredJList.setVisibleRowCount(5);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a scroll pane to the GUI
    private void createListPanel() {
        scrollPane = new JScrollPane(databaseJList);
        scrollPane.setPreferredSize(SCROLL_PANEL_DIMENSION);
        add(scrollPane, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: adds city names from database to combo box options in alphabetical order
    private void addCityList(JComboBox<Object> citySearchBox) {
        List<String> cities = new ArrayList<>();
        for (CollectionCentre c : collectionCentreDatabase.getCentres()) {
            if (!cities.contains(c.getCity())) {
                cities.add(c.getCity());
            }
        }
        Collections.sort(cities);
        for (Object c : cities) {
            citySearchBox.addItem(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates combo box to filter CollectionCentres by city
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
                resetCheckBoxes();
                criteriaMessage.setText(updateCriteriaMessage());
            }
        });
        JPanel cityPanel = new JPanel();
        cityPanel.add(new JLabel("City: "), BorderLayout.PAGE_START);
        cityPanel.add(citySearchBox, BorderLayout.PAGE_END);
        cityPanel.setMaximumSize(new Dimension(300, 50));
        return cityPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates combo box to filter CollectionCentres by health authorities
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
                resetCheckBoxes();
                criteriaMessage.setText(updateCriteriaMessage());
            }
        });
        JPanel healthAuthorityPanel = new JPanel();
        healthAuthorityPanel.add(new JLabel("Health Authority: "), BorderLayout.PAGE_START);
        healthAuthorityPanel.add(healthAuthoritySearchBox, BorderLayout.PAGE_END);
        healthAuthorityPanel.setMaximumSize(new Dimension(300, 100));
        return healthAuthorityPanel;
    }

    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: creates and adds combo boxes for city search and health authority search
    private void createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        createVerificationImagePanel();
        createErrorImagePanel();
        criteriaMessage = new JLabel(collectionCentreDatabase.getCentres().size()
                + " collection centres match your criteria");
        sidePanel.add(criteriaMessage);
        sidePanel.add(verificationImagePanel);
        sidePanel.add(errorImagePanel);
        sidePanel.add(createCityFilterPanel());
        sidePanel.add(createHealthAuthoritiesFilterPanel());
        sidePanel.add(createTogglePanel(), LEFT_ALIGNMENT);

        add(sidePanel);
    }

    // MODIFIES: this
    // EFFECTS: generates a message that indicates the number of CollectionCentres that match the user's criteria
    private String updateCriteriaMessage() {
        String message;
        if (filtered.getCentres().isEmpty()) {
            message = "No collection centres match your criteria.";
        } else if (filtered.getCentres().size() == 1) {
            message = "1 collection centre matches your criteria.";
        } else {
            message = filtered.getCentres().size() + " collection centres match your criteria.";
        }
        return message;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with an verification image
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

    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: shows an error image with given message as feedback to the users action
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
    // EFFECTS: shows a verification image with given message as feedback to the users action
    private void showVerificationImage(String message) {
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

    // MODIFIES: this
    // EFFECTS: creates a panel with toggle switches
    private JPanel createTogglePanel() {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        createAppointmentBox();
        createWeekendBox();
        createWeekdayBox();
        createDriveThroughBox();
        createChildrenBox();
        createReferralBox();
        JButton resetButton = createResetButton();

        checkBoxPanel.add(new JLabel("Filter results by: "));
        checkBoxPanel.add(appointmentBox);
        checkBoxPanel.add(weekdayBox);
        checkBoxPanel.add(weekendBox);
        checkBoxPanel.add(driveThroughBox);
        checkBoxPanel.add(childrenBox);
        checkBoxPanel.add(referralBox);
        checkBoxPanel.add(resetButton);
        checkBoxPanel.setMaximumSize(new Dimension(500, 300));

        return checkBoxPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a button for user to reset all filters
    private JButton createResetButton() {
        JButton resetButton = new JButton("Reset Filters");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(databaseJList);
                resetCheckBoxes();
                filtered = collectionCentreDatabase;
                criteriaMessage.setText(collectionCentreDatabase.getCentres().size()
                        + " collection centres match your criteria");
            }
        });
        return resetButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a button that shows the user the collection centres that no not require referral when selected
    private void createReferralBox() {
        referralBox = new JCheckBox("No Referral Required");
        referralBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterReferral(false);
                    presentFilteredResults(filtered);
                    referralBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a box that shows the user the collection centres that accept all children aged 0-16 when
    //          selected
    private void createChildrenBox() {
        childrenBox = new JCheckBox("Accepts All Children 0-16");
        childrenBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterChildren(true);
                    presentFilteredResults(filtered);
                    childrenBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a box that shows the user all the collection centres that offer drive-through testing when
    //          selected
    private void createDriveThroughBox() {
        driveThroughBox = new JCheckBox("Offers Drive-Through Testing");
        driveThroughBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterDriveThrough(true);
                    presentFilteredResults(filtered);
                    driveThroughBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a box that shows the user all the collection centres that are open only on weekdays when
    //          selected
    private void createWeekdayBox() {
        weekdayBox = new JCheckBox("Open Only on Weekdays");
        weekdayBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterWeekend(false);
                    presentFilteredResults(filtered);
                    weekdayBox.setEnabled(false);
                    weekendBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a box that shows the user all the collection centres that are open on weekends when selected
    private void createWeekendBox() {
        weekendBox = new JCheckBox("Open on Weekends");
        weekendBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterWeekend(true);
                    presentFilteredResults(filtered);
                    weekdayBox.setEnabled(false);
                    weekendBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a box that shows the user all the collection centres that do not require appointments when
    //          selected
    private void createAppointmentBox() {
        appointmentBox = new JCheckBox("No Appointment Required");
        appointmentBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (filtered.getCentres().isEmpty()) {
                    filtered = collectionCentreDatabase;
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filtered = filtered.filterAppointment(false);
                    presentFilteredResults(filtered);
                    appointmentBox.setEnabled(false);
                    criteriaMessage.setText(updateCriteriaMessage());
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: deselects all check boxes and enables them from previous filtering
    private void resetCheckBoxes() {
        appointmentBox.setSelected(false);
        weekdayBox.setSelected(false);
        weekendBox.setSelected(false);
        driveThroughBox.setSelected(false);
        childrenBox.setSelected(false);
        referralBox.setSelected(false);
        appointmentBox.setEnabled(true);
        weekendBox.setEnabled(true);
        weekdayBox.setEnabled(true);
        driveThroughBox.setEnabled(true);
        childrenBox.setEnabled(true);
        referralBox.setEnabled(true);
        criteriaMessage.setText(collectionCentreDatabase.getCentres().size()
                + " collection centres match your criteria");
    }


    // MODIFIES: this
    // EFFECTS: creates and adds JPanel including all favourites buttons
    private void createFavouritesPanel() {
        JPanel favouritesPanel = new JPanel();

        favouritesPanel.add(createAddToFavouritesButton());
        favouritesPanel.add(createRemoveFromFavouritesButton());
        favouritesPanel.add(createViewFavouritesButton());
        favouritesPanel.add(createSaveButton());
        favouritesPanel.add(createLoadButton());

        add(favouritesPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for the user to load a previously saved Favourites List
    private JButton createLoadButton() {
        JButton loadFavouritesButton = new JButton("Load Favourites List");
        loadFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    favouritesList = jsonReader.read();
                    showVerificationImage("Loaded Favourites List!");
                } catch (IOException e) {
                    showErrorImage("Could not load Favourites List");
                }
            }
        });
        return loadFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a button for the user to save their Favourites List
    private JButton createSaveButton() {
        JButton saveFavouritesButton = new JButton("Save Favourites List");
        saveFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(favouritesList);
                    jsonWriter.close();
                    showVerificationImage("Saved Favourites List!");
                } catch (FileNotFoundException e) {
                    showErrorImage("Could not save Favourites List");
                }
            }
        });
        return saveFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: creates button for the user to remove the selected items from their Favourites List
    private JButton createRemoveFromFavouritesButton() {
        JButton removeFromFavouritesButton = new JButton("Remove from Favourites List");
        removeFromFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedItems = favouritesJList.getSelectedIndices();
                for (int i : selectedItems) {
                    CollectionCentre c = favouritesList.getCentres().get(i);
                    try {
                        favouritesList.removeCollectionCentre(c);
                    } catch (CentreDoesNotExistException centreDoesNotExistException) {
                        System.out.println("CollectionCentre not in FavouritesList");
                    }
                    favouritesListModel.remove(i);
                    criteriaMessage.setText(favouritesList.sizeMessage());
                    showVerificationImage("Successfully Removed!");
                }
            }
        });
        return removeFromFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: creates a button that allows the user to add the selected items to their Favourites List
    private JButton createAddToFavouritesButton() {
        JButton addToFavouritesButton = new JButton("Add to Favourites List");
        addToFavouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFromJList(databaseJList, collectionCentreDatabase);
                addFromJList(filteredJList, filtered);
            }
        });
        return addToFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: adds selected CollectionCentres from JList to FavouritesList
    private void addFromJList(JList<CollectionCentre> databaseJList,
                              CollectionCentreDatabase collectionCentreDatabase) {
        int[] selectedItems = databaseJList.getSelectedIndices();
        for (int i : selectedItems) {
            CollectionCentre c = collectionCentreDatabase.getCentres().get(i);
            if (!favouritesList.getCentres().contains(c)) {
                try {
                    favouritesList.addCollectionCentre(c);
                } catch (CentreAlreadyAddedException e) {
                    showErrorImage("Already in Favourites");
                }
                showVerificationImage("Successfully added to Favourites!");
            } else {
                showErrorImage("Already in Favourites");
            }
        }
    }

    // MODIFIES: this
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
                    criteriaMessage.setText(collectionCentreDatabase.getCentres().size()
                            + " collection centres match your criteria");
                } else {
                    updateFavourites();
                    favouritesJList.setModel(favouritesListModel);
                    viewFavouritesButton.setText("Back to Database");
                    scrollPane.setViewportView(favouritesJList);
                    criteriaMessage.setText(favouritesList.sizeMessage());
                }
            }
        });
        return viewFavouritesButton;
    }

    // MODIFIES: this
    // EFFECTS: adds all CollectionCentres in favourites list to favouritesListModel
    private void updateFavourites() {
        for (CollectionCentre c : favouritesList.getCentres()) {
            String s = cleanResults(c);
            if (!favouritesListModel.contains(s)) {
                favouritesListModel.addElement(s);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: adds all CollectionCentres in filtered to filteredListModel
    private void updateFiltered(CollectionCentreDatabase filtered) {
        filteredListModel.removeAllElements();
        for (CollectionCentre c : filtered.getCentres()) {
            filteredListModel.addElement(cleanResults(c));
        }
    }


    // MODIFIES: this
    // EFFECTS: presents filtered results to user in scrollPane
    private void presentFilteredResults(CollectionCentreDatabase filtered) {
        updateFiltered(filtered);
        filteredJList.setModel(filteredListModel);
        scrollPane.setViewportView(filteredJList);
    }

    // MODIFIES: this
    // EFFECTS: creates a clean result to display to the user
    private String cleanResults(CollectionCentre c) {
        return c.name + "   " + c.address + ", " + c.city + ", BC  Phone: " + c.phone;
    }

    // EFFECTS: required by ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}

