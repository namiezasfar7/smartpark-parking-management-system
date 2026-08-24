package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;
import com.smartpark.service.AnalyticsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


//MAIN FRAME CLASS
public class MainFrame extends JFrame {

    //DECLARE ATTRIBUTES
    //CONTROLLERS / SERVICES
    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;
    private final DashboardController dashboardController;
    private final AnalyticsService analyticsService;

    //MAIN PANELS
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;

    //CARD LAYOUT
    private CardLayout cardLayout;

    //PAGE PANELS
    private DashboardPanel dashboardPanel;
    private ParkingPanel parkingPanel;
    private VehiclePanel vehiclePanel;
    private SessionPanel sessionPanel;
    private AnalyticsPanel analyticsPanel;

    //NAVIGATION BUTTONS
    private JButton dashboardButton;
    private JButton parkingButton;
    private JButton vehiclesButton;
    private JButton sessionsButton;
    private JButton analyticsButton;

    //PAGE NAMES
    private static final String DASHBOARD_PAGE = "DASHBOARD";
    private static final String PARKING_PAGE = "PARKING";
    private static final String VEHICLES_PAGE = "VEHICLES";
    private static final String SESSIONS_PAGE = "SESSIONS";
    private static final String ANALYTICS_PAGE = "ANALYTICS";

