package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Toggle {

    protected LocatorApp locatorApp;
    protected JToggleButton toggleButton;
    private boolean filter;
    private boolean active;
    private Color buttonColour = new Color(255, 255, 255);
    private Color borderColour = new Color(50, 50, 50);
    private Color offColour = new Color(200, 200, 200);
    private Color onColour = new Color(0, 125, 255);

    public Toggle(LocatorApp app, JComponent parent) {
        locatorApp = app;
        createButton(parent);
        addToParent(parent);
        filter = false;
        active = false;
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: customizes the button used for this tool
    protected JToggleButton customizeButton(JToggleButton toggleButton) {
        toggleButton.setBorderPainted(true);
        toggleButton.setFocusPainted(true);
        toggleButton.setContentAreaFilled(true);
        return toggleButton;
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Toggle's active field to true
    public void activate() {
        filter = true;
    }

    // EFFECTS: sets this Toggle's active field to false
    public void deactivate() {
        filter = false;
    }

    // EFFECTS: resets this toggle's settings to neutral
    public void reset() {
        active = false;
    }

    // EFFECTS: creates button to activate toggle
    protected void createButton(JComponent parent) {
        // stub
    }

    // EFFECTS: adds a listener for this toggle
    protected void addListener() {
        toggleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                deactivate();
                toggleButton.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds the given toggle to the parent component
    public void addToParent(JComponent parent) {
        parent.add(toggleButton);
    }

    // EFFECTS: default behaviour does nothing
    public void mousePressed(MouseEvent e) {
    }

}
