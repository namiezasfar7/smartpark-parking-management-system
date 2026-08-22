package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.BorderLayout;

//MAIN FRAME CLASS
public class MainFrame extends JFrame {

    //DECLARE ATTRIBUTES
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JLabel logoLabel;
    private JButton dashboardButton;
    private JButton parkingButton;
    private JButton vehicleButton;
    private JButton sessionButton;
    private JButton analyticsButton;

    //DECLARE CONSTRUCTOR
    public MainFrame() {

        //INITIALIZE
        setTitle("SmartPark - Parking Management System");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);

        //CREATE PANELS
        DashboardPanel dashboardPanel = new DashboardPanel();
        ParkingPanel parkingPanel = new ParkingPanel();
        VehiclePanel vehiclePanel = new VehiclePanel();
        SessionPanel sessionPanel = new SessionPanel();
        AnalyticsPanel analyticsPanel = new AnalyticsPanel();

        //SHOW DASHBOARD
        showPanel(dashboardPanel);

        //BUTTON ACTIONS
        dashboardButton.addActionListener(e -> showPanel(dashboardPanel));
        parkingButton.addActionListener(e -> showPanel(parkingPanel));
        vehicleButton.addActionListener(e -> showPanel(vehiclePanel));
        sessionButton.addActionListener(e -> showPanel(sessionPanel));
        analyticsButton.addActionListener(e -> showPanel(analyticsPanel));
    }

    //DECLARE METHODS
    //SHOW PANEL
    private void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}