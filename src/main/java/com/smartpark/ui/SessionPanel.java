package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;

import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.Vehicle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//SESSION PANEL CLASS
public class SessionPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;

    private JPanel sessionPanel;

    private JLabel sessionLabel;

    private JPanel formPanel;

    private JLabel vehicleRegistrationLabel;
    private JTextField vehicleRegistrationField;

    private JLabel parkingSpaceLabel;
    private JComboBox<String> parkingSpaceComboBox;

    private JLabel entryTimeLabel;
    private JTextField entryTimeField;

    private JPanel activeSessionsPanel;
    private JLabel activeSessionsLabel;

    private JTable sessionsTable;
    private DefaultTableModel sessionsTableModel;
    private JScrollPane sessionsScrollPane;

    private JButton completeSessionButton;

    private static final String NO_AVAILABLE_SPACES = "No available spaces";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DECLARE CONSTRUCTOR
    public SessionPanel(
            VehicleController vehicleController,
            ParkingController parkingController,
            ParkingSessionController parkingSessionController
    ) {

        this.vehicleController = vehicleController;
        this.parkingController = parkingController;
        this.parkingSessionController = parkingSessionController;

        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        setupSessionPanel();

        refreshParkingSpaceComboBox();
        refreshActiveSessions();
    }

    //DECLARE METHODS
    //SETUP
    private void setupSessionPanel() {

        sessionPanel = new JPanel(new BorderLayout());
        sessionPanel.setBackground(UITheme.BACKGROUND_COLOR);
        sessionPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        sessionLabel = new JLabel("Session Management");

        sessionLabel.setForeground(UITheme.TEXT_COLOR);
        sessionLabel.setFont(UITheme.bold(34));
        sessionLabel.setBorder(new EmptyBorder(0, 0, 25, 0));


        sessionPanel.add(sessionLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(0, 25));
        contentPanel.setBackground(UITheme.BACKGROUND_COLOR);

        JPanel sessionForm = createSessionForm();

        //CHECK CONDITION
        if (sessionForm != null) {
            contentPanel.add(sessionForm, BorderLayout.NORTH);
        }

        JPanel activePanel = createActiveSessionsPanel();

        //CHECK CONDITION
        if (activePanel != null) {
            contentPanel.add(activePanel, BorderLayout.CENTER);
        }

        sessionPanel.add(contentPanel, BorderLayout.CENTER);

        add(sessionPanel, BorderLayout.CENTER);
    }

    //SESSION FORM
    private JPanel createSessionForm() {

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.CARD_COLOR);
        formPanel.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 18, 12, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //VEHICLE
        vehicleRegistrationLabel = createFormLabel("Vehicle Registration");

        vehicleRegistrationField = new JTextField();

        styleTextField(vehicleRegistrationField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;

        formPanel.add(vehicleRegistrationLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        formPanel.add(vehicleRegistrationField, gbc);

        //PARKING SPACE
        parkingSpaceLabel = createFormLabel("Parking Space");

        parkingSpaceComboBox = new JComboBox<>();
        parkingSpaceComboBox.setFont(UITheme.regular(15));
        parkingSpaceComboBox.setForeground(UITheme.TEXT_COLOR);
        parkingSpaceComboBox.setBackground(UITheme.BUTTON_COLOR);
        parkingSpaceComboBox.setFocusable(false);
        parkingSpaceComboBox.setPreferredSize(new Dimension(0, 42));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.25;

        formPanel.add(parkingSpaceLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        formPanel.add(parkingSpaceComboBox, gbc);

        //ENTRY TIME
        entryTimeLabel = createFormLabel("Entry Time");

        entryTimeField = new JTextField();

        styleTextField(entryTimeField);
        entryTimeField.setEditable(false);
        entryTimeField.setText(getCurrentDateTime());

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.25;

        formPanel.add(entryTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        formPanel.add(entryTimeField, gbc);

        //START BUTTON

        JButton startSessionButton = new JButton("Start Parking Session");
        startSessionButton.setFont(UITheme.bold(15));
        startSessionButton.setForeground(UITheme.TEXT_COLOR);
        startSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        startSessionButton.setFocusPainted(false);
        startSessionButton.setBorder(new EmptyBorder(10, 15, 10, 15));

        //SET CURSOR
        startSessionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //SET ACTION LISTENER
        startSessionButton.addActionListener(e -> startParkingSession());

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(10, 18, 18, 18);

        formPanel.add(
                startSessionButton,
                gbc
        );

        return formPanel;
    }

    //TEXT FIELD STYLE
    private void styleTextField(JTextField field) {

        //CHECK CONDITION
        if (field == null) {
            return;
        }

        field.setFont(UITheme.regular(15));
        field.setForeground(UITheme.TEXT_COLOR);
        field.setBackground(UITheme.BUTTON_COLOR);
        field.setCaretColor(UITheme.TEXT_COLOR);
        field.setBorder(new EmptyBorder(8, 10, 8, 10));
        field.setPreferredSize(new Dimension(0, 42));
    }

    //FORM LABEL
    private JLabel createFormLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(UITheme.TEXT_COLOR);
        label.setFont(UITheme.regular(15));

        return label;
    }

    //REFRESH PARKING SPACES
    public void refreshParkingSpaceComboBox() {

        //CHECK CONDITION
        if (parkingSpaceComboBox == null) {
            return;
        }

        String previousSelection = (String) parkingSpaceComboBox.getSelectedItem();

        parkingSpaceComboBox.removeAllItems();

        List <ParkingSpace> parkingSpaces = null;

        //CHECK CONDITION
        if (parkingController != null) {
            parkingSpaces = parkingController.getAllParkingSpaces();
        }

        //CHECK CONDITION
        if (parkingSpaces != null) {

            //LOOP UNTIL CONDITION IS TRUE
            for (ParkingSpace parkingSpace : parkingSpaces) {

                //CHECK CONDITION
                if (parkingSpace == null) {
                    continue;
                }

                //CHECK CONDITION
                if (parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
                    continue;
                }

                String spaceId = parkingSpace.getSpaceId();

                //CHECK CONDITION
                if (spaceId == null || spaceId.trim().isEmpty()) {
                    continue;
                }

                parkingSpaceComboBox.addItem(spaceId);
            }
        }

        //CHECK CONDITION
        if (parkingSpaceComboBox.getItemCount() == 0) {

            parkingSpaceComboBox.addItem(NO_AVAILABLE_SPACES);

            parkingSpaceComboBox.setEnabled(false);

        }
        else {

            parkingSpaceComboBox.setEnabled(true);

            //CHECK CONDITION
            if (previousSelection != null) {

                //LOOP UNTIL CONDITION IS TRUE
                for (int i = 0; i < parkingSpaceComboBox.getItemCount(); i++) {

                    //CHECK CONDITION
                    if (previousSelection.equals(parkingSpaceComboBox.getItemAt(i))) {

                        parkingSpaceComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        parkingSpaceComboBox.revalidate();
        parkingSpaceComboBox.repaint();
    }

    //START SESSION
    private void startParkingSession() {

        //CHECK CONDITION
        if (vehicleRegistrationField == null || parkingSpaceComboBox == null) {
            return;
        }

        String registration = vehicleRegistrationField.getText().trim();


        String selectedSpace = (String) parkingSpaceComboBox.getSelectedItem();

        //CHECK CONDITION
        if (registration.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter a vehicle registration.", "Missing Vehicle", JOptionPane.WARNING_MESSAGE);
            vehicleRegistrationField.requestFocus();
            return;
        }

        //CHECK CONDITION
        if (selectedSpace == null || NO_AVAILABLE_SPACES.equals(selectedSpace)) {

            JOptionPane.showMessageDialog(this, "There are no available parking spaces.", "No Available Space", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //CHECK CONDITION
        if (parkingController == null) {

            JOptionPane.showMessageDialog(this, "Parking controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ParkingSpace parkingSpace = parkingController.findParkingSpace(selectedSpace);

        //CHECK CONDITION
        if (parkingSpace == null) {

            JOptionPane.showMessageDialog(this, "The selected parking space could not be found.", "Parking Space Error", JOptionPane.ERROR_MESSAGE);
            refreshParkingSpaceComboBox();
            return;
        }

        //CHECK CONDITION
        if (parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {

            JOptionPane.showMessageDialog(this, "The selected parking space is no longer available.", "Parking Space Unavailable", JOptionPane.WARNING_MESSAGE);
            refreshParkingSpaceComboBox();
            return;
        }

        //CHECK CONDITION
        if (vehicleController == null) {

            JOptionPane.showMessageDialog(this, "Vehicle controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vehicle vehicle = vehicleController.findVehicle(registration);

        //CHECK CONDITION
        if (vehicle == null) {

            JOptionPane.showMessageDialog(
                    this, "Vehicle '" + registration + "' was not found.\n" + "Please register the vehicle first.", "Vehicle Not Found", JOptionPane.WARNING_MESSAGE);
            vehicleRegistrationField.requestFocus();
            return;
        }

        //CHECK CONDITION
        if (parkingSessionController == null) {

            JOptionPane.showMessageDialog(this, "Parking session controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sessionId = "S-" + System.currentTimeMillis();

        String entryTime = getCurrentDateTime();

        ParkingSession session = new ParkingSession(sessionId, vehicle, parkingSpace, entryTime);

        parkingSessionController.startSession(session);
        entryTimeField.setText(getCurrentDateTime());
        vehicleRegistrationField.setText("");

        refreshParkingSpaceComboBox();
        refreshActiveSessions();

        JOptionPane.showMessageDialog(this, "Parking session started successfully.", "Session Started", JOptionPane.INFORMATION_MESSAGE);
    }

    //CURRENT DATE TIME
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    //ACTIVE SESSIONS
    private JPanel createActiveSessionsPanel() {

        activeSessionsPanel = new JPanel(new BorderLayout(0, 12));
        activeSessionsPanel.setBackground(UITheme.CARD_COLOR);
        activeSessionsPanel.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));

        //TITLE
        activeSessionsLabel = new JLabel("Active Sessions");
        activeSessionsLabel.setForeground(UITheme.TEXT_COLOR);
        activeSessionsLabel.setFont(UITheme.bold(20));
        activeSessionsLabel.setBorder(new EmptyBorder(15, 18, 5, 18));

        activeSessionsPanel.add(activeSessionsLabel, BorderLayout.NORTH);

        //TABLE COLUMNS
        String[] columns = {
                "Session ID",
                "Vehicle",
                "Parking Space",
                "Entry Time",
                "Status"
        };

        sessionsTableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //TABLE
        sessionsTable = new JTable(sessionsTableModel);
        sessionsTable.setBackground(UITheme.CARD_COLOR);
        sessionsTable.setForeground(UITheme.TEXT_COLOR);
        sessionsTable.setFont(UITheme.regular(13));
        sessionsTable.setRowHeight(34);
        sessionsTable.setGridColor(UITheme.BORDER_COLOR);
        sessionsTable.setSelectionBackground(UITheme.BUTTON_COLOR);
        sessionsTable.setSelectionForeground(UITheme.TEXT_COLOR);

        sessionsTable.setShowVerticalLines(false);
        sessionsTable.setFillsViewportHeight(true);
        sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //TABLE HEADER
        JTableHeader header = sessionsTable.getTableHeader();
        header.setBackground(UITheme.BUTTON_COLOR);
        header.setForeground(UITheme.TEXT_COLOR);
        header.setFont(UITheme.bold(13));

        //FIX HEADER HEIGHT
        header.setPreferredSize(new Dimension(0, 40));

        header.setReorderingAllowed(false);

        //HEADER RENDERER
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(UITheme.BUTTON_COLOR);
        headerRenderer.setForeground(UITheme.TEXT_COLOR);
        headerRenderer.setFont(UITheme.bold(13));
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        header.setDefaultRenderer(headerRenderer);

        //TABLE CELL RENDERER
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(UITheme.CARD_COLOR);
        cellRenderer.setForeground(UITheme.TEXT_COLOR);
        cellRenderer.setFont(UITheme.regular(13));
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        sessionsTable.setDefaultRenderer(Object.class, cellRenderer);

        //COLUMN WIDTHS
        sessionsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        sessionsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        sessionsTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        sessionsTable.getColumnModel().getColumn(3).setPreferredWidth(180);
        sessionsTable.getColumnModel().getColumn(4).setPreferredWidth(100);

        //SCROLL PANE
        sessionsScrollPane = new JScrollPane(sessionsTable);
        sessionsScrollPane.setBackground(UITheme.CARD_COLOR);
        sessionsScrollPane.getViewport().setBackground(UITheme.CARD_COLOR);
        sessionsScrollPane.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));

        activeSessionsPanel.add(sessionsScrollPane, BorderLayout.CENTER);

        //COMPLETE BUTTON
        completeSessionButton = new JButton("Complete Selected Session");
        completeSessionButton.setFont(UITheme.bold(15));
        completeSessionButton.setForeground(UITheme.TEXT_COLOR);
        completeSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        completeSessionButton.setFocusPainted(false);

        //CURSOR
        completeSessionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        completeSessionButton.setBorder(new EmptyBorder(10, 15, 10, 15));

        //ACTION LISTENER
        completeSessionButton.addActionListener(e -> completeSelectedSession());

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(UITheme.CARD_COLOR);
        buttonPanel.setBorder(new EmptyBorder(0, 18, 18, 18));

        buttonPanel.add(completeSessionButton, BorderLayout.CENTER);

        activeSessionsPanel.add(buttonPanel, BorderLayout.SOUTH);

        return activeSessionsPanel;
    }

    //REFRESH ACTIVE SESSIONS
    public void refreshActiveSessions() {

        //CHECK CONDITION
        if (sessionsTableModel == null) {
            return;
        }

        sessionsTableModel.setRowCount(0);

        //CHECK CONDITION
        if (parkingSessionController == null) {
            return;
        }

        List <ParkingSession> sessions = parkingSessionController.getAllSessions();

        //CHECK CONDITION
        if (sessions == null) {
            return;
        }

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession session : sessions) {

            //CHECK CONDITION
            if (session == null) {
                continue;
            }

            //CHECK CONDITION
            if (session.getStatus()
                    != ParkingSessionStatus.ACTIVE) {

                continue;
            }

            Vehicle vehicle = session.getVehicle();
            ParkingSpace parkingSpace = session.getParkingSpace();
            String sessionId = session.getSessionId();

            String vehicleRegistration = vehicle == null ? "-" : vehicle.getRegistrationNumber();
            String parkingSpaceId = parkingSpace == null ? "-" : parkingSpace.getSpaceId();

            String entryTime = session.getEntryTime();

            String status = session.getStatus() == null ? "-" : session.getStatus().toString();


            sessionsTableModel.addRow(new Object[]{sessionId, vehicleRegistration, parkingSpaceId, entryTime, status});
        }

        //CHECK CONDITION
        if (sessionsTable != null) {
            sessionsTable.revalidate();
            sessionsTable.repaint();
        }
    }

    //COMPLETE SESSION
    private void completeSelectedSession() {

        //CHECK CONDITION
        if (sessionsTable == null || sessionsTableModel == null) {
            return;
        }

        int selectedRow = sessionsTable.getSelectedRow();

        //CHECK CONDITION
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an active session first.", "No Session Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = sessionsTable.convertRowIndexToModel(selectedRow);

        Object sessionValue = sessionsTableModel.getValueAt(modelRow, 0);

        //CHECK CONDITION
        if (sessionValue == null) {
            return;
        }

        String sessionId = sessionValue.toString();

        int confirmation = JOptionPane.showConfirmDialog(this, "Complete session " + sessionId + "?", "Complete Session", JOptionPane.YES_NO_OPTION);

        //CHECK CONDITION
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        //CHECK CONDITION
        if (parkingSessionController == null) {

            JOptionPane.showMessageDialog(this, "Parking session controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        parkingSessionController.completeSession(sessionId);

        refreshActiveSessions();
        refreshParkingSpaceComboBox();

        JOptionPane.showMessageDialog(this, "Parking session completed successfully.", "Session Completed", JOptionPane.INFORMATION_MESSAGE);
    }

    //REFRESH
    public void refresh() {

        refreshParkingSpaceComboBox();
        refreshActiveSessions();

        if (entryTimeField != null) {
            entryTimeField.setText(getCurrentDateTime());
        }

        revalidate();
        repaint();
    }
}