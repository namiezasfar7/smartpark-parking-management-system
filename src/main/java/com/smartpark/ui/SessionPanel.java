package com.smartpark.ui;

//IMPORTS
import javax.swing.*;
import java.awt.*;

//SESSION PANEL
public class SessionPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel sessionPanel;
    private JLabel sessionLabel;

    private JPanel workspacePanel;

    private JLabel vehicleRegistrationLabel;
    private JTextField registrationTextField;

    private JLabel parkingSpaceLabel;
    private JComboBox parkingSpaceComboBox;

    private JLabel entryTimeLabel;
    private JTextField entryTimeTextField;

    private JButton startSessionButton;

    private JLabel activeSessionLabel;
    private JTable activeSessionTable;

    private JPanel activeSessionPanel;
    private JScrollPane activeSessionScrollPane;

    private JButton completeSessionButton;


    //DECLARE CONSTRUCTOR
    public SessionPanel() {

        //ROOT PANEL
        setLayout(
                new BorderLayout()
        );

        setBackground(
                UITheme.BACKGROUND_COLOR
        );

        //ADD SESSION PANEL
        add(
                sessionPanel,
                BorderLayout.CENTER
        );

        //SETUP SESSION PANEL
        setupSessionPanel();
    }


    //SETUP SESSION PANEL
    private void setupSessionPanel() {

        //MAIN SESSION PANEL
        sessionPanel.setLayout(
                new BorderLayout()
        );

        sessionPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );


        //SESSION TITLE
        sessionLabel.setText(
                "Session Management"
        );

        sessionLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        sessionLabel.setFont(
                UITheme.bold(28)
        );


        //WORKSPACE
        workspacePanel.setLayout(
                new BorderLayout(
                        0,
                        20
                )
        );

        workspacePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );


        //SETUP SESSION FORM
        setupSessionForm();


        //SETUP ACTIVE SESSION
        setupActiveSession();


        //BUILD MAIN PANEL
        sessionPanel.removeAll();

        sessionPanel.add(
                sessionLabel,
                BorderLayout.NORTH
        );

        sessionPanel.add(
                workspacePanel,
                BorderLayout.CENTER
        );


        sessionPanel.revalidate();
        sessionPanel.repaint();
    }


    //SETUP SESSION FORM
    private void setupSessionForm() {

        //FORM PANEL
        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                UITheme.CARD_COLOR
        );

        formPanel.setBorder(
                BorderFactory.createLineBorder(
                        UITheme.BORDER_COLOR,
                        1
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        20,
                        10,
                        20
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        //VEHICLE REGISTRATION LABEL
        vehicleRegistrationLabel.setText(
                "Vehicle Registration"
        );

        vehicleRegistrationLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        vehicleRegistrationLabel.setFont(
                UITheme.regular(15)
        );


        //REGISTRATION FIELD
        registrationTextField.setBackground(
                UITheme.BUTTON_COLOR
        );

        registrationTextField.setForeground(
                UITheme.TEXT_COLOR
        );

        registrationTextField.setCaretColor(
                UITheme.TEXT_COLOR
        );

        registrationTextField.setFont(
                UITheme.regular(15)
        );

        registrationTextField.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );


        //PARKING SPACE LABEL
        parkingSpaceLabel.setText(
                "Parking Space"
        );

        parkingSpaceLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        parkingSpaceLabel.setFont(
                UITheme.regular(15)
        );


        //PARKING SPACE COMBO BOX
        parkingSpaceComboBox.setBackground(
                UITheme.BUTTON_COLOR
        );

        parkingSpaceComboBox.setForeground(
                UITheme.TEXT_COLOR
        );

        parkingSpaceComboBox.setFont(
                UITheme.regular(15)
        );


        //ENTRY TIME LABEL
        entryTimeLabel.setText(
                "Entry Time"
        );

        entryTimeLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        entryTimeLabel.setFont(
                UITheme.regular(15)
        );


        //ENTRY TIME FIELD
        entryTimeTextField.setBackground(
                UITheme.BUTTON_COLOR
        );

        entryTimeTextField.setForeground(
                UITheme.TEXT_COLOR
        );

        entryTimeTextField.setCaretColor(
                UITheme.TEXT_COLOR
        );

        entryTimeTextField.setFont(
                UITheme.regular(15)
        );

        entryTimeTextField.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );


        //START SESSION BUTTON
        startSessionButton.setText(
                "Start Session"
        );

        startSessionButton.setBackground(
                UITheme.BUTTON_SELECTED_COLOR
        );

        startSessionButton.setForeground(
                UITheme.TEXT_COLOR
        );

        startSessionButton.setFont(
                UITheme.bold(15)
        );

        startSessionButton.setFocusPainted(
                false
        );

        startSessionButton.setBorderPainted(
                false
        );

        startSessionButton.setOpaque(
                true
        );

        //HAND CURSOR
        startSessionButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        //ROW 1
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0;

        formPanel.add(
                vehicleRegistrationLabel,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1.0;

        formPanel.add(
                registrationTextField,
                gbc
        );


        //ROW 2
        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.weightx = 0;

        formPanel.add(
                parkingSpaceLabel,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1.0;

        formPanel.add(
                parkingSpaceComboBox,
                gbc
        );


        //ROW 3
        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.weightx = 0;

        formPanel.add(
                entryTimeLabel,
                gbc
        );


        gbc.gridx = 1;

        gbc.weightx = 1.0;

        formPanel.add(
                entryTimeTextField,
                gbc
        );


        //ROW 4
        gbc.gridx = 1;
        gbc.gridy = 3;

        gbc.weightx = 1.0;

        formPanel.add(
                startSessionButton,
                gbc
        );


        //ADD FORM
        workspacePanel.add(
                formPanel,
                BorderLayout.NORTH
        );
    }


    //SETUP ACTIVE SESSION
    private void setupActiveSession() {

        //ACTIVE SESSION PANEL
        activeSessionPanel.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        activeSessionPanel.setBackground(
                UITheme.CARD_COLOR
        );

        activeSessionPanel.setBorder(
                BorderFactory.createLineBorder(
                        UITheme.BORDER_COLOR,
                        1
                )
        );


        //ACTIVE SESSION LABEL
        activeSessionLabel.setText(
                "Active Sessions"
        );

        activeSessionLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        activeSessionLabel.setFont(
                UITheme.bold(17)
        );


        //TABLE
        activeSessionTable.setBackground(
                UITheme.CARD_COLOR
        );

        activeSessionTable.setForeground(
                UITheme.TEXT_COLOR
        );

        activeSessionTable.setFont(
                UITheme.regular(14)
        );

        activeSessionTable.setRowHeight(
                38
        );

        activeSessionTable.setGridColor(
                UITheme.BORDER_COLOR
        );

        activeSessionTable.setSelectionBackground(
                UITheme.BUTTON_COLOR
        );

        activeSessionTable.setSelectionForeground(
                UITheme.TEXT_COLOR
        );

        activeSessionTable.setShowVerticalLines(
                false
        );

        activeSessionTable.setShowHorizontalLines(
                true
        );


        //SCROLL PANE
        activeSessionScrollPane.setBackground(
                UITheme.CARD_COLOR
        );

        activeSessionScrollPane
                .getViewport()
                .setBackground(
                        UITheme.CARD_COLOR
                );

        activeSessionScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        UITheme.BORDER_COLOR,
                        1
                )
        );


        //COMPLETE SESSION BUTTON
        completeSessionButton.setText(
                "Complete Selected Session"
        );

        completeSessionButton.setBackground(
                UITheme.BUTTON_SELECTED_COLOR
        );

        completeSessionButton.setForeground(
                UITheme.TEXT_COLOR
        );

        completeSessionButton.setFont(
                UITheme.bold(15)
        );

        completeSessionButton.setFocusPainted(
                false
        );

        completeSessionButton.setBorderPainted(
                false
        );

        completeSessionButton.setOpaque(
                true
        );

        //HAND CURSOR
        completeSessionButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        //ADD TITLE
        activeSessionPanel.add(
                activeSessionLabel,
                BorderLayout.NORTH
        );


        //ADD TABLE
        activeSessionPanel.add(
                activeSessionScrollPane,
                BorderLayout.CENTER
        );


        //ADD BUTTON
        activeSessionPanel.add(
                completeSessionButton,
                BorderLayout.SOUTH
        );


        //ADD ACTIVE SESSION PANEL
        workspacePanel.add(
                activeSessionPanel,
                BorderLayout.CENTER
        );
    }
}