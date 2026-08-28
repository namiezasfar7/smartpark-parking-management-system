package com.smartpark.ui;

import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;

import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.Vehicle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


//SESSION PANEL CLASS
public class SessionPanel extends JPanel {

    //DECLARE ATTRIBUTES
    //CONTROLLERS
    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;

    //MAIN PANELS
    private JPanel sessionPanel;
    private JLabel sessionLabel;
    private JPanel formPanel;
    private JPanel activeSessionsPanel;

    //VEHICLE
    private JLabel vehicleRegistrationLabel;
    private JTextField vehicleRegistrationField;

    //ZONE
    private JLabel zoneLabel;
    private JComboBox<String> zoneComboBox;

    //PARKING SPACE
    private JLabel parkingSpaceLabel;
    private JComboBox<String> parkingSpaceComboBox;

    //ENTRY TIME
    private JLabel entryTimeLabel;
    private JTextField entryTimeField;

    //ACTIVE SESSIONS
    private JLabel activeSessionsLabel;

    private JTable sessionsTable;
    private DefaultTableModel sessionsTableModel;
    private JScrollPane sessionsScrollPane;

    private JButton completeSessionButton;

    //CONSTANTS
    private static final String NO_AVAILABLE_SPACES = "No available spaces";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DECLARE CONSTRUCTOR
    public SessionPanel(VehicleController vehicleController, ParkingController parkingController, ParkingSessionController parkingSessionController) {

        this.vehicleController = vehicleController;
        this.parkingController = parkingController;
        this.parkingSessionController = parkingSessionController;


        //ROOT PANEL
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        //SETUP
        setupSessionPanel();

        //REFRESH
        refreshParkingZoneComboBox();
        refreshParkingSpaceComboBox();
        refreshActiveSessions();
    }

    //SETUP SESSION PANEL

