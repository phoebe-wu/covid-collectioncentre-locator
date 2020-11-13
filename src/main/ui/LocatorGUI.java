package ui;

import javax.swing.*;
import java.awt.*;

public class LocatorGUI extends JFrame {
    private static final String TITLE = "Find a Covid-19 Testing Centre Near You";
    private JLabel titleLabel;
    private ResultsGUI resultsArea;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;

    public LocatorGUI() {
        super("BC Covid-19 Collection Centre Locator");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titleLabel = new JLabel(TITLE);
        add(titleLabel, BorderLayout.NORTH);

        resultsArea = new ResultsGUI();
        add(resultsArea, BorderLayout.WEST);

        pack();
        setVisible(true);


    }
}




