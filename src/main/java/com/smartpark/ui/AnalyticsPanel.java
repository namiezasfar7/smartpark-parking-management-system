package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//ANALYTICS PANEL CLASS
public class AnalyticsPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel analyticsPanel;
    private JLabel analyticsLabel;
    private JPanel workspacePanel;
    private JLabel totalParkingSessionLabel;
    private JLabel completedParkingSessionLabel;
    private JLabel currentlyActiveParkingSessionLabel;
    private JLabel averageParkingDurationLabel;
    private JTable statisticsTable;
    private JScrollPane statisticsScrollPane;

    //DECLARE CONSTRUCTOR
    public AnalyticsPanel() {
        setLayout(new BorderLayout());
        add(analyticsPanel, BorderLayout.CENTER);
    }
}
