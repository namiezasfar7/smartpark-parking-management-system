package com.smartpark.ui;

//IMPORTS
import com.smartpark.controller.VehicleController;
import com.smartpark.model.Vehicle;
import com.smartpark.model.VehicleType;
import com.smartpark.ui.components.RoundedPanelBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

//VEHICLE PANEL CLASS
public class VehiclePanel extends JPanel {

    //DECLARE ATTRIBUTES
    private JPanel vehiclePanel;
    private JLabel vehicleLabel;

    private JPanel workspacePanel;

    private JLabel registrationNumberLabel;
    private JTextField registrationNumberField;

    private JLabel ownerNameLabel;
    private JTextField ownerNameField;

    private JLabel vehicleTypeLabel;
    private JComboBox<VehicleType> vehicleTypeComboBox;

    private JButton registerVehicleButton;

    private JPanel vehicleListPanel;
    private JLabel listRegisterdVehicleLabel;

    private JTable vehicleListTable;
    private JScrollPane vehicleScrollPane;

    //CONTROLLER
    private final VehicleController vehicleController;

    //DECLARE CONSTRUCTOR
    public VehiclePanel(VehicleController vehicleController) {

        this.vehicleController = vehicleController;

        //INITIALIZE COMPONENTS
        vehiclePanel = new JPanel();

        vehicleLabel = new JLabel();

        workspacePanel = new JPanel();

        registrationNumberLabel = new JLabel();
        registrationNumberField = new RoundedTextField("  PG 1234");

        ownerNameLabel = new JLabel();
        ownerNameField = new RoundedTextField("  Sivakumar Sheshanth");

        vehicleTypeLabel = new JLabel();
        vehicleTypeComboBox = new RoundedComboBox<>();

        registerVehicleButton = new RoundedButton("Register Vehicle");

        vehicleListPanel = new RoundedPanel();

        listRegisterdVehicleLabel = new JLabel();

        vehicleListTable = new JTable();

        vehicleScrollPane = new JScrollPane(vehicleListTable);

        //ROOT PANEL
        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        //ADD VEHICLE PANEL
        add(vehiclePanel, BorderLayout.CENTER);

        //SETUP VEHICLE PANEL
        setupVehiclePanel();

        //LOAD EXISTING VEHICLES
        refreshVehicleTable();

        //REGISTER BUTTON ACTION
        registerVehicleButton.addActionListener(e -> registerVehicle());
    }

    //DECLARE METHODS
    //SET UP VEHICLE PANEL
    private void setupVehiclePanel() {

        //MAIN VEHICLE PANEL
        vehiclePanel.setLayout(new BorderLayout());
        vehiclePanel.setBackground(UITheme.BACKGROUND_COLOR);
        vehiclePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        //VEHICLE TITLE
        vehicleLabel.setText("Vehicle Management");
        vehicleLabel.setForeground(UITheme.TEXT_COLOR);
        vehicleLabel.setFont(UITheme.bold(28));
        vehicleLabel.setBorder(new EmptyBorder(0, 0, 25, 0));

        //WORKSPACE
        workspacePanel.removeAll();

        workspacePanel.setLayout(new BorderLayout(0, 24));
        workspacePanel.setBackground(UITheme.BACKGROUND_COLOR);
        workspacePanel.setBorder(new EmptyBorder(5, 0, 5, 0));

        //SETUP FORM
        setupVehicleForm();

        //SETUP VEHICLE LIST
        setupVehicleList();

        //BUILD MAIN PANEL
        vehiclePanel.removeAll();

        vehiclePanel.add(vehicleLabel, BorderLayout.NORTH);
        vehiclePanel.add(workspacePanel, BorderLayout.CENTER);

        vehiclePanel.revalidate();
        vehiclePanel.repaint();
    }

