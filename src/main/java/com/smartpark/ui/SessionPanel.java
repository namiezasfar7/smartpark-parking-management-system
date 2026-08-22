package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//SESSION PANEL
public class SessionPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel sessionPanel;
    private JLabel sessionLabel;
    private JPanel workspacePanel;
    private JLabel vehicleRegistrationLabel;
    private JTextField registrationTextField;
    private JLabel parkingSpaceLabel;
    private JComboBox parkingSpaceComboBox;
    private JLabel entryTimeLabel;
    private JTextField entryTimeTextField;
    private JButton startSessionButton;
    private JLabel activeSessionLabel;
    private JTable activeSessionTable;
    private JPanel activeSessionPanel;
    private JScrollPane activeSessionScrollPane;
    private JButton completeSessionButton;

    //DECLARE CONSTRUCTOR
    public SessionPanel() {
        setLayout(new BorderLayout());
        add(sessionPanel, BorderLayout.CENTER);
    }
}
