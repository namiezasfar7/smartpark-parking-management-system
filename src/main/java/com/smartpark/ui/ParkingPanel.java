package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.ParkingController;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.ui.components.RoundedPanelBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//PARKING PANEL CLASS
public class ParkingPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private final ParkingController parkingController;

    private JPanel parkingPanel;

    private JLabel parkingLabel;

    private JLabel zoneLabel;
    private JComboBox <String> zoneComboBox;

    private JPanel parkingGridPanel;
    private JPanel workspacePanel;

    private final Map <String, JPanel> spacePanels = new HashMap<>();

    //DECLARE CONSTRUCTOR
    public ParkingPanel(ParkingController parkingController) {

        this.parkingController = parkingController;

        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        setupParking();
    }

    //DECLARE METHODS
    //SETUP PARKING
    private void setupParking() {

        parkingPanel = new JPanel(new BorderLayout());
        parkingPanel.setBackground(UITheme.BACKGROUND_COLOR);
        parkingPanel.setBorder(new EmptyBorder(15, 18, 15, 18));

        //HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UITheme.BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 18, 0));

        parkingLabel = new JLabel("Parking");
        parkingLabel.setForeground(UITheme.TEXT_COLOR);
        parkingLabel.setFont(UITheme.bold(28));

        headerPanel.add(parkingLabel, BorderLayout.WEST);

        //ZONE CONTROL
        JPanel zonePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        zonePanel.setBackground(UITheme.BACKGROUND_COLOR);

        zoneLabel = new JLabel("Zone");
        zoneLabel.setForeground(UITheme.SECONDARY_TEXT_COLOR);
        zoneLabel.setFont(UITheme.regular(14));

        zoneComboBox = new JComboBox<>(new String[]{"All Zones"});
        zoneComboBox.setFont(UITheme.regular(14));
        zoneComboBox.setForeground(UITheme.TEXT_COLOR);
        zoneComboBox.setBackground(UITheme.BUTTON_COLOR);
        zoneComboBox.setFocusable(false);
        zoneComboBox.setPreferredSize(new Dimension(180, 38));

        zonePanel.add(zoneLabel);
        zonePanel.add(zoneComboBox);

        headerPanel.add(zonePanel, BorderLayout.EAST);

        //PARKING GRID
        parkingGridPanel = new JPanel(new BorderLayout());
        parkingGridPanel.setBackground(UITheme.BACKGROUND_COLOR);
        parkingGridPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        workspacePanel = new JPanel(new GridLayout(0, 4, 16, 16));
        workspacePanel.setBackground(UITheme.BACKGROUND_COLOR);

        parkingGridPanel.add(workspacePanel, BorderLayout.CENTER);

        JPanel legendPanel = createLegendPanel();

        //CHECK CONDITION
        if (legendPanel != null) {
            parkingGridPanel.add(legendPanel, BorderLayout.SOUTH);
        }

        //BUILD PAGE
        parkingPanel.add(headerPanel, BorderLayout.NORTH);
        parkingPanel.add(parkingGridPanel, BorderLayout.CENTER);

        add(parkingPanel, BorderLayout.CENTER);

        refreshParkingSpaces();
    }

    //REFRESH PARKING SPACES
    public void refreshParkingSpaces() {

        //CHECK CONDITION
        if (workspacePanel == null) {
            return;
        }

        workspacePanel.removeAll();

        spacePanels.clear();

        List <ParkingSpace> parkingSpaces = null;

        //CHECK CONDITION
        if (parkingController != null) {
            parkingSpaces = parkingController.getAllParkingSpaces();
        }

        //CHECK CONDITION
        if (parkingSpaces == null || parkingSpaces.isEmpty()) {

            workspacePanel.setLayout(new BorderLayout());

            JLabel emptyLabel = new JLabel("No parking spaces found.", SwingConstants.CENTER);

            emptyLabel.setForeground(UITheme.SECONDARY_TEXT_COLOR);

            emptyLabel.setFont(UITheme.bold(16));

            workspacePanel.add(emptyLabel, BorderLayout.CENTER);

        }
        else {

            workspacePanel.setLayout(new GridLayout(0, 4, 16, 16));

            //LOOP UNTIL CONDITION IS TRUE
            for (ParkingSpace parkingSpace : parkingSpaces) {

                //CHECK CONDITION
                if (parkingSpace == null) {
                    continue;
                }

                String spaceId = parkingSpace.getSpaceId();

                //CHECK CONDITION
                if (spaceId == null || spaceId.trim().isEmpty()) {
                    spaceId = "Unknown";
                }

                JPanel card = createParkingSpaceCard(spaceId);

                //CHECK CONDITION
                if (card == null) {
                    continue;
                }

                spacePanels.put(spaceId, card);

                updateSpaceCard(parkingSpace, card);

                workspacePanel.add(card);
            }
        }

        workspacePanel.revalidate();
        workspacePanel.repaint();

        parkingGridPanel.revalidate();
        parkingGridPanel.repaint();

        revalidate();
        repaint();
    }

    //CREATE PARKING SPACE CARD
    private JPanel createParkingSpaceCard(String spaceId) {

        JPanel card = new JPanel(new BorderLayout()) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                //ANTIALIASING
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                //ROUNDED BACKGROUND
                int arc = 18;

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        //TRANSPARENT PANEL
        card.setOpaque(false);

        //DEFAULT CARD COLOR
        card.setBackground(UITheme.PARKING_CARD_COLOR);

        //ROUNDED BORDER
        card.setBorder(new RoundedPanelBorder(UITheme.BORDER_COLOR, 18));

        //TOP
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(18, 20, 10, 20));

        //SPACE ID
        JLabel spaceLabel = new JLabel(spaceId);
        spaceLabel.setForeground(UITheme.TEXT_COLOR);
        spaceLabel.setFont(UITheme.bold(18));

        topPanel.add(spaceLabel, BorderLayout.WEST);

        //STATUS INDICATOR
        JPanel indicatorPanel = new JPanel();
        indicatorPanel.setOpaque(true);
        indicatorPanel.setPreferredSize(new Dimension(14, 14));
        indicatorPanel.setMinimumSize(new Dimension(14, 14));
        indicatorPanel.setMaximumSize(new Dimension(14, 14));

        topPanel.add(indicatorPanel, BorderLayout.EAST);

        //STATUS
        JPanel statusPanel = new JPanel(new BorderLayout());

        statusPanel.setOpaque(false);
        statusPanel.setBorder(new EmptyBorder(10, 20, 18, 20));

        JLabel statusLabel = new JLabel("UNKNOWN");
        statusLabel.setFont(UITheme.bold(14));
        statusPanel.add(statusLabel, BorderLayout.WEST);

        //ADD COMPONENTS
        card.add(topPanel, BorderLayout.NORTH);
        card.add(statusPanel, BorderLayout.SOUTH);

        //STORE COMPONENTS
        card.putClientProperty("statusLabel", statusLabel);
        card.putClientProperty("indicator", indicatorPanel);

        //CARD SIZE
        card.setPreferredSize(new Dimension(200, 150));

        return card;
    }

    //UPDATE SPACE CARD
    private void updateSpaceCard(ParkingSpace parkingSpace, JPanel card) {

        //CHECK CONDITION
        if (card == null) {
            return;
        }

        //CHECK CONDITION
        if (parkingSpace == null) {

            updateCardAppearance(card, "NOT FOUND", UITheme.SECONDARY_TEXT_COLOR);
            return;
        }

        ParkingSpaceStatus status = parkingSpace.getStatus();

        Color statusColor = getStatusColor(status);

        String statusText = status == null ? "UNKNOWN" : status.toString();

        updateCardAppearance(card, statusText, statusColor);
    }

    //UPDATE CARD APPEARANCE
    private void updateCardAppearance(JPanel card, String status, Color color) {

        //CHECK CONDITION
        if (card == null) {
            return;
        }

        JLabel statusLabel = (JLabel) card.getClientProperty("statusLabel");

        JPanel indicator = (JPanel) card.getClientProperty("indicator");

        //UPDATE STATUS TEXT
        if (statusLabel != null) {
            statusLabel.setText(status == null ? "UNKNOWN" : status);
            statusLabel.setForeground(color);
        }

        //UPDATE INDICATOR
        if (indicator != null) {
            indicator.setBackground(color);
        }

        //OCCUPIED APPEARANCE
        if ("OCCUPIED".equalsIgnoreCase(status)) {

            card.setBackground(new Color(95, 48, 55));
            card.setBorder(new RoundedPanelBorder(new Color(180, 65, 75), 18));

            //MAKE OCCUPIED TEXT VISIBLE
            if (statusLabel != null) {
                statusLabel.setForeground(new Color(255, 170, 175));
            }
        }
        else {
            card.setBackground(UITheme.PARKING_CARD_COLOR);
            card.setBorder(new RoundedPanelBorder(UITheme.BORDER_COLOR, 18));
        }

        card.repaint();
    }

    //STATUS COLOR
    private Color getStatusColor(ParkingSpaceStatus status) {

        //CHECK CONDITION
        if (status == null) {
            return UITheme.SECONDARY_TEXT_COLOR;
        }

        switch (status) {

            case AVAILABLE:
                return UITheme.PARKING_AVAILABLE;

            case OCCUPIED:
                return UITheme.PARKING_OCCUPIED;

            case OUT_OF_SERVICE:
                return UITheme.PARKING_MAINTENANCE;

            default:
                return UITheme.SECONDARY_TEXT_COLOR;
        }
    }

    //LEGEND
    private JPanel createLegendPanel() {

        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 8));

        legendPanel.setBackground(UITheme.BACKGROUND_COLOR);
        legendPanel.add(createLegendItem("Available", UITheme.PARKING_AVAILABLE));
        legendPanel.add(createLegendItem("Occupied", UITheme.PARKING_OCCUPIED));
        legendPanel.add(createLegendItem("Reserved", UITheme.PARKING_RESERVED));

        return legendPanel;
    }

    //LEGEND ITEM
    private JPanel createLegendItem(String text, Color color) {

        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 0));
        itemPanel.setBackground(UITheme.BACKGROUND_COLOR);

        JPanel indicator = new JPanel();
        indicator.setPreferredSize(new Dimension(10, 10));
        indicator.setBackground(color);

        JLabel label = new JLabel(text);
        label.setForeground(UITheme.SECONDARY_TEXT_COLOR);
        label.setFont(UITheme.regular(13));

        itemPanel.add(indicator);
        itemPanel.add(label);

        return itemPanel;
    }
}