    private void setupSessionPanel() {

        //MAIN PANEL
        sessionPanel = new JPanel(new BorderLayout());
        sessionPanel.setBackground(UITheme.BACKGROUND_COLOR);
        sessionPanel.setBorder(new EmptyBorder(20, 22, 20, 22));

        //TITLE
        sessionLabel = new JLabel("Session Management");
        sessionLabel.setForeground(UITheme.TEXT_COLOR);
        sessionLabel.setFont(UITheme.bold(34));
        sessionLabel.setBorder(new EmptyBorder(0, 0, 25, 0));

        sessionPanel.add(sessionLabel, BorderLayout.NORTH);

        //CONTENT
        JPanel contentPanel = new JPanel(new BorderLayout(0, 24));
        contentPanel.setBackground(UITheme.BACKGROUND_COLOR);

        //FORM
        JPanel sessionForm = createSessionForm();

        //CHECK CONDITION
        if (sessionForm != null) {
            contentPanel.add(sessionForm, BorderLayout.NORTH);
        }

        //ACTIVE SESSIONS
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

        //ROUNDED FORM PANEL
        formPanel = new RoundedPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(UITheme.CARD_COLOR);
        formPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedPanelBorder(UITheme.BORDER_COLOR, 18),
                new EmptyBorder(14, 24, 18, 24))
        );

        //GRID BAG
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        //VEHICLE REGISTRATION
        vehicleRegistrationLabel = createFormLabel("Vehicle Registration");

        vehicleRegistrationField = new RoundedTextField();

        styleTextField(vehicleRegistrationField);

        vehicleRegistrationField.setToolTipText("Enter vehicle registration");

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        gbc.insets = new Insets(6, 0, 6, 14);

        formPanel.add(vehicleRegistrationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(6, 0, 6, 0);

        formPanel.add(vehicleRegistrationField, gbc);

        //ZONE
        zoneLabel = createFormLabel("Zone");

        zoneComboBox = new RoundedComboBox<>();

        styleComboBox(zoneComboBox);

        zoneComboBox.setPreferredSize(new Dimension(180, 42));

        //WHEN ZONE CHANGES
        zoneComboBox.addActionListener(e -> refreshParkingSpaceComboBox());

        //PARKING SPACE
        parkingSpaceLabel = createFormLabel("Parking Space");

        parkingSpaceComboBox = new RoundedComboBox<>();

        styleComboBox(parkingSpaceComboBox);

        //ZONE AND PARKING SPACE ROW
        gbc.gridy = 1;

        // ZONE LABEL
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        gbc.insets = new Insets(6, 0, 6, 12);

        formPanel.add(zoneLabel, gbc);

        //ZONE COMBO
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        gbc.insets = new Insets(6, 0, 6, 32);

        formPanel.add(zoneComboBox, gbc);

        //PARKING LABEL
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(6, 0, 6, 12);

        formPanel.add(parkingSpaceLabel, gbc);

        //PARKING COMBO
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(6, 0, 6, 0);

        formPanel.add(parkingSpaceComboBox, gbc);

        //ENTRY TIME
        entryTimeLabel = createFormLabel("Entry Time");
        entryTimeField = new RoundedTextField();

        styleTextField(entryTimeField);

        entryTimeField.setEditable(false);
        entryTimeField.setText(getCurrentDateTime());

        gbc.gridy = 2;

        //LABEL
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(6, 0, 6, 14);

        formPanel.add(entryTimeLabel, gbc);

        //FIELD
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(6, 0, 6, 0);

        formPanel.add(entryTimeField, gbc);

        // START BUTTON
        RoundedButton startSessionButton = new RoundedButton("Start Parking Session");
        startSessionButton.setFont(UITheme.bold(15));
        startSessionButton.setForeground(UITheme.TEXT_COLOR);
        startSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        startSessionButton.setFocusPainted(false);

        //SET CURSOR
        startSessionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startSessionButton.setPreferredSize(new Dimension(0, 46));

        startSessionButton.addActionListener(e -> startParkingSession());

        //BUTTON
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(10, 0, 0, 0);

        formPanel.add(startSessionButton, gbc);

        return formPanel;
    }

    //TEXT FIELD STYLE
    private void styleTextField(JTextField field) {

        if (field == null) {
            return;
        }

        field.setFont(UITheme.regular(15));
        field.setForeground(UITheme.TEXT_COLOR);
        field.setBackground(UITheme.BUTTON_COLOR);
        field.setCaretColor(UITheme.TEXT_COLOR);
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(10, 14, 10, 14));
        field.setPreferredSize(new Dimension(0, 42));
    }

    //COMBO BOX STYLE
    private void styleComboBox(JComboBox<?> comboBox) {

        comboBox.setFont(UITheme.regular(15));
        comboBox.setForeground(UITheme.TEXT_COLOR);
        comboBox.setBackground(UITheme.BUTTON_COLOR);

        comboBox.setFocusable(false);
        comboBox.setOpaque(false);

        comboBox.setBorder(new EmptyBorder(0, 12, 0, 34));
        comboBox.setPreferredSize(new Dimension(0, 42));
    }

    //FORM LABEL
    private JLabel createFormLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(UITheme.TEXT_COLOR);
        label.setFont(UITheme.regular(15));

        return label;
    }

    //REFRESH PARKING ZONES
    public void refreshParkingZoneComboBox() {

        if (zoneComboBox == null || parkingController == null) {
            return;
        }

        String previousSelection = (String) zoneComboBox.getSelectedItem();

        zoneComboBox.removeAllItems();

        List<ParkingSpace> parkingSpaces = parkingController.getAllParkingSpaces();

        if (parkingSpaces != null) {

            for (ParkingSpace parkingSpace : parkingSpaces) {

                if (parkingSpace == null || parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
                    continue;
                }

                String zoneId = parkingSpace.getZoneId();

                if (zoneId == null || zoneId.trim().isEmpty()) {
                    continue;
                }

                String zoneName = getZoneDisplayName(zoneId);
                boolean alreadyAdded = false;

                for (int i = 0; i < zoneComboBox.getItemCount(); i++) {
                    if (zoneName.equals(zoneComboBox.getItemAt(i))) {
                        alreadyAdded = true;
                        break;
                    }
                }

                if (!alreadyAdded) {
                    zoneComboBox.addItem(zoneName);
                }
            }
        }

        if (previousSelection != null) {
            zoneComboBox.setSelectedItem(previousSelection);
        }

        if (zoneComboBox.getSelectedIndex() == -1 && zoneComboBox.getItemCount() > 0) {
            zoneComboBox.setSelectedIndex(0);
        }

        zoneComboBox.revalidate();
        zoneComboBox.repaint();
    }

    //REFRESH PARKING SPACES
    public void refreshParkingSpaceComboBox() {

        if (parkingSpaceComboBox == null) {
            return;
        }

        String previousSelection = (String) parkingSpaceComboBox.getSelectedItem();

        parkingSpaceComboBox.removeAllItems();

        String selectedZoneName = zoneComboBox == null ? null : (String) zoneComboBox.getSelectedItem();
        String selectedZoneId = getZoneId(selectedZoneName);

        List<ParkingSpace> parkingSpaces = parkingController == null
                ? null
                : parkingController.getAllParkingSpaces();

        if (parkingSpaces != null) {

            for (ParkingSpace parkingSpace : parkingSpaces) {

                if (parkingSpace == null || parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
                    continue;
                }

                String spaceId = parkingSpace.getSpaceId();

                if (spaceId == null || spaceId.trim().isEmpty()) {
                    continue;
                }

                if (selectedZoneId != null
                        && parkingSpace.getZoneId() != null
                        && selectedZoneId.equalsIgnoreCase(parkingSpace.getZoneId())) {
                    parkingSpaceComboBox.addItem(spaceId);
                }
            }
        }

        if (parkingSpaceComboBox.getItemCount() == 0) {
            parkingSpaceComboBox.addItem(NO_AVAILABLE_SPACES);
            parkingSpaceComboBox.setEnabled(false);
        }
        else {
            parkingSpaceComboBox.setEnabled(true);

            if (previousSelection != null) {
                parkingSpaceComboBox.setSelectedItem(previousSelection);
            }

            if (parkingSpaceComboBox.getSelectedIndex() == -1) {
                parkingSpaceComboBox.setSelectedIndex(0);
            }
        }

        parkingSpaceComboBox.revalidate();
        parkingSpaceComboBox.repaint();
    }

    //GET FRIENDLY ZONE NAME
    private String getZoneDisplayName(String zoneId) {

        if (zoneId == null) {
            return "Unknown";
        }

        switch (zoneId.toUpperCase()) {
            case "GF":
                return "Ground";
            case "L1":
                return "Level 01";
            case "L2":
                return "Level 02";
            case "L3":
                return "Level 03";
            default:
                return zoneId;
        }
    }

    //GET ZONE ID
    private String getZoneId(String zoneName) {

        if (zoneName == null) {
            return null;
        }

        switch (zoneName) {
            case "Ground":
                return "GF";
            case "Level 01":
                return "L1";
            case "Level 02":
                return "L2";
            case "Level 03":
                return "L3";
            default:
                return zoneName;
        }
    }

    //START PARKING SESSION
    private void startParkingSession() {

        //CHECK CONDITION
        if (vehicleRegistrationField == null || parkingSpaceComboBox == null) {
            return;
        }

        String registration = vehicleRegistrationField.getText().trim().toUpperCase();

        String selectedSpace = (String) parkingSpaceComboBox.getSelectedItem();
        String selectedZone = zoneComboBox == null ? null : (String) zoneComboBox.getSelectedItem();

        //EMPTY REGISTRATION
        if (registration.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter a vehicle registration.", "Missing Vehicle", JOptionPane.WARNING_MESSAGE);

            vehicleRegistrationField.requestFocus();
            return;
        }

        //NO ZONE
        if (selectedZone == null) {

            JOptionPane.showMessageDialog(this, "Please select an available parking zone.", "No Zone Selected", JOptionPane.WARNING_MESSAGE);

            return;
        }

        //NO SPACE
        if (selectedSpace == null || NO_AVAILABLE_SPACES.equals(selectedSpace)) {

            JOptionPane.showMessageDialog(this, "There are no available parking spaces in this zone.", "No Available Space", JOptionPane.WARNING_MESSAGE);

            return;
        }

        //PARKING CONTROLLER
        if (parkingController == null) {

            JOptionPane.showMessageDialog(this, "Parking controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //FIND PARKING SPACE
        ParkingSpace parkingSpace = parkingController.findParkingSpace(selectedSpace);

        if (parkingSpace == null) {

            JOptionPane.showMessageDialog(this, "The selected parking space could not be found.", "Parking Space Error", JOptionPane.ERROR_MESSAGE);

            refreshParkingSpaceComboBox();

            return;
        }

        //CHECK SPACE BELONGS TO SELECTED ZONE
        String selectedZoneId = getZoneId(selectedZone);

        if (parkingSpace.getZoneId() == null || !selectedZoneId.equalsIgnoreCase(parkingSpace.getZoneId())) {

            JOptionPane.showMessageDialog(this, "The selected parking space does not belong to the selected zone.", "Zone Mismatch", JOptionPane.WARNING_MESSAGE);

            refreshParkingZoneComboBox();
            refreshParkingSpaceComboBox();

            return;
        }

        //CHECK SPACE STATUS
        if (parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {

            JOptionPane.showMessageDialog(this, "The selected parking space is no longer available.", "Parking Space Unavailable", JOptionPane.WARNING_MESSAGE);

            refreshParkingSpaceComboBox();

            return;
        }

        //VEHICLE CONTROLLER
        if (vehicleController == null) {

            JOptionPane.showMessageDialog(this, "Vehicle controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //FIND VEHICLE
        Vehicle vehicle = vehicleController.findVehicle(registration);

        if (vehicle == null) {

            JOptionPane.showMessageDialog(this, "Vehicle '" + registration + "' was not found.\n" + "Please register the vehicle first.", "Vehicle Not Found", JOptionPane.WARNING_MESSAGE);

            vehicleRegistrationField.requestFocus();

            return;
        }

        //SESSION CONTROLLER
        if (parkingSessionController == null) {

            JOptionPane.showMessageDialog(this, "Parking session controller is unavailable.", "System Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //CREATE SESSION
        String sessionId = "S-" + System.currentTimeMillis();
        String entryTime = getCurrentDateTime();

        ParkingSession session = new ParkingSession(sessionId, vehicle, parkingSpace, entryTime);

        //START SESSION
        parkingSessionController.startSession(session);

        //RESET FORM
        entryTimeField.setText(getCurrentDateTime());

        vehicleRegistrationField.setText("");

        //REFRESH
        refreshParkingSpaceComboBox();
        refreshActiveSessions();

        //SUCCESS
        JOptionPane.showMessageDialog(this, "Parking session started successfully.", "Session Started", JOptionPane.INFORMATION_MESSAGE);
    }

    //CURRENT DATE TIME
    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    //ACTIVE SESSIONS PANEL
    private JPanel createActiveSessionsPanel() {

        activeSessionsPanel = new RoundedPanel();
        activeSessionsPanel.setLayout(new BorderLayout(0, 0));
        activeSessionsPanel.setBackground(UITheme.CARD_COLOR);
        activeSessionsPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedPanelBorder(UITheme.BORDER_COLOR, 18),
                new EmptyBorder(0, 0, 0, 0))
        );

        //TITLE
        activeSessionsLabel = new JLabel("Active Sessions");
        activeSessionsLabel.setForeground(UITheme.TEXT_COLOR);
        activeSessionsLabel.setFont(UITheme.bold(20));
        activeSessionsLabel.setBorder(new EmptyBorder(18, 22, 14, 22));

        activeSessionsPanel.add(activeSessionsLabel, BorderLayout.NORTH);

        //TABLE COLUMNS
        String[] columns = {
                "Session ID",
                "Vehicle",
                "Zone",
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
        sessionsTable.setRowHeight(38);
        sessionsTable.setGridColor(UITheme.BORDER_COLOR);
        sessionsTable.setSelectionBackground(UITheme.BUTTON_COLOR);
        sessionsTable.setSelectionForeground(UITheme.TEXT_COLOR);

        sessionsTable.setShowVerticalLines(false);
        sessionsTable.setShowHorizontalLines(true);
        sessionsTable.setFillsViewportHeight(true);

        sessionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //TABLE HEADER
        JTableHeader header = sessionsTable.getTableHeader();
        header.setBackground(UITheme.BUTTON_COLOR);
        header.setForeground(UITheme.TEXT_COLOR);
        header.setFont(UITheme.bold(13));
        header.setPreferredSize(new Dimension(0, 44));
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
        sessionsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        sessionsTable.getColumnModel().getColumn(3).setPreferredWidth(130);
        sessionsTable.getColumnModel().getColumn(4).setPreferredWidth(180);
        sessionsTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        //SCROLL PANE
        sessionsScrollPane = new JScrollPane(sessionsTable);
        sessionsScrollPane.setBackground(UITheme.CARD_COLOR);
        sessionsScrollPane.getViewport().setBackground(UITheme.CARD_COLOR);
        sessionsScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        activeSessionsPanel.add(sessionsScrollPane, BorderLayout.CENTER);

        //COMPLETE BUTTON
        completeSessionButton = new RoundedButton("Complete Selected Session");
        completeSessionButton.setFont(UITheme.bold(15));
        completeSessionButton.setForeground(UITheme.TEXT_COLOR);
        completeSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        completeSessionButton.setFocusPainted(false);

        //SET CURSOR
        completeSessionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        completeSessionButton.setPreferredSize(new Dimension(0, 46));

        completeSessionButton.addActionListener(e -> completeSelectedSession());

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(UITheme.CARD_COLOR);
        buttonPanel.setBorder(new EmptyBorder(16, 22, 20, 22));
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
            if (session.getStatus() != ParkingSessionStatus.ACTIVE) {
                continue;
            }

            Vehicle vehicle = session.getVehicle();
            ParkingSpace parkingSpace = session.getParkingSpace();
            String sessionId = session.getSessionId();

            String vehicleRegistration = vehicle == null ? "-" : vehicle.getRegistrationNumber();
            String parkingSpaceId = parkingSpace == null ? "-" : parkingSpace.getSpaceId();
            String zone = parkingSpace == null ? "-" : getZoneDisplayName(parkingSpace.getZoneId());

            String entryTime = session.getEntryTime();

            String status = session.getStatus() == null ? "-" : session.getStatus().toString();

            sessionsTableModel.addRow(new Object[] {sessionId, vehicleRegistration, zone, parkingSpaceId, entryTime, status});
        }

        if (sessionsTable != null) {

            sessionsTable.revalidate();
            sessionsTable.repaint();
        }
    }

    //COMPLETE SELECTED SESSION
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
        refreshParkingZoneComboBox();
        refreshParkingSpaceComboBox();

        JOptionPane.showMessageDialog(this, "Parking session completed successfully.", "Session Completed", JOptionPane.INFORMATION_MESSAGE);
    }

    //REFRESH
    public void refresh() {

        refreshParkingZoneComboBox();
        refreshParkingSpaceComboBox();
        refreshActiveSessions();

        //CHECK CONDITION
        if (entryTimeField != null) {
            entryTimeField.setText(getCurrentDateTime());
        }

        revalidate();
        repaint();
    }

    //ROUNDED PANEL
    private static class RoundedPanel extends JPanel {

        private final int radius = 18;

        public RoundedPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }
    }

    //ROUNDED TEXT FIELD
    private static class RoundedTextField extends JTextField {

        private final int radius = 12;

        public RoundedTextField() {

            super();

            setOpaque(false);

            setBorder(new EmptyBorder(10, 14, 10, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(UITheme.BORDER_COLOR);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();
        }
    }

    //ROUNDED COMBO BOX
    private static class RoundedComboBox<E> extends JComboBox<E> {

        private final int radius = 12;

        public RoundedComboBox() {

            super();

            setOpaque(false);

            setUI(new BasicComboBoxUI() {

                @Override
                protected JButton createArrowButton() {

                    return new JButton() {
                        {
                            setOpaque(false);
                            setContentAreaFilled(false);
                            setBorderPainted(false);
                            setFocusPainted(false);
                            setFocusable(false);
                        }

                        @Override
                        protected void paintComponent(Graphics g) {

                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                            //SMALL ARROW
                            int centerX = getWidth() - 9;
                            int centerY = getHeight() / 2;

                            Polygon arrow = new Polygon();
                            arrow.addPoint(centerX - 5, centerY - 2);
                            arrow.addPoint(centerX + 5, centerY - 2);
                            arrow.addPoint(centerX, centerY + 4);

                            g2.setColor(UITheme.TEXT_COLOR);
                            g2.fillPolygon(arrow);

                            g2.dispose();
                        }
                    };
                }

                @Override
                public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //BACKGROUND
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //BORDER
            g2.setColor(UITheme.BORDER_COLOR);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();
        }
    }

    //ROUNDED COMBO BOX UI
    private static class RoundedComboBoxUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {

            JButton arrowButton = new JButton();
            arrowButton.setOpaque(false);
            arrowButton.setContentAreaFilled(false);
            arrowButton.setBorderPainted(false);
            arrowButton.setFocusPainted(false);

            arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
            arrowButton.setPreferredSize(new Dimension(42, 42));
            arrowButton.setIcon(new ComboBoxArrowIcon(UITheme.TEXT_COLOR));

            return arrowButton;
        }

        @Override
        protected ListCellRenderer <Object> createRenderer() {

            return new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    //SET TEXT STYLE
                    label.setFont(UITheme.regular(15));
                    label.setForeground(UITheme.TEXT_COLOR);

                    //SET BACKGROUND
                    if (isSelected) {
                        label.setBackground(UITheme.BUTTON_SELECTED_COLOR);
                    }
                    else {
                        label.setBackground(UITheme.BUTTON_COLOR);
                    }

                    label.setBorder(new EmptyBorder(8, 12, 8, 12));

                    return label;
                }
            };
        }

        @Override
        protected void installDefaults() {

            super.installDefaults();

            //REMOVE DEFAULT COMBO BOX BORDER
            comboBox.setBorder(new EmptyBorder(0, 10, 0, 42));
        }
    }

    //COMBO BOX ARROW ICON
    private static class ComboBoxArrowIcon implements Icon {

        private final Color color;

        public ComboBoxArrowIcon(Color color) {
            this.color = color;
        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 10;
        }

        @Override
        public void paintIcon(Component component, Graphics graphics, int x, int y) {

            Graphics2D g2 = (Graphics2D) graphics.create();

            //ENABLE ANTIALIASING
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //DRAW ARROW
            g2.setColor(color);

            int[] xPoints = {x, x + 16, x + 8};
            int[] yPoints = {y, y, y + 10};

            g2.fillPolygon(xPoints, yPoints, 3);

            g2.dispose();
        }
    }

    //ROUNDED BUTTON
    private static class RoundedButton extends JButton {

        private boolean mouseOver = false;

        public RoundedButton(String text) {

            super(text);

            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseOver = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOver = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color backgroundColor = getBackground();

            if (mouseOver) {
                backgroundColor = UITheme.BUTTON_SELECTED_COLOR.brighter();
            }

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);

            g2.dispose();

            super.paintComponent(g);
        }
    }

    //ROUNDED PANEL BORDER
    private static class RoundedPanelBorder implements Border {

        private final Color color;
        private final int radius;

        public RoundedPanelBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return new Insets(1, 1, 1, 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

            g2.dispose();
        }
    }
}