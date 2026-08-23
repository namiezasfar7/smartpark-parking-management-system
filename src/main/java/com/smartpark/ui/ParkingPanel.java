package com.smartpark.ui;

//IMPORTS
import com.smartpark.ui.components.RoundedPanelBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//PARKING PANEL CLASS
public class ParkingPanel extends JPanel {

    //DECLARE ATTRIBUTES
    //MAIN PANEL
    private JPanel parkingPanel;

    //HEADER
    private JLabel parkingLabel;

    //ZONE CONTROLS
    private JLabel zoneLabel;
    private JComboBox zoneComboBox;

    //PARKING GRID
    private JPanel parkingGridPanel;

    //PARKING SPACES
    private JPanel space01;
    private JPanel space02;
    private JPanel space03;
    private JPanel space04;
    private JPanel space05;
    private JPanel space06;
    private JPanel space07;
    private JPanel space08;

    //SPACE LABELS
    private JLabel parkingSpace01Label;
    private JLabel parkingSpace02Label;
    private JLabel parkingSpace03Label;
    private JLabel parkingSpace04Label;
    private JLabel parkingSpace05Label;
    private JLabel parkingSpace06Label;
    private JLabel parkingSpace07Label;
    private JLabel parkingSpace08Label;

    //STATUS LABELS
    private JLabel parkingSpace01StatusLabel;
    private JLabel parkingSpace02StatusLabel;
    private JLabel parkingSpace03StatusLabel;
    private JLabel parkingSpace04StatusLabel;
    private JLabel parkingSpace05StatusLabel;
    private JLabel parkingSpace06StatusLabel;
    private JLabel parkingSpace07StatusLabel;
    private JLabel parkingSpace08StatusLabel;

    //WORKSPACE
    private JPanel workspacePanel;

    //DECLARE CONSTRUCTOR
    public ParkingPanel() {

        //ROOT PANEL
        setLayout(
                new BorderLayout()
        );

        setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //ADD DESIGNER PANEL
        add(
                parkingPanel,
                BorderLayout.CENTER
        );

        //SETUP PARKING
        setupParking();
    }

    //SETUP PARKING
    private void setupParking() {

        //MAIN PARKING PANEL
        parkingPanel.setLayout(
                new BorderLayout()
        );

        parkingPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        parkingPanel.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );

