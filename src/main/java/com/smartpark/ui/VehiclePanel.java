package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//VEHICLE PANEL
public class VehiclePanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel vehiclePanel;
    private JLabel vehicleLabel;
    private JPanel workspacePanel;
    private JLabel registrationNumberLabel;
    private JTextField registrationNumberField;
    private JLabel vehicleTypeLabel;
    private JComboBox vehicleTypeComboBox;
    private JButton registerVehicleButton;
    private JPanel vehicleListPanel;
    private JLabel listRegisterdVehicleLabel;
    private JTable vehicleListTable;
    private JScrollPane vehicleScrollPane;

    //DECLARE CONSTRUCTOR
    public VehiclePanel() {
        setLayout(new BorderLayout());
        add(vehiclePanel, BorderLayout.CENTER);
    }
}
