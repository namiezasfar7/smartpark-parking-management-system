package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.smartpark.ui.components.RoundedPanelBorder;
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

        //ROOT PANEL
        setLayout(
                new BorderLayout()
        );

        setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //ADD DESIGNER PANEL
        add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        //SETUP DASHBOARD
        setupDashboard();
    }

    //SETUP DASHBOARD
    private void setupDashboard() {

        //MAIN DASHBOARD PANEL
        dashboardPanel.setLayout(
                new BorderLayout()
        );

        dashboardPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        dashboardPanel.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );

        //DASHBOARD TITLE
        dashboardLabel.setText(
                "Dashboard"
        );

        dashboardLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        dashboardLabel.setFont(
                UITheme.bold(28)
        );

        dashboardLabel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
        );

        //WORKSPACE
        workspacePanel.removeAll();

        workspacePanel.setLayout(
                new GridLayout(
                        2,
                        2,
                        18,
                        18
                )
        );

        workspacePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        workspacePanel.setBorder(
                new EmptyBorder(
                        5,
                        0,
                        0,
                        0
                )
        );

        //STYLE CARDS
        setupCard(
                spacesCard,
                spacesTitleLabel,
                spacesValueLabel,
                "Total Spaces",
                "25",
                UITheme.CARD_TOTAL
        );

        setupCard(
                occupiedCard,
                occupiedTitleLabel,
                occupiedValueLabel,
                "Occupied Spaces",
                "7",
                UITheme.CARD_OCCUPIED
        );

        setupCard(
                availableCard,
                availableTitleLabel,
                availableValueLabel,
                "Available Spaces",
                "18",
                UITheme.CARD_AVAILABLE
        );

        setupCard(
                sessionCard,
                sessionTitleLabel,
                sessionValueLabel,
                "Active Sessions",
                "7",
                UITheme.CARD_SESSIONS
        );

        //ADD CARDS
        workspacePanel.add(
                spacesCard
        );

        workspacePanel.add(
                occupiedCard
        );

        workspacePanel.add(
                availableCard
        );

        workspacePanel.add(
                sessionCard
        );

        //BUILD DASHBOARD
        dashboardPanel.removeAll();

        dashboardPanel.add(
                dashboardLabel,
                BorderLayout.NORTH
        );

        dashboardPanel.add(
                workspacePanel,
                BorderLayout.CENTER
        );

        dashboardPanel.revalidate();
        dashboardPanel.repaint();
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
        card.setOpaque(
                false
        );

        card.setLayout(
                new BorderLayout()
        );

        //ROUNDED CARD BACKGROUND
        card.setBorder(
                new RoundedPanelBorder(
                        cardColor,
                        24
                )
        );

        //TITLE
        titleLabel.setText(
                title
        );

        titleLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        titleLabel.setFont(
                UITheme.regular(15)
        );

        titleLabel.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        10,
                        24
                )
        );

        //VALUE
        valueLabel.setText(
                value
        );

        valueLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        valueLabel.setFont(
                UITheme.bold(32)
        );

        valueLabel.setBorder(
                new EmptyBorder(
                        10,
                        24,
                        22,
                        24
                )
        );

        //ADD COMPONENTS
        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.SOUTH
        );

        //CARD SIZE
        card.setPreferredSize(
                new Dimension(
                        300,
                        180
                )
        );
    }
}