package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//DASHBOARD PANEL CLASS
public class DashboardPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel dashboardPanel;
    private JLabel dashboardLabel;
    private JPanel spacesCard;
    private JPanel availableCard;
    private JPanel occupiedCard;
    private JPanel sessionCard;
    private JLabel spacesTitleLabel;
    private JLabel spacesValueLabel;
    private JLabel availableTitleLabel;
    private JLabel availableValueLabel;
    private JLabel occupiedTitleLabel;
    private JLabel occupiedValueLabel;
    private JLabel sessionTitleLabel;
    private JLabel sessionValueLabel;
    private JPanel workspacePanel;

    //DECLARE CONSTRUCTOR
    public DashboardPanel() {
        setLayout(new BorderLayout());
        add(dashboardPanel, BorderLayout.CENTER);
    }
}