    //SET UP VEHICLE FORM
    private void setupVehicleForm() {

        //FORM PANEL
        JPanel formPanel = new RoundedPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(UITheme.CARD_COLOR);
        formPanel.setBorder(new EmptyBorder(14, 24, 18, 24));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        //REGISTRATION NUMBER
        registrationNumberLabel.setText("Registration Number");
        registrationNumberLabel.setForeground(UITheme.TEXT_COLOR);
        registrationNumberLabel.setFont(UITheme.regular(15));

        styleTextField(registrationNumberField);

        //OWNER NAME
        ownerNameLabel.setText("Owner Name");
        ownerNameLabel.setForeground(UITheme.TEXT_COLOR);
        ownerNameLabel.setFont(UITheme.regular(15));

        styleTextField(ownerNameField);

        //VEHICLE TYPE
        vehicleTypeLabel.setText("Vehicle Type");
        vehicleTypeLabel.setForeground(UITheme.TEXT_COLOR);
        vehicleTypeLabel.setFont(UITheme.regular(15));

        vehicleTypeComboBox.setModel(new DefaultComboBoxModel<>(VehicleType.values()));
        vehicleTypeComboBox.setBackground(UITheme.BUTTON_COLOR);
        vehicleTypeComboBox.setForeground(UITheme.TEXT_COLOR);
        vehicleTypeComboBox.setFont(UITheme.regular(15));
        vehicleTypeComboBox.setFocusable(false);
        vehicleTypeComboBox.setPreferredSize(new Dimension(0, 42));

        //REGISTER BUTTON
        registerVehicleButton.setFont(UITheme.bold(14));
        registerVehicleButton.setForeground(UITheme.TEXT_COLOR);
        registerVehicleButton.setBackground(UITheme.BUTTON_SELECTED_COLOR);
        registerVehicleButton.setFocusPainted(false);

        //SET CURSOR
        registerVehicleButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerVehicleButton.setPreferredSize(new Dimension(0, 46));

        //ROW 1 - REGISTRATION NUMBER
        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        formPanel.add(registrationNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        formPanel.add(registrationNumberField, gbc);

        //ROW 2 - OWNER NAME + VEHICLE TYPE
        gbc.gridy = 1;

        //OWNER LABEL
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        formPanel.add(ownerNameLabel, gbc);

        //OWNER FIELD
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;

        formPanel.add(ownerNameField, gbc);

        //VEHICLE TYPE LABEL
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        formPanel.add(vehicleTypeLabel, gbc);

        //VEHICLE TYPE
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;

        formPanel.add(vehicleTypeComboBox, gbc);

        //ROW 3 - REGISTER BUTTON
        gbc.gridy = 2;

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        gbc.insets = new Insets(10, 10, 4, 10);

        formPanel.add(registerVehicleButton, gbc);

        //ADD FORM
        workspacePanel.add(formPanel, BorderLayout.NORTH);
    }

    //STYLE TEXT FIELD
    private void styleTextField(JTextField field) {

        field.setBackground(UITheme.BUTTON_COLOR);
        field.setForeground(UITheme.TEXT_COLOR);
        field.setCaretColor(UITheme.TEXT_COLOR);
        field.setFont(UITheme.regular(15));
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(10, 14, 10, 14));
        field.setPreferredSize(new Dimension(0, 42));
    }

