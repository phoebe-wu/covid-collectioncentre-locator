package ui;

import javax.swing.*;
import java.awt.*;

// Represents and shows a GUI for LocatorApp console application
// Adapted from C3 Traffic Light Lecture Lab
public class LocatorGUI extends JFrame {
    private static final String TITLE = "Find a Covid-19 Testing Centre Near You";
    private JLabel titleLabel;
    private FilterGUI filterGUI;

    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;

    public LocatorGUI() {
        super("BC Covid-19 Collection Centre Locator");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titleLabel = new JLabel(TITLE);
        add(titleLabel, BorderLayout.NORTH);

        filterGUI = new FilterGUI();
        add(filterGUI, BorderLayout.WEST);

        pack();
        setVisible(true);
    }
}




