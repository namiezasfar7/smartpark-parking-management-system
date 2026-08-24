package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.ui.components.RoundedPanelBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    //CONTROLLER
    private DashboardController dashboardController;

    //DECLARE CONSTRUCTOR
    public DashboardPanel(DashboardController dashboardController) {

        this.dashboardController = dashboardController;

        //INITIALIZE COMPONENTS
        dashboardPanel = new JPanel();

        dashboardLabel = new JLabel();

        spacesCard = new JPanel();
        availableCard = new JPanel();
        occupiedCard = new JPanel();
        sessionCard = new JPanel();

        spacesTitleLabel = new JLabel();
        spacesValueLabel = new JLabel();

        availableTitleLabel = new JLabel();
        availableValueLabel = new JLabel();

        occupiedTitleLabel = new JLabel();
        occupiedValueLabel = new JLabel();

        sessionTitleLabel = new JLabel();
        sessionValueLabel = new JLabel();

        workspacePanel = new JPanel();

        //ROOT PANEL
        setLayout(new BorderLayout());

        setBackground(UITheme.BACKGROUND_COLOR);

        //ADD DASHBOARD PANEL
        add(dashboardPanel, BorderLayout.CENTER);

        //SETUP DASHBOARD
        setupDashboard();
    }

    //SETUP DASHBOARD
    private void setupDashboard() {

        //MAIN DASHBOARD PANEL
        dashboardPanel.setLayout(new BorderLayout());

        dashboardPanel.setBackground(UITheme.BACKGROUND_COLOR);

        dashboardPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        //DASHBOARD TITLE
        dashboardLabel.setText("Dashboard");

        dashboardLabel.setForeground(UITheme.TEXT_COLOR);

        dashboardLabel.setFont(UITheme.bold(28));

        dashboardLabel.setBorder(new EmptyBorder(0, 0, 25, 0));

        //WORKSPACE
        workspacePanel.removeAll();

        workspacePanel.setLayout(new GridLayout(2, 2, 24, 24));

        workspacePanel.setBackground(UITheme.BACKGROUND_COLOR);

        workspacePanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        //GET UPDATED DATA FROM CONTROLLER
        int totalSpaces = dashboardController.getTotalSpaces();
        int occupiedSpaces = dashboardController.getOccupiedSpaces();
        int availableSpaces = dashboardController.getAvailableSpaces();
        int activeSessions = dashboardController.getActiveSessions();

        //STYLE CARDS
        setupCard(
                spacesCard,
                spacesTitleLabel,
                spacesValueLabel,
                "Total Spaces",
                String.valueOf(totalSpaces),
                UITheme.CARD_TOTAL
        );

        setupCard(
                occupiedCard,
                occupiedTitleLabel,
                occupiedValueLabel,
                "Occupied Spaces",
                String.valueOf(occupiedSpaces),
                UITheme.CARD_OCCUPIED
        );

        setupCard(
                availableCard,
                availableTitleLabel,
                availableValueLabel,
                "Available Spaces",
                String.valueOf(availableSpaces),
                UITheme.CARD_AVAILABLE
        );

        setupCard(
                sessionCard,
                sessionTitleLabel,
                sessionValueLabel,
                "Active Sessions",
                String.valueOf(activeSessions),
                UITheme.CARD_SESSIONS
        );

        //ADD CARDS
        workspacePanel.add(spacesCard);
        workspacePanel.add(occupiedCard);
        workspacePanel.add(availableCard);
        workspacePanel.add(sessionCard);

        //BUILD DASHBOARD
        dashboardPanel.removeAll();

        dashboardPanel.add(dashboardLabel, BorderLayout.NORTH);

        dashboardPanel.add(workspacePanel, BorderLayout.CENTER);

        dashboardPanel.revalidate();
        dashboardPanel.repaint();
    }

    //REFRESH DASHBOARD
    public void refresh() {
        setupDashboard();
    }

    //SETUP CARD
    private void setupCard(
            JPanel card,
            JLabel titleLabel,
            JLabel valueLabel,
            String title,
            String value,
            Color cardColor
    ) {

        //CARD
        card.setOpaque(false);

        card.setLayout(new BorderLayout());

        //ROUNDED CARD BACKGROUND
        card.setBorder(new RoundedPanelBorder(cardColor, 24));

        //TITLE
        titleLabel.setText(title);
        titleLabel.setForeground(UITheme.TEXT_COLOR);
        titleLabel.setFont(UITheme.regular(15));

        titleLabel.setBorder(new EmptyBorder(28, 30, 14, 30));

        //VALUE
        valueLabel.setText(value);
        valueLabel.setForeground(UITheme.TEXT_COLOR);
        valueLabel.setFont(UITheme.bold(32));

        valueLabel.setBorder(new EmptyBorder(14, 30, 28, 30));

        //ADD COMPONENTS
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.SOUTH);

        //CARD SIZE
        card.setPreferredSize(new Dimension(320, 190));
    }
}