        //HEADER
        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        18,
                        0
                )
        );

        //PARKING TITLE
        parkingLabel.setText(
                "Parking"
        );

        parkingLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        parkingLabel.setFont(
                UITheme.bold(28)
        );


        headerPanel.add(
                parkingLabel,
                BorderLayout.WEST
        );

        //ZONE CONTROL
        JPanel zonePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        zonePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //ZONE LABEL
        zoneLabel.setText(
                "Zone"
        );

        zoneLabel.setForeground(
                UITheme.SECONDARY_TEXT_COLOR
        );

        zoneLabel.setFont(
                UITheme.regular(14)
        );

        //ZONE COMBO BOX
        zoneComboBox.setFont(
                UITheme.regular(14)
        );

        zoneComboBox.setForeground(
                UITheme.TEXT_COLOR
        );

        zoneComboBox.setBackground(
                UITheme.BUTTON_COLOR
        );

        zoneComboBox.setFocusable(false);

        zoneComboBox.setPreferredSize(
                new Dimension(
                        180,
                        38
                )
        );

        zonePanel.add(
                zoneLabel
        );

        zonePanel.add(
                zoneComboBox
        );

        headerPanel.add(
                zonePanel,
                BorderLayout.EAST
        );

        //PARKING GRID
        parkingGridPanel.setLayout(
                new BorderLayout()
        );

        parkingGridPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        parkingGridPanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        15,
                        0
                )
        );

        //GRID WORKSPACE
        workspacePanel.removeAll();

        workspacePanel.setLayout(
                new GridLayout(
                        2,
                        4,
                        16,
                        16
                )
        );

        workspacePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //STYLE PARKING SPACES
        setupParkingSpace(
                space01,
                parkingSpace01Label,
                parkingSpace01StatusLabel,
                "S01",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space02,
                parkingSpace02Label,
                parkingSpace02StatusLabel,
                "S02",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space03,
                parkingSpace03Label,
                parkingSpace03StatusLabel,
                "S03",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space04,
                parkingSpace04Label,
                parkingSpace04StatusLabel,
                "S04",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space05,
                parkingSpace05Label,
                parkingSpace05StatusLabel,
                "S05",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space06,
                parkingSpace06Label,
                parkingSpace06StatusLabel,
                "S06",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space07,
                parkingSpace07Label,
                parkingSpace07StatusLabel,
                "S07",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        setupParkingSpace(
                space08,
                parkingSpace08Label,
                parkingSpace08StatusLabel,
                "S08",
                "AVAILABLE",
                UITheme.PARKING_AVAILABLE
        );

        //ADD SPACES
        workspacePanel.add(
                space01
        );

        workspacePanel.add(
                space02
        );

        workspacePanel.add(
                space03
        );

        workspacePanel.add(
                space04
        );

        workspacePanel.add(
                space05
        );

        workspacePanel.add(
                space06
        );

        workspacePanel.add(
                space07
        );

        workspacePanel.add(
                space08
        );


        parkingGridPanel.add(
                workspacePanel,
                BorderLayout.CENTER
        );

        //LEGEND
        JPanel legendPanel =
                createLegendPanel();

        parkingGridPanel.add(
                legendPanel,
                BorderLayout.SOUTH
        );

        //BUILD PARKING PAGE
        parkingPanel.removeAll();

        parkingPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        parkingPanel.add(
                parkingGridPanel,
                BorderLayout.CENTER
        );


        parkingPanel.revalidate();
        parkingPanel.repaint();
    }

    //PARKING SPACE STYLE
    private void setupParkingSpace(
            JPanel space,
            JLabel spaceLabel,
            JLabel statusLabel,
            String spaceNumber,
            String status,
            Color statusColor
    ) {

        //CARD LAYOUT
        space.setLayout(
                new BorderLayout()
        );

        //CARD COLOR
        space.setBackground(
                UITheme.PARKING_CARD_COLOR
        );

        //ROUNDED BORDER
        space.setBorder(
                new RoundedPanelBorder(
                        UITheme.BORDER_COLOR,
                        18
                )
        );

        //TOP AREA
        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);

        topPanel.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        10,
                        18
                )
        );

        //SPACE NUMBER
        spaceLabel.setText(
                spaceNumber
        );

        spaceLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        spaceLabel.setFont(
                UITheme.bold(18)
        );

        topPanel.add(
                spaceLabel,
                BorderLayout.WEST
        );

        //STATUS INDICATOR
        JPanel indicatorPanel =
                new JPanel();

        indicatorPanel.setPreferredSize(
                new Dimension(
                        12,
                        12
                )
        );

        indicatorPanel.setBackground(
                statusColor
        );

        indicatorPanel.setOpaque(true);


        topPanel.add(
                indicatorPanel,
                BorderLayout.EAST
        );

        //STATUS AREA
        JPanel statusPanel =
                new JPanel(
                        new BorderLayout()
                );

        statusPanel.setOpaque(false);

        statusPanel.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        18,
                        18
                )
        );

        //STATUS
        statusLabel.setText(
                status
        );

        statusLabel.setForeground(
                statusColor
        );

        statusLabel.setFont(
                UITheme.bold(14)
        );

        statusPanel.add(
                statusLabel,
                BorderLayout.WEST
        );

        //ADD TO CARD
        space.add(
                topPanel,
                BorderLayout.NORTH
        );

        space.add(
                statusPanel,
                BorderLayout.SOUTH
        );

        //PREFERRED SIZE
        space.setPreferredSize(
                new Dimension(
                        200,
                        150
                )
        );
    }

    //LEGEND
    private JPanel createLegendPanel() {

        JPanel legendPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                20,
                                8
                        )
                );

        legendPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //AVAILABLE
        legendPanel.add(
                createLegendItem(
                        "Available",
                        UITheme.PARKING_AVAILABLE
                )
        );

        //OCCUPIED
        legendPanel.add(
                createLegendItem(
                        "Occupied",
                        UITheme.PARKING_OCCUPIED
                )
        );

        //RESERVED
        legendPanel.add(
                createLegendItem(
                        "Reserved",
                        UITheme.PARKING_RESERVED
                )
        );

        return legendPanel;
    }

    //LEGEND ITEM
    private JPanel createLegendItem(
            String text,
            Color color
    ) {

        JPanel itemPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                7,
                                0
                        )
                );

        itemPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //COLOR INDICATOR
        JPanel indicator =
                new JPanel();

        indicator.setPreferredSize(
                new Dimension(
                        10,
                        10
                )
        );

        indicator.setBackground(
                color
        );

        //TEXT
        JLabel label =
                new JLabel(
                        text
                );

        label.setForeground(
                UITheme.SECONDARY_TEXT_COLOR
        );

        label.setFont(
                UITheme.regular(13)
        );

        itemPanel.add(
                indicator
        );

        itemPanel.add(
                label
        );

        return itemPanel;
    }
}