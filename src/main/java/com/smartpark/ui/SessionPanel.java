package com.smartpark.ui;

import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.ParkingZone;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SessionPanel extends JPanel {

    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;

    private JTextField vehicleRegistrationField;
    private JComboBox<String> zoneComboBox;
    private JComboBox<String> parkingSpaceComboBox;
    private JTextField entryTimeField;
    private JTable sessionsTable;
    private DefaultTableModel sessionsTableModel;

    private final Map<String, ParkingZone> zonesByName = new LinkedHashMap<>();

    private static final String SELECT_ZONE = "Select a zone";
    private static final String NO_AVAILABLE_SPACES = "No available spaces";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        populateZoneComboBox();
        refreshParkingSpaceComboBox();
        refreshActiveSessions();
    }

    private void setupSessionPanel() {
        JPanel sessionPanel = new JPanel(new BorderLayout());
        sessionPanel.setBackground(UITheme.BACKGROUND_COLOR);
        sessionPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        JLabel sessionLabel = new JLabel("Session Management");
        sessionLabel.setForeground(UITheme.TEXT_COLOR);
        sessionLabel.setFont(UITheme.bold(34));
        sessionLabel.setBorder(new EmptyBorder(0, 0, 25, 0));
        sessionPanel.add(sessionLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(0, 25));
        contentPanel.setBackground(UITheme.BACKGROUND_COLOR);
        contentPanel.add(createSessionForm(), BorderLayout.NORTH);
        contentPanel.add(createActiveSessionsPanel(), BorderLayout.CENTER);
        sessionPanel.add(contentPanel, BorderLayout.CENTER);

        add(sessionPanel, BorderLayout.CENTER);
    }

    private JPanel createSessionForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.CARD_COLOR);
        formPanel.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 18, 12, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        vehicleRegistrationField = new JTextField();
        styleTextField(vehicleRegistrationField);
        addFormRow(formPanel, gbc, 0, "Vehicle Registration", vehicleRegistrationField);

        zoneComboBox = createComboBox();
        zoneComboBox.addActionListener(event -> refreshParkingSpaceComboBox());
        addFormRow(formPanel, gbc, 1, "Zone", zoneComboBox);

        parkingSpaceComboBox = createComboBox();
        addFormRow(formPanel, gbc, 2, "Parking Space", parkingSpaceComboBox);

        entryTimeField = new JTextField(getCurrentDateTime());
        styleTextField(entryTimeField);
        entryTimeField.setEditable(false);
        addFormRow(formPanel, gbc, 3, "Entry Time", entryTimeField);

        JButton startSessionButton = new JButton("Start Parking Session");
        startSessionButton.setFont(UITheme.bold(15));
        startSessionButton.setForeground(UITheme.TEXT_COLOR);
        startSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        startSessionButton.setFocusPainted(false);
        startSessionButton.setBorder(new EmptyBorder(10, 15, 10, 15));
        startSessionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startSessionButton.addActionListener(event -> startParkingSession());

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 18, 18, 18);
        formPanel.add(startSessionButton, gbc);

        return formPanel;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent input) {
        JLabel formLabel = new JLabel(label);
        formLabel.setForeground(UITheme.TEXT_COLOR);
        formLabel.setFont(UITheme.regular(15));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.insets = new Insets(12, 18, 12, 18);
        panel.add(formLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;
        panel.add(input, gbc);
    }

    private JComboBox<String> createComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(UITheme.regular(15));
        comboBox.setForeground(UITheme.TEXT_COLOR);
        comboBox.setBackground(UITheme.BUTTON_COLOR);
        comboBox.setFocusable(false);
        comboBox.setPreferredSize(new Dimension(0, 42));
        return comboBox;
    }

    private void styleTextField(JTextField field) {
        field.setFont(UITheme.regular(15));
        field.setForeground(UITheme.TEXT_COLOR);
        field.setBackground(UITheme.BUTTON_COLOR);
        field.setCaretColor(UITheme.TEXT_COLOR);
        field.setBorder(new EmptyBorder(8, 10, 8, 10));
        field.setPreferredSize(new Dimension(0, 42));
    }

    private void populateZoneComboBox() {
        if (zoneComboBox == null) {
            return;
        }

        zonesByName.clear();
        zoneComboBox.removeAllItems();
        zoneComboBox.addItem(SELECT_ZONE);

        if (parkingController == null) {
            return;
        }

        List<ParkingZone> zones = parkingController.getAllZones();
        if (zones == null) {
            return;
        }

        for (ParkingZone zone : zones) {
            if (zone == null || zone.getZoneName() == null || zone.getZoneName().trim().isEmpty()) {
                continue;
            }

            zonesByName.put(zone.getZoneName(), zone);
            zoneComboBox.addItem(zone.getZoneName());
        }
    }

    public void refreshParkingSpaceComboBox() {
        if (parkingSpaceComboBox == null) {
            return;
        }

        parkingSpaceComboBox.removeAllItems();

        ParkingZone selectedZone = getSelectedZone();
        if (selectedZone == null || parkingController == null) {
            showNoAvailableSpaces();
            return;
        }

        List<ParkingSpace> parkingSpaces =
                parkingController.getParkingSpacesByZone(selectedZone.getZoneId());

        if (parkingSpaces != null) {
            for (ParkingSpace parkingSpace : parkingSpaces) {
                if (parkingSpace == null || parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
                    continue;
                }

                String spaceId = parkingSpace.getSpaceId();
                if (spaceId != null && !spaceId.trim().isEmpty()) {
                    parkingSpaceComboBox.addItem(spaceId);
                }
            }
        }

        if (parkingSpaceComboBox.getItemCount() == 0) {
            showNoAvailableSpaces();
        } else {
            parkingSpaceComboBox.setEnabled(true);
        }
    }

    private void showNoAvailableSpaces() {
        parkingSpaceComboBox.removeAllItems();
        parkingSpaceComboBox.addItem(NO_AVAILABLE_SPACES);
        parkingSpaceComboBox.setEnabled(false);
    }

    private ParkingZone getSelectedZone() {
        if (zoneComboBox == null) {
            return null;
        }

        Object selectedItem = zoneComboBox.getSelectedItem();
        if (selectedItem == null) {
            return null;
        }

        return zonesByName.get(selectedItem.toString());
    }

    private void startParkingSession() {
        String registration = vehicleRegistrationField.getText().trim();
        ParkingZone selectedZone = getSelectedZone();
        String selectedSpace = (String) parkingSpaceComboBox.getSelectedItem();

        if (registration.isEmpty()) {
            showWarning("Please enter a vehicle registration.", "Missing Vehicle");
            vehicleRegistrationField.requestFocus();
            return;
        }

        if (selectedZone == null) {
            showWarning("Please select a parking zone.", "Missing Zone");
            return;
        }

        if (selectedSpace == null || NO_AVAILABLE_SPACES.equals(selectedSpace)) {
            showWarning("There are no available parking spaces in the selected zone.", "No Available Space");
            return;
        }

        if (parkingController == null || vehicleController == null || parkingSessionController == null) {
            JOptionPane.showMessageDialog(this, "A required controller is unavailable.",
                    "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ParkingSpace parkingSpace = parkingController.findParkingSpace(selectedSpace);
        if (parkingSpace == null || parkingSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
            showWarning("The selected parking space is no longer available.", "Parking Space Unavailable");
            refreshParkingSpaceComboBox();
            return;
        }

        Vehicle vehicle = vehicleController.findVehicle(registration);
        if (vehicle == null) {
            showWarning("Vehicle '" + registration + "' was not found.\nPlease register the vehicle first.",
                    "Vehicle Not Found");
            vehicleRegistrationField.requestFocus();
            return;
        }

        try {
            ParkingSession session = new ParkingSession(
                    "S-" + System.currentTimeMillis(),
                    vehicle,
                    parkingSpace,
                    selectedZone,
                    getCurrentDateTime()
            );

            parkingSessionController.startSession(session);
            entryTimeField.setText(getCurrentDateTime());
            vehicleRegistrationField.setText("");
            refreshParkingSpaceComboBox();
            refreshActiveSessions();

            JOptionPane.showMessageDialog(this, "Parking session started successfully.",
                    "Session Started", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Unable to Start Session", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createActiveSessionsPanel() {
        JPanel activeSessionsPanel = new JPanel(new BorderLayout(0, 12));
        activeSessionsPanel.setBackground(UITheme.CARD_COLOR);
        activeSessionsPanel.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));

        JLabel title = new JLabel("Active Sessions");
        title.setForeground(UITheme.TEXT_COLOR);
        title.setFont(UITheme.bold(20));
        title.setBorder(new EmptyBorder(15, 18, 5, 18));
        activeSessionsPanel.add(title, BorderLayout.NORTH);

        String[] columns = {"Session ID", "Vehicle", "Zone", "Parking Space", "Entry Time", "Status"};
        sessionsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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

        JTableHeader header = sessionsTable.getTableHeader();
        header.setBackground(UITheme.BUTTON_COLOR);
        header.setForeground(UITheme.TEXT_COLOR);
        header.setFont(UITheme.bold(13));
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(UITheme.CARD_COLOR);
        renderer.setForeground(UITheme.TEXT_COLOR);
        renderer.setFont(UITheme.regular(13));
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        sessionsTable.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(sessionsTable);
        scrollPane.setBackground(UITheme.CARD_COLOR);
        scrollPane.getViewport().setBackground(UITheme.CARD_COLOR);
        scrollPane.setBorder(new LineBorder(UITheme.BORDER_COLOR, 1));
        activeSessionsPanel.add(scrollPane, BorderLayout.CENTER);

        JButton completeSessionButton = new JButton("Complete Selected Session");
        completeSessionButton.setFont(UITheme.bold(15));
        completeSessionButton.setForeground(UITheme.TEXT_COLOR);
        completeSessionButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        completeSessionButton.setFocusPainted(false);
        completeSessionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        completeSessionButton.setBorder(new EmptyBorder(10, 15, 10, 15));
        completeSessionButton.addActionListener(event -> completeSelectedSession());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(UITheme.CARD_COLOR);
        buttonPanel.setBorder(new EmptyBorder(0, 18, 18, 18));
        buttonPanel.add(completeSessionButton, BorderLayout.CENTER);
        activeSessionsPanel.add(buttonPanel, BorderLayout.SOUTH);

        return activeSessionsPanel;
    }

    public void refreshActiveSessions() {
        if (sessionsTableModel == null || parkingSessionController == null) {
            return;
        }

        sessionsTableModel.setRowCount(0);
        List<ParkingSession> sessions = parkingSessionController.getAllSessions();
        if (sessions == null) {
            return;
        }

        for (ParkingSession session : sessions) {
            if (session == null || session.getStatus() != ParkingSessionStatus.ACTIVE) {
                continue;
            }

            Vehicle vehicle = session.getVehicle();
            ParkingSpace space = session.getParkingSpace();
            ParkingZone zone = session.getParkingZone();

            sessionsTableModel.addRow(new Object[]{
                    session.getSessionId(),
                    vehicle == null ? "-" : vehicle.getRegistrationNumber(),
                    zone == null ? "-" : zone.getZoneName(),
                    space == null ? "-" : space.getSpaceId(),
                    session.getEntryTime(),
                    session.getStatus()
            });
        }
    }

    private void completeSelectedSession() {
        int selectedRow = sessionsTable.getSelectedRow();
        if (selectedRow == -1) {
            showWarning("Please select an active session first.", "No Session Selected");
            return;
        }

        String sessionId = sessionsTableModel.getValueAt(
                sessionsTable.convertRowIndexToModel(selectedRow), 0).toString();

        if (JOptionPane.showConfirmDialog(this, "Complete session " + sessionId + "?",
                "Complete Session", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            parkingSessionController.completeSession(sessionId);
            refreshParkingSpaceComboBox();
            refreshActiveSessions();
            JOptionPane.showMessageDialog(this, "Parking session completed successfully.",
                    "Session Completed", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(),
                    "Unable to Complete Session", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public void refresh() {
        populateZoneComboBox();
        refreshParkingSpaceComboBox();
        refreshActiveSessions();
        entryTimeField.setText(getCurrentDateTime());
        revalidate();
        repaint();
    }
}