    //DECLARE CONSTRUCTOR
    public MainFrame(
            VehicleController vehicleController,
            ParkingController parkingController,
            ParkingSessionController parkingSessionController,
            DashboardController dashboardController,
            AnalyticsService analyticsService
    ) {

        this.vehicleController = vehicleController;
        this.parkingController = parkingController;
        this.parkingSessionController = parkingSessionController;
        this.dashboardController = dashboardController;
        this.analyticsService = analyticsService;

        //FRAME SETTINGS
        setTitle("SmartPark - Parking Management System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //STARTUP WINDOW SIZE
        setSize(1200, 750);

        setMinimumSize(new Dimension(1200, 750));

        //CENTER WINDOW ON SCREEN
        setLocationRelativeTo(null);

        setBackground(UITheme.BACKGROUND_COLOR);

        //CREATE UI
        setupMainFrame();
    }

    //DECLARE METHODS
    //SETUP MAIN FRAME
    private void setupMainFrame() {

        //MAIN PANEL
        mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(UITheme.BACKGROUND_COLOR);

        //SIDEBAR
        sidebarPanel = createSidebar();

        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        //CONTENT AREA
        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        contentPanel.setBackground(UITheme.BACKGROUND_COLOR);

        //CREATE PAGE PANELS
        createPagePanels();

        //ADD PAGE PANELS
        contentPanel.add(dashboardPanel, DASHBOARD_PAGE);
        contentPanel.add(parkingPanel, PARKING_PAGE);
        contentPanel.add(vehiclePanel, VEHICLES_PAGE);
        contentPanel.add(sessionPanel, SESSIONS_PAGE);
        contentPanel.add(analyticsPanel, ANALYTICS_PAGE);

        //ADD CONTENT
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        //SET CONTENT PANE
        setContentPane(mainPanel);

        //SHOW DEFAULT PAGE
        showPage(DASHBOARD_PAGE);
    }

    //CREATE PAGE PANELS
    private void createPagePanels() {

        //DASHBOARD
        dashboardPanel = new DashboardPanel(dashboardController);

        //PARKING
        parkingPanel = new ParkingPanel(parkingController);

        //VEHICLES
        vehiclePanel = new VehiclePanel(vehicleController);

        //SESSIONS
        sessionPanel = new SessionPanel(
                vehicleController,
                parkingController,
                parkingSessionController
        );

        //ANALYTICS
        analyticsPanel = new AnalyticsPanel(analyticsService);
    }

    //CREATE SIDEBAR
    private JPanel createSidebar() {

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setBackground(UITheme.SIDEBAR_COLOR);

        sidebar.setPreferredSize(new Dimension(315, 0));

        //SIDEBAR CONTENT
        JPanel sidebarContent = new JPanel();

        sidebarContent.setLayout(
                new BoxLayout(sidebarContent, BoxLayout.Y_AXIS)
        );

        sidebarContent.setBackground(UITheme.SIDEBAR_COLOR);

        sidebarContent.setBorder(
                new EmptyBorder(35, 29, 25, 29)
        );

        //LOGO
        JLabel logoLabel = new JLabel("SmartPark");

        logoLabel.setForeground(UITheme.TEXT_COLOR);

        logoLabel.setFont(UITheme.bold(30));

        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarContent.add(logoLabel);

        sidebarContent.add(
                Box.createRigidArea(new Dimension(0, 50))
        );

        //CREATE NAVIGATION BUTTONS
        dashboardButton = createNavigationButton("Dashboard");
        parkingButton = createNavigationButton("Parking");
        vehiclesButton = createNavigationButton("Vehicles");
        sessionsButton = createNavigationButton("Sessions");
        analyticsButton = createNavigationButton("Analytics");

        //ACTION LISTENERS
        dashboardButton.addActionListener(e -> showPage(DASHBOARD_PAGE));
        parkingButton.addActionListener(e -> showPage(PARKING_PAGE));
        vehiclesButton.addActionListener(e -> showPage(VEHICLES_PAGE));
        sessionsButton.addActionListener(e -> showPage(SESSIONS_PAGE));
        analyticsButton.addActionListener(e -> showPage(ANALYTICS_PAGE));

        //ADD NAVIGATION BUTTONS
        sidebarContent.add(dashboardButton);

        sidebarContent.add(
                Box.createRigidArea(new Dimension(0, 14))
        );

        sidebarContent.add(parkingButton);

        sidebarContent.add(
                Box.createRigidArea(new Dimension(0, 14))
        );

        sidebarContent.add(vehiclesButton);

        sidebarContent.add(
                Box.createRigidArea(new Dimension(0, 14))
        );

        sidebarContent.add(sessionsButton);

        sidebarContent.add(
                Box.createRigidArea(new Dimension(0, 14))
        );

        sidebarContent.add(analyticsButton);

        //ADD SIDEBAR CONTENT
        sidebar.add(
                sidebarContent,
                BorderLayout.NORTH
        );

        return sidebar;
    }

    //CREATE ROUNDED NAVIGATION BUTTON
    private JButton createNavigationButton(String text) {

        RoundedNavigationButton button =
                new RoundedNavigationButton(text);

        button.setFont(UITheme.regular(16));

        button.setForeground(UITheme.TEXT_COLOR);

        button.setBackground(UITheme.BUTTON_COLOR);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setPreferredSize(
                new Dimension(260, 58)
        );

        button.setMinimumSize(
                new Dimension(260, 58)
        );

        button.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 58)
        );

