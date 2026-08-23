package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
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

        //ROOT PANEL
        setLayout(
                new BorderLayout()
        );

        setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //ADD DESIGNER PANEL
        add(
                vehiclePanel,
                BorderLayout.CENTER
        );

        //SETUP VEHICLE PANEL
        setupVehiclePanel();
    }


    //SETUP VEHICLE PANEL
    private void setupVehiclePanel() {

        //MAIN VEHICLE PANEL
        vehiclePanel.setLayout(
                new BorderLayout()
        );

        vehiclePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        vehiclePanel.setBorder(
                new EmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );


        //VEHICLE TITLE
        vehicleLabel.setText(
                "Vehicle Management"
        );

        vehicleLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        vehicleLabel.setFont(
                UITheme.bold(28)
        );

        vehicleLabel.setBorder(
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
                new BorderLayout(
                        0,
                        18
                )
        );

        workspacePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );


        //SETUP FORM
        setupVehicleForm();


        //SETUP VEHICLE LIST
        setupVehicleList();


        //BUILD MAIN PANEL
        vehiclePanel.removeAll();

        vehiclePanel.add(
                vehicleLabel,
                BorderLayout.NORTH
        );

        vehiclePanel.add(
                workspacePanel,
                BorderLayout.CENTER
        );


        vehiclePanel.revalidate();
        vehiclePanel.repaint();
    }


    //SETUP VEHICLE FORM
    private void setupVehicleForm() {

        //FORM PANEL
        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new GridBagLayout()
        );

        formPanel.setBackground(
                UITheme.CARD_COLOR
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                UITheme.BORDER_COLOR,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                20,
                                22,
                                20,
                                22
                        )
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        7,
                        7,
                        7
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        //REGISTRATION NUMBER LABEL
        registrationNumberLabel.setText(
                "Registration Number"
        );

        registrationNumberLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        registrationNumberLabel.setFont(
                UITheme.regular(15)
        );


        //REGISTRATION NUMBER FIELD
        registrationNumberField.setBackground(
                UITheme.BUTTON_COLOR
        );

        registrationNumberField.setForeground(
                UITheme.TEXT_COLOR
        );

        registrationNumberField.setCaretColor(
                UITheme.TEXT_COLOR
        );

        registrationNumberField.setFont(
                UITheme.regular(15)
        );

        registrationNumberField.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                UITheme.BORDER_COLOR,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );


        //VEHICLE TYPE LABEL
        vehicleTypeLabel.setText(
                "Vehicle Type"
        );

        vehicleTypeLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        vehicleTypeLabel.setFont(
                UITheme.regular(15)
        );


        //VEHICLE TYPE COMBO BOX
        vehicleTypeComboBox.setBackground(
                UITheme.BUTTON_COLOR
        );

        vehicleTypeComboBox.setForeground(
                UITheme.TEXT_COLOR
        );

        vehicleTypeComboBox.setFont(
                UITheme.regular(15)
        );

        vehicleTypeComboBox.setBorder(
                new LineBorder(
                        UITheme.BORDER_COLOR,
                        1,
                        true
                )
        );


        //REGISTER BUTTON
        registerVehicleButton.setText(
                "Register Vehicle"
        );

        registerVehicleButton.setBackground(
                UITheme.BUTTON_SELECTED_COLOR
        );

        registerVehicleButton.setForeground(
                UITheme.TEXT_COLOR
        );

        registerVehicleButton.setFont(
                UITheme.bold(14)
        );

        registerVehicleButton.setFocusPainted(
                false
        );

        registerVehicleButton.setBorderPainted(
                false
        );

        registerVehicleButton.setOpaque(
                true
        );

        registerVehicleButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        registerVehicleButton.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );


        //ROW 1 - REGISTRATION NUMBER
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0;

        formPanel.add(
                registrationNumberLabel,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1.0;

        formPanel.add(
                registrationNumberField,
                gbc
        );


        //ROW 2 - VEHICLE TYPE
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.weightx = 0;

        formPanel.add(
                vehicleTypeLabel,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1.0;

        formPanel.add(
                vehicleTypeComboBox,
                gbc
        );


        //ROW 3 - BUTTON
        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.weightx = 1.0;

        formPanel.add(
                registerVehicleButton,
                gbc
        );


        //ADD FORM
        workspacePanel.add(
                formPanel,
                BorderLayout.NORTH
        );
    }


    //SETUP VEHICLE LIST
    private void setupVehicleList() {

        //LIST PANEL
        vehicleListPanel.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        vehicleListPanel.setBackground(
                UITheme.CARD_COLOR
        );

        vehicleListPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                UITheme.BORDER_COLOR,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );


        //LIST TITLE
        listRegisterdVehicleLabel.setText(
                "Registered Vehicles"
        );

        listRegisterdVehicleLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        listRegisterdVehicleLabel.setFont(
                UITheme.bold(17)
        );


        //TABLE
        vehicleListTable.setBackground(
                UITheme.CARD_COLOR
        );

        vehicleListTable.setForeground(
                UITheme.TEXT_COLOR
        );

        vehicleListTable.setFont(
                UITheme.regular(14)
        );

        vehicleListTable.setRowHeight(
                38
        );

        vehicleListTable.setGridColor(
                UITheme.BORDER_COLOR
        );

        vehicleListTable.setSelectionBackground(
                UITheme.BUTTON_COLOR
        );

        vehicleListTable.setSelectionForeground(
                UITheme.TEXT_COLOR
        );

        vehicleListTable.setShowVerticalLines(
                false
        );

        vehicleListTable.setShowHorizontalLines(
                true
        );


        //TABLE HEADER
        JTableHeader tableHeader =
                vehicleListTable.getTableHeader();

        tableHeader.setBackground(
                UITheme.BUTTON_COLOR
        );

        tableHeader.setForeground(
                UITheme.TEXT_COLOR
        );

        tableHeader.setFont(
                UITheme.bold(14)
        );

        tableHeader.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );


        //SCROLL PANE
        vehicleScrollPane.setBackground(
                UITheme.CARD_COLOR
        );

        vehicleScrollPane.getViewport().setBackground(
                UITheme.CARD_COLOR
        );

        vehicleScrollPane.setBorder(
                new LineBorder(
                        UITheme.BORDER_COLOR,
                        1,
                        true
                )
        );


        //ADD COMPONENTS
        vehicleListPanel.add(
                listRegisterdVehicleLabel,
                BorderLayout.NORTH
        );

        vehicleListPanel.add(
                vehicleScrollPane,
                BorderLayout.CENTER
        );


        //ADD LIST TO WORKSPACE
        workspacePanel.add(
                vehicleListPanel,
                BorderLayout.CENTER
        );
    }
}