package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//PARKING PANEL CLASS
public class ParkingPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel parkingPanel;
    private JLabel parkingLabel;
    private JLabel zoneLabel;
    private JComboBox zoneComboBox;
    private JPanel parkingGridPanel;
    private JPanel space01;
    private JPanel space02;
    private JPanel space04;
    private JPanel space03;
    private JPanel space05;
    private JPanel space06;
    private JPanel space07;
    private JPanel space08;
    private JLabel parkingSpace01Label;
    private JLabel parkingSpace02Label;
    private JLabel parkingSpace03Label;
    private JLabel parkingSpace04Label;
    private JLabel parkingSpace05Label;
    private JLabel parkingSpace06Label;
    private JLabel parkingSpace07Label;
    private JLabel parkingSpace08Label;
    private JLabel parkingSpace01StatusLabel;
    private JLabel parkingSpace02StatusLabel;
    private JLabel parkingSpace03StatusLabel;
    private JLabel parkingSpace04StatusLabel;
    private JLabel parkingSpace05StatusLabel;
    private JLabel parkingSpace06StatusLabel;
    private JLabel parkingSpace07StatusLabel;
    private JLabel parkingSpace08StatusLabel;
    private JPanel workspacePanel;

    //DECLARE CONSTRUCTOR
    public ParkingPanel() {
        setLayout(new BorderLayout());
        add(parkingPanel, BorderLayout.CENTER);
    }
}