        //SET CURSOR
        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    //SHOW PAGE
    private void showPage(String page) {

        //REFRESH DASHBOARD
        if (DASHBOARD_PAGE.equals(page)) {

            if (dashboardPanel != null) {
                dashboardPanel.refresh();
            }
        }

        //REFRESH PARKING
        if (PARKING_PAGE.equals(page)) {

            if (parkingPanel != null) {
                parkingPanel.refreshParkingSpaces();
            }
        }

        //REFRESH SESSIONS
        if (SESSIONS_PAGE.equals(page)) {

            if (sessionPanel != null) {
                sessionPanel.refresh();
            }
        }

        //REFRESH ANALYTICS
        if (ANALYTICS_PAGE.equals(page)) {

            if (analyticsPanel != null) {
                analyticsPanel.refresh();
            }
        }

        //SHOW PAGE
        cardLayout.show(contentPanel, page);

        //RESET BUTTONS
        resetNavigationButtons();

        //SELECT ACTIVE BUTTON
        switch (page) {

            case DASHBOARD_PAGE:

                setSelectedButton(dashboardButton);
                break;

            case PARKING_PAGE:

                setSelectedButton(parkingButton);
                break;

            case VEHICLES_PAGE:

                setSelectedButton(vehiclesButton);
                break;

            case SESSIONS_PAGE:

                setSelectedButton(sessionsButton);
                break;

            case ANALYTICS_PAGE:

                setSelectedButton(analyticsButton);
                break;

            default:

                break;
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    //RESET NAVIGATION BUTTONS
    private void resetNavigationButtons() {

        setNormalButton(dashboardButton);

        setNormalButton(parkingButton);

        setNormalButton(vehiclesButton);

        setNormalButton(sessionsButton);

        setNormalButton(analyticsButton);
    }

    //NORMAL BUTTON
    private void setNormalButton(JButton button) {

        //CHECK CONDITION
        if (button == null) {
            return;
        }

        button.setBackground(UITheme.BUTTON_COLOR);

        button.setForeground(UITheme.TEXT_COLOR);

        button.repaint();
    }

    //SELECTED BUTTON
    private void setSelectedButton(JButton button) {

        //CHECK CONDITION
        if (button == null) {
            return;
        }

        button.setBackground(
                UITheme.BUTTON_SELECTED_COLOR
        );

        button.setForeground(UITheme.TEXT_COLOR);

        button.repaint();
    }

    //REFRESH ALL
    public void refreshAll() {

        //REFRESH PARKING
        if (parkingPanel != null) {
            parkingPanel.refreshParkingSpaces();
        }

        //REFRESH SESSIONS
        if (sessionPanel != null) {
            sessionPanel.refresh();
        }

        //REFRESH DASHBOARD
        if (dashboardPanel != null) {
            dashboardPanel.refresh();
        }

        //REFRESH ANALYTICS
        if (analyticsPanel != null) {
            analyticsPanel.revalidate();

            analyticsPanel.repaint();
        }

        revalidate();

        repaint();
    }

    //ROUNDED NAVIGATION BUTTON
    private static class RoundedNavigationButton extends JButton {

        //DECLARE ATTRIBUTE
        private boolean mouseOver = false;

        //DECLARE CONSTRUCTOR
        public RoundedNavigationButton(String text) {

            super(text);

            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setOpaque(false);

            addMouseListener(
                    new java.awt.event.MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                java.awt.event.MouseEvent e
                        ) {

                            mouseOver = true;

                            repaint();
                        }

                        @Override
                        public void mouseExited(
                                java.awt.event.MouseEvent e
                        ) {

                            mouseOver = false;

                            repaint();
                        }
                    }
            );
        }

        //DECLARE METHODS
        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            //ANTIALIASING
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
            );

            //DIMENSIONS
            int width = getWidth();

            int height = getHeight();

            //CHECK CONDITION
            if (width <= 0 || height <= 0) {

                g2.dispose();

                return;
            }

            //DECLARE ATTRIBUTE
            int arc = 18;

            //DETERMINE BACKGROUND
            Color backgroundColor = getBackground();

            //CHECK CONDITION
            if (mouseOver) {

                backgroundColor =
                        UITheme.BUTTON_SELECTED_COLOR;
            }

            //ROUNDED BACKGROUND
            g2.setColor(backgroundColor);

            g2.fillRoundRect(
                    0,
                    0,
                    width - 1,
                    height - 1,
                    arc,
                    arc
            );

            //ROUNDED BORDER
            g2.setColor(UITheme.BORDER_COLOR);

            g2.setStroke(
                    new BasicStroke(1.0f)
            );

            g2.drawRoundRect(
                    0,
                    0,
                    width - 1,
                    height - 1,
                    arc,
                    arc
            );

            g2.dispose();

            //DRAW TEXT
            super.paintComponent(g);
        }
    }
}