    //VEHICLE LIST
    private void setupVehicleList() {

        //LIST PANEL
        vehicleListPanel.setLayout(new BorderLayout(0, 0));
        vehicleListPanel.setBackground(UITheme.CARD_COLOR);
        vehicleListPanel.setOpaque(false);
        vehicleListPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedPanelBorder(UITheme.BORDER_COLOR, 18),
                                                                      new EmptyBorder(0, 0, 0, 0))
        );

        //LIST TITLE
        listRegisterdVehicleLabel.setText("Registered Vehicles");
        listRegisterdVehicleLabel.setForeground(UITheme.TEXT_COLOR);
        listRegisterdVehicleLabel.setFont(UITheme.bold(17));
        listRegisterdVehicleLabel.setOpaque(false);
        listRegisterdVehicleLabel.setBorder(new EmptyBorder(18, 22, 14, 22));

        // TABLE MODEL
        vehicleListTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Registration Number", "Owner Name", "Vehicle Type"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        //TABLE STYLE
        vehicleListTable.setOpaque(true);
        vehicleListTable.setBackground(UITheme.CARD_COLOR);
        vehicleListTable.setForeground(UITheme.TEXT_COLOR);
        vehicleListTable.setFont(UITheme.regular(13));
        vehicleListTable.setRowHeight(38);
        vehicleListTable.setGridColor(UITheme.BORDER_COLOR);
        vehicleListTable.setSelectionBackground(UITheme.BUTTON_COLOR);
        vehicleListTable.setSelectionForeground(UITheme.TEXT_COLOR);
        vehicleListTable.setShowVerticalLines(false);
        vehicleListTable.setShowHorizontalLines(true);
        vehicleListTable.setFillsViewportHeight(true);
        vehicleListTable.setIntercellSpacing(new Dimension(0, 0));
        vehicleListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //TABLE HEADER
        JTableHeader tableHeader = vehicleListTable.getTableHeader();
        tableHeader.setBackground(UITheme.BUTTON_COLOR);
        tableHeader.setForeground(UITheme.TEXT_COLOR);
        tableHeader.setFont(UITheme.bold(13));
        tableHeader.setPreferredSize(new Dimension(0, 44));
        tableHeader.setReorderingAllowed(false);

        //HEADER RENDERER
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setOpaque(true);
        headerRenderer.setBackground(UITheme.BUTTON_COLOR);
        headerRenderer.setForeground(UITheme.TEXT_COLOR);
        headerRenderer.setFont(UITheme.bold(13));
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBorder(new EmptyBorder(0, 0, 0, 0));

        tableHeader.setDefaultRenderer(headerRenderer);

        //TABLE CELL RENDERER
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setOpaque(true);
        cellRenderer.setBackground(UITheme.CARD_COLOR);
        cellRenderer.setForeground(UITheme.TEXT_COLOR);
        cellRenderer.setFont(UITheme.regular(13));
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        vehicleListTable.setDefaultRenderer(Object.class, cellRenderer);

        //SCROLL PANE
        vehicleScrollPane.setOpaque(false);
        vehicleScrollPane.getViewport().setOpaque(true);
        vehicleScrollPane.getViewport().setBackground(UITheme.CARD_COLOR);
        vehicleScrollPane.setBorder(BorderFactory.createEmptyBorder());
        vehicleScrollPane.getVerticalScrollBar().setOpaque(false);

        //ADD TITLE TO LIST PANEL
        vehicleListPanel.add(listRegisterdVehicleLabel, BorderLayout.NORTH);

        //ADD TABLE/SCROLL PANE TO LIST PANEL
        vehicleListPanel.add(vehicleScrollPane, BorderLayout.CENTER);

        //ADD LIST PANEL TO WORKSPACE
        workspacePanel.add(vehicleListPanel, BorderLayout.CENTER);
    }

    //REGISTER VEHICLE
    private void registerVehicle() {

        String registrationNumber = registrationNumberField.getText().trim().toUpperCase();
        String ownerName = ownerNameField.getText().trim();

        //CHECK EMPTY REGISTRATION
        if (registrationNumber.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter a registration number.", "Invalid Registration", JOptionPane.WARNING_MESSAGE);

            registrationNumberField.requestFocus();

            return;
        }

        //CHECK EMPTY OWNER
        if (ownerName.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter the owner's name.", "Invalid Owner Name", JOptionPane.WARNING_MESSAGE);

            ownerNameField.requestFocus();

            return;
        }

        //CHECK DUPLICATE REGISTRATION
        if (vehicleController.findVehicle(registrationNumber) != null) {

            JOptionPane.showMessageDialog(this, "A vehicle with this registration number already exists.", "Duplicate Vehicle", JOptionPane.WARNING_MESSAGE);

            registrationNumberField.requestFocus();

            return;
        }

        //GET VEHICLE TYPE
        VehicleType vehicleType = (VehicleType) vehicleTypeComboBox.getSelectedItem();

        //CREATE VEHICLE
        Vehicle vehicle = new Vehicle(registrationNumber, ownerName, vehicleType);

        //REGISTER THROUGH CONTROLLER
        vehicleController.registerVehicle(vehicle);

        //REFRESH TABLE
        refreshVehicleTable();

        //CLEAR FORM
        registrationNumberField.setText("");
        ownerNameField.setText("");

        vehicleTypeComboBox.setSelectedIndex(0);

        registrationNumberField.requestFocus();

        //SUCCESS MESSAGE
        JOptionPane.showMessageDialog(this, "Vehicle registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    //REFRESH VEHICLE TABLE
    private void refreshVehicleTable() {

        DefaultTableModel model = (DefaultTableModel) vehicleListTable.getModel();

        //CLEAR EXISTING ROWS
        model.setRowCount(0);

        //GET VEHICLES THROUGH CONTROLLER
        for (Vehicle vehicle : vehicleController.getAllVehicles()) {

            model.addRow(new Object[] {vehicle.getRegistrationNumber(), vehicle.getOwnerName(), vehicle.getVehicleType()});
        }
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

            //CARD BACKGROUND
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();

            //PAINT CHILD COMPONENTS
            super.paintComponent(g);
        }
    }

    //ROUNDED TEXT FIELD
    private static class RoundedTextField extends JTextField {

        private final int radius = 12;
        private final String placeholder;

        public RoundedTextField(String placeholder) {

            super();

            this.placeholder = placeholder;

            setOpaque(false);

            setBorder(new EmptyBorder(10, 14, 10, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //FIELD BACKGROUND
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();

            //PAINT NORMAL TEXT
            super.paintComponent(g);

            //PLACEHOLDER
            if (getText().isEmpty() && !hasFocus() && placeholder != null && !placeholder.isEmpty()) {

                Graphics2D placeholderGraphics = (Graphics2D) g.create();
                placeholderGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                placeholderGraphics.setFont(getFont());

                Color placeholderColor = UITheme.TEXT_COLOR;

                placeholderGraphics.setColor(new Color(placeholderColor.getRed(), placeholderColor.getGreen(), placeholderColor.getBlue(), 110));

                FontMetrics metrics = placeholderGraphics.getFontMetrics();

                int x = 14;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

                placeholderGraphics.drawString(placeholder, x, y);

                placeholderGraphics.dispose();
            }
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
            setFocusable(false);
            setBorder(new EmptyBorder(0, 12, 0, 12));

            setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {

                @Override
                protected JButton createArrowButton() {

                    JButton arrowButton = new JButton() {

                        @Override
                        protected void paintComponent(Graphics g) {

                            Graphics2D g2 = (Graphics2D) g.create();
                            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                            //ARROW COLOR
                            g2.setColor(UITheme.TEXT_COLOR);

                            //ARROW SIZE
                            int arrowWidth = 10;
                            int arrowHeight = 6;

                            //ARROW POSITION
                            int x = (getWidth() - arrowWidth) / 2;
                            int y = (getHeight() - arrowHeight) / 2;

                            //DRAW DOWNWARD ARROW
                            Polygon arrow = new Polygon();
                            arrow.addPoint(x, y);
                            arrow.addPoint(x + arrowWidth, y);
                            arrow.addPoint(x + arrowWidth / 2, y + arrowHeight);

                            g2.fillPolygon(arrow);

                            g2.dispose();
                        }
                    };

                    arrowButton.setOpaque(false);
                    arrowButton.setContentAreaFilled(false);
                    arrowButton.setBorderPainted(false);
                    arrowButton.setFocusPainted(false);

                    return arrowButton;
                }

                @Override
                public void paintCurrentValueBackground(
                        Graphics g,
                        Rectangle bounds,
                        boolean hasFocus) {
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //COMBO BOX BACKGROUND
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //COMBO BOX BORDER
            g2.setColor(UITheme.BORDER_COLOR);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

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

            addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    mouseOver = true;
                    repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
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

            //CHECK CONDITION
            if (mouseOver) {
                backgroundColor = UITheme.BUTTON_SELECTED_COLOR.brighter();
            }

            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);

            g2.dispose();

            super.paintComponent(g);
        }
    }
}