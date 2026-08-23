package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.repository.VehicleRepository;
import com.smartpark.repository.memory.InMemoryParkingSessionRepository;
import com.smartpark.repository.memory.InMemoryParkingSpaceRepository;
import com.smartpark.repository.memory.InMemoryVehicleRepository;
import com.smartpark.service.AnalyticsService;
import com.smartpark.service.ParkingService;
import com.smartpark.service.ParkingSessionService;
import com.smartpark.service.VehicleService;
import com.smartpark.ui.components.RoundedButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//MAIN FRAME CLASS
public class MainFrame extends JFrame {

    //=========================================================
    // DECLARE ATTRIBUTES
    //=========================================================

    //MAIN PANELS
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel rightPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;

    //SIDEBAR COMPONENTS
    private JLabel logoLabel;

    private JButton dashboardButton;
    private JButton parkingButton;
    private JButton vehicleButton;
    private JButton sessionButton;
    private JButton analyticsButton;

    private JPanel buttonPanel;

    //APPLICATION PANELS
    private DashboardPanel dashboardPanel;
    private ParkingPanel parkingPanel;
    private VehiclePanel vehiclePanel;
    private SessionPanel sessionPanel;
    private AnalyticsPanel analyticsPanel;

    //CONTROLLERS
    private VehicleController vehicleController;
    private ParkingController parkingController;
    private ParkingSessionController parkingSessionController;
    private DashboardController dashboardController;


    //=========================================================
    // CONSTRUCTOR
    //=========================================================

    public MainFrame() {

        //FRAME SETTINGS
        setTitle(
                "SmartPark - Parking Management System"
        );

        setIconImage(
                Toolkit.getDefaultToolkit().getImage(
                        getClass().getResource(
                                "/icons/smartpark-logo.png"
                        )
                )
        );

        setSize(
                1200,
                750
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        //=====================================================
        // SET CONTENT PANE
        //=====================================================

        setContentPane(
                mainPanel
        );


        //=====================================================
        // SETUP MAIN LAYOUT
        //=====================================================

        setupMainLayout();


        //=====================================================
        // CREATE APPLICATION DEPENDENCIES
        //=====================================================

        setupControllers();


        //=====================================================
        // CREATE APPLICATION PANELS
        //=====================================================

        dashboardPanel =
                new DashboardPanel();

        parkingPanel =
                new ParkingPanel();

        vehiclePanel =
                new VehiclePanel(
                        vehicleController
                );

        sessionPanel =
                new SessionPanel();

        analyticsPanel =
                new AnalyticsPanel();


        //=====================================================
        // SETUP SIDEBAR
        //=====================================================

        setupSidebar();


        //=====================================================
        // SHOW DASHBOARD FIRST
        //=====================================================

        showPanel(
                dashboardPanel
        );


        //=====================================================
        // BUTTON ACTIONS
        //=====================================================

        dashboardButton.addActionListener(
                e -> {

                    showPanel(
                            dashboardPanel
                    );

                    setSelectedButton(
                            dashboardButton
                    );
                }
        );


        parkingButton.addActionListener(
                e -> {

                    showPanel(
                            parkingPanel
                    );

                    setSelectedButton(
                            parkingButton
                    );
                }
        );


        vehicleButton.addActionListener(
                e -> {

                    showPanel(
                            vehiclePanel
                    );

                    setSelectedButton(
                            vehicleButton
                    );
                }
        );


        sessionButton.addActionListener(
                e -> {

                    showPanel(
                            sessionPanel
                    );

                    setSelectedButton(
                            sessionButton
                    );
                }
        );


        analyticsButton.addActionListener(
                e -> {

                    showPanel(
                            analyticsPanel
                    );

                    setSelectedButton(
                            analyticsButton
                    );
                }
        );
    }


    //=========================================================
    // CREATE CONTROLLERS
    //=========================================================

    private void setupControllers() {

        //=====================================================
        // VEHICLE
        //=====================================================

        VehicleRepository vehicleRepository =
                new InMemoryVehicleRepository();

        VehicleService vehicleService =
                new VehicleService(
                        vehicleRepository
                );

        vehicleController =
                new VehicleController(
                        vehicleService
                );


        //=====================================================
        // PARKING SPACE
        //=====================================================

        ParkingSpaceRepository parkingSpaceRepository =
                new InMemoryParkingSpaceRepository();

        ParkingService parkingService =
                new ParkingService(
                        parkingSpaceRepository
                );

        parkingController =
                new ParkingController(
                        parkingService
                );


        //=====================================================
        // PARKING SESSION
        //=====================================================

        ParkingSessionRepository parkingSessionRepository =
                new InMemoryParkingSessionRepository();

        ParkingSessionService parkingSessionService =
                new ParkingSessionService(
                        parkingSessionRepository,
                        parkingSpaceRepository
                );

        parkingSessionController =
                new ParkingSessionController(
                        parkingSessionService
                );


        //=====================================================
        // DASHBOARD / ANALYTICS
        //=====================================================

        AnalyticsService analyticsService =
                new AnalyticsService(
                        parkingSpaceRepository,
                        parkingSessionRepository
                );

        dashboardController =
                new DashboardController(
                        analyticsService
                );
    }


    //=========================================================
    // CREATE CUSTOM COMPONENTS
    //=========================================================

    private void createUIComponents() {

        dashboardButton =
                new RoundedButton();

        parkingButton =
                new RoundedButton();

        vehicleButton =
                new RoundedButton();

        sessionButton =
                new RoundedButton();

        analyticsButton =
                new RoundedButton();
    }


    //=========================================================
    // MAIN LAYOUT
    //=========================================================

    private void setupMainLayout() {

        //MAIN PANEL
        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );


        //SIDEBAR
        sidebarPanel.setLayout(
                new BorderLayout()
        );

        sidebarPanel.setPreferredSize(
                new Dimension(
                        250,
                        0
                )
        );

        sidebarPanel.setBackground(
                UITheme.SIDEBAR_COLOR
        );


        //RIGHT SIDE
        rightPanel.setLayout(
                new BorderLayout()
        );

        rightPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );


        //HEADER
        headerPanel.setLayout(
                new BorderLayout()
        );

        headerPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        15,
                        30
                )
        );


        //CONTENT
        contentPanel.setLayout(
                new BorderLayout()
        );

        contentPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        10,
                        30,
                        30,
                        30
                )
        );


        //BUILD RIGHT SIDE
        rightPanel.removeAll();

        rightPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        rightPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        //BUILD MAIN FRAME
        mainPanel.removeAll();

        mainPanel.add(
                sidebarPanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                rightPanel,
                BorderLayout.CENTER
        );


        mainPanel.revalidate();
        mainPanel.repaint();
    }


    //=========================================================
    // SIDEBAR
    //=========================================================

    private void setupSidebar() {

        sidebarPanel.removeAll();


        //LOGO
        logoLabel.setText(
                "SmartPark"
        );

        logoLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        logoLabel.setFont(
                UITheme.bold(24)
        );

        logoLabel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );


        sidebarPanel.add(
                logoLabel,
                BorderLayout.NORTH
        );


        //BUTTON PANEL
        buttonPanel =
                new JPanel();

        buttonPanel.setLayout(
                new BoxLayout(
                        buttonPanel,
                        BoxLayout.Y_AXIS
                )
        );

        buttonPanel.setBackground(
                UITheme.SIDEBAR_COLOR
        );

        buttonPanel.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        20,
                        20
                )
        );


        //STYLE BUTTONS
        styleButton(
                dashboardButton
        );

        styleButton(
                parkingButton
        );

        styleButton(
                vehicleButton
        );

        styleButton(
                sessionButton
        );

        styleButton(
                analyticsButton
        );


        //ADD DASHBOARD
        buttonPanel.add(
                dashboardButton
        );

        buttonPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );


        //ADD PARKING
        buttonPanel.add(
                parkingButton
        );

        buttonPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );


        //ADD VEHICLES
        buttonPanel.add(
                vehicleButton
        );

        buttonPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );


        //ADD SESSIONS
        buttonPanel.add(
                sessionButton
        );

        buttonPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );


        //ADD ANALYTICS
        buttonPanel.add(
                analyticsButton
        );


        //ADD BUTTON PANEL
        sidebarPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        //DASHBOARD SELECTED INITIALLY
        setSelectedButton(
                dashboardButton
        );


        sidebarPanel.revalidate();
        sidebarPanel.repaint();
    }


    //=========================================================
    // BUTTON STYLE
    //=========================================================

    private void styleButton(
            JButton button
    ) {

        button.setForeground(
                UITheme.TEXT_COLOR
        );

        button.setFont(
                UITheme.regular(15)
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        button.setPreferredSize(
                new Dimension(
                        210,
                        48
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );
    }


    //=========================================================
    // SELECTED BUTTON
    //=========================================================

    private void setSelectedButton(
            JButton selectedButton
    ) {

        JButton[] buttons = {
                dashboardButton,
                parkingButton,
                vehicleButton,
                sessionButton,
                analyticsButton
        };


        for (
                JButton button :
                buttons
        ) {

            if (
                    button
                            instanceof RoundedButton
            ) {

                (
                        (RoundedButton)
                                button
                ).setButtonColor(
                        UITheme.BUTTON_COLOR
                );
            }
        }


        if (
                selectedButton
                        instanceof RoundedButton
        ) {

            (
                    (RoundedButton)
                            selectedButton
            ).setButtonColor(
                    UITheme.BUTTON_SELECTED_COLOR
            );
        }


        selectedButton.repaint();
    }


    //=========================================================
    // SHOW PANEL
    //=========================================================

    private void showPanel(
            JPanel panel
    ) {

        contentPanel.removeAll();

        contentPanel.setLayout(
                new BorderLayout()
        );

        contentPanel.add(
                panel,
                BorderLayout.CENTER
        );

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}