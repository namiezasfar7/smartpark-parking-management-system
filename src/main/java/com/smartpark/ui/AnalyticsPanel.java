package com.smartpark.ui;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.service.AnalyticsService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


//ANALYTICS PANEL CLASS
public class AnalyticsPanel extends JPanel {

    //DECLARE ATTRIBUTES
    private final AnalyticsService analyticsService;

    private JPanel analyticsPanel;
    private JLabel analyticsLabel;
    private JPanel workspacePanel;

    private JLabel totalParkingSessionLabel;
    private JLabel completedParkingSessionLabel;
    private JLabel currentlyActiveParkingSessionLabel;
    private JLabel averageParkingDurationLabel;

    private JTable statisticsTable;
    private DefaultTableModel statisticsTableModel;
    private JScrollPane statisticsScrollPane;

    private JPanel chartsPanel;
    private LineChartPanel sessionsChartPanel;
    private DonutChartPanel statusChartPanel;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    //DECLARE CONSTRUCTOR
    public AnalyticsPanel(AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;

        setLayout(new BorderLayout());
        setBackground(UITheme.BACKGROUND_COLOR);

        analyticsPanel = new JPanel(new BorderLayout());
        analyticsPanel.setBackground(UITheme.BACKGROUND_COLOR);

        add(analyticsPanel, BorderLayout.CENTER);

        setupAnalyticsPanel();

        refresh();
    }


    //DECLARE METHODS
    //MAIN ANALYTICS PANEL
    private void setupAnalyticsPanel() {

        analyticsPanel.setBorder(
                new EmptyBorder(20, 40, 20, 40)
        );

        //TITLE
        analyticsLabel = new JLabel("Analytics");

        analyticsLabel.setForeground(UITheme.TEXT_COLOR);
        analyticsLabel.setFont(UITheme.bold(34));

        analyticsLabel.setBorder(
                new EmptyBorder(0, 0, 20, 0)
        );

        analyticsPanel.add(
                analyticsLabel,
                BorderLayout.NORTH
        );

        //WORKSPACE
        workspacePanel = new JPanel();

        workspacePanel.setLayout(
                new BoxLayout(
                        workspacePanel,
                        BoxLayout.Y_AXIS
                )
        );

        workspacePanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        setupStatisticCards();
        setupCharts();
        setupStatisticsTable();

        //WORKSPACE SCROLL
        JScrollPane workspaceScrollPane =
                new JScrollPane(workspacePanel);

        workspaceScrollPane.setBorder(null);

        workspaceScrollPane.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        workspaceScrollPane.getViewport().setBackground(
                UITheme.BACKGROUND_COLOR
        );

        workspaceScrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        workspaceScrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        workspaceScrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        //SCROLLBAR
        JScrollBar verticalScrollBar =
                workspaceScrollPane.getVerticalScrollBar();

        verticalScrollBar.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        verticalScrollBar.setForeground(
                UITheme.BUTTON_COLOR
        );

        verticalScrollBar.setPreferredSize(
                new Dimension(12, 0)
        );

        verticalScrollBar.setUI(
                new BasicScrollBarUI() {

                    @Override
                    protected void configureScrollBarColors() {

                        this.thumbColor =
                                UITheme.BUTTON_COLOR;

                        this.trackColor =
                                UITheme.BACKGROUND_COLOR;
                    }

                    @Override
                    protected JButton createDecreaseButton(
                            int orientation
                    ) {

                        return createZeroButton();
                    }

                    @Override
                    protected JButton createIncreaseButton(
                            int orientation
                    ) {

                        return createZeroButton();
                    }

                    private JButton createZeroButton() {

                        JButton button = new JButton();

                        button.setPreferredSize(
                                new Dimension(0, 0)
                        );

                        button.setMinimumSize(
                                new Dimension(0, 0)
                        );

                        button.setMaximumSize(
                                new Dimension(0, 0)
                        );

                        return button;
                    }

                    @Override
                    protected void paintTrack(
                            Graphics g,
                            JComponent c,
                            Rectangle trackBounds
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setColor(
                                UITheme.BACKGROUND_COLOR
                        );

                        g2.fillRect(
                                trackBounds.x,
                                trackBounds.y,
                                trackBounds.width,
                                trackBounds.height
                        );

                        g2.dispose();
                    }

                    @Override
                    protected void paintThumb(
                            Graphics g,
                            JComponent c,
                            Rectangle thumbBounds
                    ) {

                        if (
                                thumbBounds.isEmpty()
                                        ||
                                        !((JScrollBar) c).isEnabled()
                        ) {
                            return;
                        }

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(
                                UITheme.BUTTON_COLOR
                        );

                        int width =
                                Math.max(
                                        1,
                                        thumbBounds.width - 4
                                );

                        int height =
                                Math.max(
                                        1,
                                        thumbBounds.height - 4
                                );

                        g2.fillRoundRect(
                                thumbBounds.x + 2,
                                thumbBounds.y + 2,
                                width,
                                height,
                                6,
                                6
                        );

                        g2.dispose();
                    }
                }
        );

        analyticsPanel.add(
                workspaceScrollPane,
                BorderLayout.CENTER
        );
    }


    //STATISTIC CARDS
    private void setupStatisticCards() {

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(1, 4, 16, 0)
                );

        cardsPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        cardsPanel.setPreferredSize(
                new Dimension(0, 145)
        );

        cardsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        145
                )
        );

        cardsPanel.setMinimumSize(
                new Dimension(0, 145)
        );

        cardsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JPanel totalCard =
                createStatisticCard(
                        "TOTAL SESSIONS",
                        "0",
                        UITheme.CARD_TOTAL
                );

        JPanel completedCard =
                createStatisticCard(
                        "COMPLETED",
                        "0",
                        UITheme.CARD_AVAILABLE
                );

        JPanel activeCard =
                createStatisticCard(
                        "ACTIVE",
                        "0",
                        UITheme.CARD_OCCUPIED
                );

        JPanel averageCard =
                createStatisticCard(
                        "AVG. DURATION",
                        "0m",
                        UITheme.CARD_SESSIONS
                );

        totalParkingSessionLabel =
                findValueLabel(totalCard);

        completedParkingSessionLabel =
                findValueLabel(completedCard);

        currentlyActiveParkingSessionLabel =
                findValueLabel(activeCard);

        averageParkingDurationLabel =
                findValueLabel(averageCard);

        cardsPanel.add(totalCard);
        cardsPanel.add(completedCard);
        cardsPanel.add(activeCard);
        cardsPanel.add(averageCard);

        workspacePanel.add(cardsPanel);

        workspacePanel.add(
                Box.createRigidArea(
                        new Dimension(0, 18)
                )
        );
    }


    //STATISTIC CARD
    private JPanel createStatisticCard(
            String title,
            String value,
            Color color
    ) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(color);

        card.setBorder(
                new EmptyBorder(
                        15,
                        10,
                        15,
                        10
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        titleLabel.setFont(
                UITheme.regular(15)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel valueLabel =
                new JLabel(
                        value,
                        SwingConstants.CENTER
                );

        valueLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        valueLabel.setFont(
                UITheme.bold(30)
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(titleLabel);

        card.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        card.add(valueLabel);

        return card;
    }


    //FIND VALUE LABEL
    private JLabel findValueLabel(JPanel card) {

        if (card == null) {
            return null;
        }

        for (Component component :
                card.getComponents()) {

            if (component instanceof JLabel) {

                JLabel label =
                        (JLabel) component;

                if (
                        label.getFont() != null
                                &&
                                label.getFont().isBold()
                ) {

                    return label;
                }
            }
        }

        return null;
    }


    //CHARTS
    private void setupCharts() {

        chartsPanel =
                new JPanel(
                        new GridLayout(1, 2, 16, 0)
                );

        chartsPanel.setBackground(
                UITheme.BACKGROUND_COLOR
        );

        chartsPanel.setPreferredSize(
                new Dimension(0, 300)
        );

        chartsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        300
                )
        );

        chartsPanel.setMinimumSize(
                new Dimension(0, 300)
        );

        chartsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        //LINE CHART
        JPanel sessionsContainer =
                createChartContainer(
                        "Sessions Over Time"
                );

        sessionsChartPanel =
                new LineChartPanel();

        sessionsContainer.add(
                sessionsChartPanel,
                BorderLayout.CENTER
        );

        //DONUT CHART
        JPanel statusContainer =
                createChartContainer(
                        "Session Status"
                );

        statusChartPanel =
                new DonutChartPanel();

        statusContainer.add(
                statusChartPanel,
                BorderLayout.CENTER
        );

        chartsPanel.add(sessionsContainer);
        chartsPanel.add(statusContainer);

        workspacePanel.add(chartsPanel);

        workspacePanel.add(
                Box.createRigidArea(
                        new Dimension(0, 18)
                )
        );
    }


    //CHART CONTAINER
    private JPanel createChartContainer(
            String title
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                UITheme.CARD_COLOR
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                UITheme.BORDER_COLOR,
                                1
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        panel.setMinimumSize(
                new Dimension(0, 280)
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        titleLabel.setFont(
                UITheme.bold(19)
        );

        titleLabel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        8,
                        0
                )
        );

        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        return panel;
    }


    //STATISTICS TABLE
    private void setupStatisticsTable() {

        JPanel statisticsPanel =
                new JPanel(
                        new BorderLayout(0, 10)
                );

        statisticsPanel.setBackground(
                UITheme.CARD_COLOR
        );

        statisticsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                UITheme.BORDER_COLOR,
                                1
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        statisticsPanel.setPreferredSize(
                new Dimension(0, 245)
        );

        statisticsPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        245
                )
        );

        statisticsPanel.setMinimumSize(
                new Dimension(0, 245)
        );

        statisticsPanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel statisticsLabel =
                new JLabel(
                        "Session Statistics"
                );

        statisticsLabel.setForeground(
                UITheme.TEXT_COLOR
        );

        statisticsLabel.setFont(
                UITheme.bold(19)
        );

        //TABLE COLUMNS
        String[] columns = {
                "Session ID",
                "Registration",
                "Parking Space",
                "Entry Time",
                "Exit Time",
                "Duration",
                "Status"
        };

        //TABLE MODEL
        statisticsTableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        statisticsTable =
                new JTable(
                        statisticsTableModel
                );

        statisticsTable.setBackground(
                UITheme.CARD_COLOR
        );

        statisticsTable.setForeground(
                UITheme.TEXT_COLOR
        );

        statisticsTable.setFont(
                UITheme.regular(13)
        );

        statisticsTable.setRowHeight(34);

        statisticsTable.setGridColor(
                UITheme.BORDER_COLOR
        );

        statisticsTable.setSelectionBackground(
                UITheme.BUTTON_COLOR
        );

        statisticsTable.setSelectionForeground(
                UITheme.TEXT_COLOR
        );

        statisticsTable.setShowVerticalLines(false);

        statisticsTable.setFillsViewportHeight(true);

        //HEADER
        JTableHeader header =
                statisticsTable.getTableHeader();

        header.setBackground(
                UITheme.BUTTON_COLOR
        );

        header.setForeground(
                UITheme.TEXT_COLOR
        );

        header.setFont(
                UITheme.bold(13)
        );

        header.setPreferredSize(
                new Dimension(0, 36)
        );

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setBackground(
                UITheme.BUTTON_COLOR
        );

        headerRenderer.setForeground(
                UITheme.TEXT_COLOR
        );

        headerRenderer.setFont(
                UITheme.bold(13)
        );

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        header.setDefaultRenderer(
                headerRenderer
        );

        //CELL RENDERER
        DefaultTableCellRenderer cellRenderer =
                new DefaultTableCellRenderer();

        cellRenderer.setBackground(
                UITheme.CARD_COLOR
        );

        cellRenderer.setForeground(
                UITheme.TEXT_COLOR
        );

        cellRenderer.setFont(
                UITheme.regular(13)
        );

        cellRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        statisticsTable.setDefaultRenderer(
                Object.class,
                cellRenderer
        );

        //SCROLL PANE
        statisticsScrollPane =
                new JScrollPane(
                        statisticsTable
                );

        statisticsScrollPane.setBackground(
                UITheme.CARD_COLOR
        );

        statisticsScrollPane.getViewport()
                .setBackground(
                        UITheme.CARD_COLOR
                );

        statisticsScrollPane.setBorder(
                new LineBorder(
                        UITheme.BORDER_COLOR,
                        1
                )
        );

        statisticsPanel.add(
                statisticsLabel,
                BorderLayout.NORTH
        );

        statisticsPanel.add(
                statisticsScrollPane,
                BorderLayout.CENTER
        );

        workspacePanel.add(statisticsPanel);
    }


    //REFRESH
    public void refresh() {

        if (analyticsService == null) {
            return;
        }

        //REFRESH STATISTIC CARDS
        int totalSessions =
                analyticsService.getTotalSessions();

        int completedSessions =
                analyticsService.getCompletedSessions();

        int activeSessions =
                analyticsService.getActiveSessions();

        String averageDuration =
                analyticsService.getAverageDurationFormatted();

        if (totalParkingSessionLabel != null) {
            totalParkingSessionLabel.setText(
                    String.valueOf(totalSessions)
            );
        }

        if (completedParkingSessionLabel != null) {
            completedParkingSessionLabel.setText(
                    String.valueOf(completedSessions)
            );
        }

        if (currentlyActiveParkingSessionLabel != null) {
            currentlyActiveParkingSessionLabel.setText(
                    String.valueOf(activeSessions)
            );
        }

        if (averageParkingDurationLabel != null) {
            averageParkingDurationLabel.setText(
                    averageDuration
            );
        }

        //REFRESH TABLE
        refreshStatisticsTable();

        //REFRESH CHARTS
        if (sessionsChartPanel != null) {

            sessionsChartPanel.refreshData();

            sessionsChartPanel.repaint();
        }

        if (statusChartPanel != null) {

            statusChartPanel.refreshData();

            statusChartPanel.repaint();
        }

        revalidate();
        repaint();
    }


    //REFRESH TABLE
    private void refreshStatisticsTable() {

        if (
                statisticsTableModel == null
                        ||
                        analyticsService == null
        ) {
            return;
        }

        statisticsTableModel.setRowCount(0);

        List<ParkingSession> sessions =
                analyticsService.getAllSessions();

        if (sessions == null) {
            return;
        }

        for (ParkingSession session : sessions) {

            if (session == null) {
                continue;
            }

            //SESSION ID
            String sessionId =
                    session.getSessionId();

            if (
                    sessionId == null
                            ||
                            sessionId.trim().isEmpty()
            ) {

                sessionId = "-";
            }

            //VEHICLE REGISTRATION
            String registration = "-";

            if (session.getVehicle() != null) {

                String value =
                        session.getVehicle()
                                .getRegistrationNumber();

                if (
                        value != null
                                &&
                                !value.trim().isEmpty()
                ) {

                    registration = value;
                }
            }

            //PARKING SPACE
            String parkingSpace = "-";

            if (session.getParkingSpace() != null) {

                String value =
                        session.getParkingSpace()
                                .getSpaceId();

                if (
                        value != null
                                &&
                                !value.trim().isEmpty()
                ) {

                    parkingSpace = value;
                }
            }

            //ENTRY TIME
            String entryTime =
                    formatDateTime(
                            session.getEntryTime()
                    );

            //EXIT TIME
            String exitTime =
                    formatDateTime(
                            session.getExitTime()
                    );

            //DURATION
            String duration =
                    getDuration(
                            session.getEntryTime(),
                            session.getExitTime()
                    );

            //STATUS
            String status = "-";

            if (session.getStatus() != null) {

                status =
                        session.getStatus().toString();
            }

            //ADD ROW
            statisticsTableModel.addRow(
                    new Object[]{
                            sessionId,
                            registration,
                            parkingSpace,
                            entryTime,
                            exitTime,
                            duration,
                            status
                    }
            );
        }

        if (statisticsTable != null) {

            statisticsTable.revalidate();

            statisticsTable.repaint();
        }
    }


    //FORMAT DATE TIME
    private String formatDateTime(String dateTime) {

        if (
                dateTime == null
                        ||
                        dateTime.trim().isEmpty()
        ) {

            return "-";
        }

        try {

            LocalDateTime parsedDateTime =
                    LocalDateTime.parse(
                            dateTime.trim(),
                            DATE_TIME_FORMATTER
                    );

            return parsedDateTime.format(
                    DATE_TIME_FORMATTER
            );

        }
        catch (DateTimeParseException e) {

            return dateTime;
        }
    }


    //GET DURATION
    private String getDuration(
            String entryTime,
            String exitTime
    ) {

        //NO ENTRY TIME
        if (
                entryTime == null
                        ||
                        entryTime.trim().isEmpty()
        ) {

            return "-";
        }

        //NO EXIT TIME
        if (
                exitTime == null
                        ||
                        exitTime.trim().isEmpty()
        ) {

            return "-";
        }

        try {

            LocalDateTime entry =
                    LocalDateTime.parse(
                            entryTime.trim(),
                            DATE_TIME_FORMATTER
                    );

            LocalDateTime exit =
                    LocalDateTime.parse(
                            exitTime.trim(),
                            DATE_TIME_FORMATTER
                    );

            long minutes =
                    Duration.between(
                            entry,
                            exit
                    ).toMinutes();

            if (minutes < 0) {

                return "-";
            }

            long hours =
                    minutes / 60;

            long remainingMinutes =
                    minutes % 60;

            if (hours > 0) {

                return hours
                        + "h "
                        + remainingMinutes
                        + "m";
            }

            return remainingMinutes + "m";

        }
        catch (DateTimeParseException e) {

            return "-";
        }
    }


    //LINE CHART
    private class LineChartPanel extends JPanel {

        private int[] values = new int[7];

        private final String[] labels = {
                "Day 1",
                "Day 2",
                "Day 3",
                "Day 4",
                "Day 5",
                "Day 6",
                "Today"
        };

        public LineChartPanel() {

            setBackground(
                    UITheme.CARD_COLOR
            );

            setOpaque(true);

            setPreferredSize(
                    new Dimension(0, 220)
            );

            setMinimumSize(
                    new Dimension(0, 200)
            );

            refreshData();
        }

        private void refreshData() {

            if (analyticsService == null) {
                return;
            }

            values =
                    analyticsService.getLastSevenDaysCounts();

            if (values == null || values.length == 0) {

                values = new int[7];
            }
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int width = getWidth();
            int height = getHeight();

            int left = 40;
            int right = 15;
            int top = 15;
            int bottom = 35;

            int chartWidth =
                    width - left - right;

            int chartHeight =
                    height - top - bottom;

            if (
                    chartWidth <= 0
                            ||
                            chartHeight <= 0
            ) {

                g2.dispose();

                return;
            }

            //GRID
            g2.setColor(
                    UITheme.BORDER_COLOR
            );

            for (int i = 0; i <= 4; i++) {

                int y =
                        top
                                +
                                chartHeight * i / 4;

                g2.drawLine(
                        left,
                        y,
                        width - right,
                        y
                );
            }

            int maxValue = 1;

            for (int value : values) {

                if (value > maxValue) {

                    maxValue = value;
                }
            }

            maxValue =
                    Math.max(
                            5,
                            ((maxValue + 4) / 5) * 5
                    );

            //LINE
            g2.setStroke(
                    new BasicStroke(
                            3f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            int previousX = 0;
            int previousY = 0;

            for (
                    int i = 0;
                    i < values.length;
                    i++
            ) {

                int x;

                if (values.length == 1) {

                    x =
                            left
                                    +
                                    chartWidth / 2;

                }
                else {

                    x =
                            left
                                    +
                                    chartWidth
                                            *
                                            i
                                            /
                                            (values.length - 1);
                }

                int y =
                        top
                                +
                                chartHeight
                                -
                                values[i]
                                        *
                                        chartHeight
                                        /
                                        maxValue;

                if (i > 0) {

                    g2.setColor(
                            UITheme.TEXT_COLOR
                    );

                    g2.drawLine(
                            previousX,
                            previousY,
                            x,
                            y
                    );
                }

                g2.setColor(
                        UITheme.BUTTON_SELECTED_COLOR
                );

                g2.fill(
                        new Ellipse2D.Double(
                                x - 5,
                                y - 5,
                                10,
                                10
                        )
                );

                previousX = x;
                previousY = y;
            }

            //LABELS
            g2.setColor(
                    UITheme.SECONDARY_TEXT_COLOR
            );

            g2.setFont(
                    UITheme.regular(12)
            );

            FontMetrics fm =
                    g2.getFontMetrics();

            for (
                    int i = 0;
                    i < labels.length;
                    i++
            ) {

                int x;

                if (labels.length == 1) {

                    x =
                            left
                                    +
                                    chartWidth / 2;
                }
                else {

                    x =
                            left
                                    +
                                    chartWidth
                                            *
                                            i
                                            /
                                            (labels.length - 1);
                }

                int textWidth =
                        fm.stringWidth(
                                labels[i]
                        );

                g2.drawString(
                        labels[i],
                        x - textWidth / 2,
                        height - 8
                );
            }

            g2.dispose();
        }
    }


    //DONUT CHART
    private class DonutChartPanel extends JPanel {

        private int completed = 0;
        private int active = 0;
        private int other = 0;

        public DonutChartPanel() {

            setBackground(
                    UITheme.CARD_COLOR
            );

            setOpaque(true);

            setPreferredSize(
                    new Dimension(0, 220)
            );

            setMinimumSize(
                    new Dimension(0, 200)
            );

            refreshData();
        }

        private void refreshData() {

            if (analyticsService == null) {
                return;
            }

            completed =
                    analyticsService.getCompletedSessions();

            active =
                    analyticsService.getActiveSessions();

            other =
                    analyticsService.getOtherSessions();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int total =
                    completed
                            +
                            active
                            +
                            other;

            if (total <= 0) {

                g2.setColor(
                        UITheme.SECONDARY_TEXT_COLOR
                );

                g2.setFont(
                        UITheme.regular(13)
                );

                String text =
                        "No session data";

                FontMetrics fm =
                        g2.getFontMetrics();

                g2.drawString(
                        text,
                        (
                                getWidth()
                                        -
                                        fm.stringWidth(text)
                        ) / 2,
                        getHeight() / 2
                );

                g2.dispose();

                return;
            }

            int width = getWidth();
            int height = getHeight();

            int diameter =
                    Math.min(
                            Math.max(
                                    130,
                                    height - 20
                            ),
                            175
                    );

            diameter =
                    Math.min(
                            diameter,
                            Math.max(
                                    120,
                                    width - 160
                            )
                    );

            diameter =
                    Math.max(
                            100,
                            diameter
                    );

            int x = 20;

            int y =
                    Math.max(
                            0,
                            (height - diameter) / 2
                    );

            double completedAngle =
                    360.0
                            *
                            completed
                            /
                            total;

            double activeAngle =
                    360.0
                            *
                            active
                            /
                            total;

            double otherAngle =
                    360.0
                            *
                            other
                            /
                            total;

            //COMPLETED
            g2.setColor(
                    UITheme.CARD_AVAILABLE
            );

            g2.fill(
                    new Arc2D.Double(
                            x,
                            y,
                            diameter,
                            diameter,
                            90,
                            -completedAngle,
                            Arc2D.PIE
                    )
            );

            //ACTIVE
            g2.setColor(
                    UITheme.CARD_OCCUPIED
            );

            g2.fill(
                    new Arc2D.Double(
                            x,
                            y,
                            diameter,
                            diameter,
                            90 - completedAngle,
                            -activeAngle,
                            Arc2D.PIE
                    )
            );

            //OTHER
            g2.setColor(
                    UITheme.CARD_SESSIONS
            );

            g2.fill(
                    new Arc2D.Double(
                            x,
                            y,
                            diameter,
                            diameter,
                            90
                                    -
                                    completedAngle
                                    -
                                    activeAngle,
                            -otherAngle,
                            Arc2D.PIE
                    )
            );

            //CENTER HOLE
            int holeSize =
                    diameter / 2;

            int holeX =
                    x
                            +
                            (diameter - holeSize) / 2;

            int holeY =
                    y
                            +
                            (diameter - holeSize) / 2;

            g2.setColor(
                    UITheme.CARD_COLOR
            );

            g2.fillOval(
                    holeX,
                    holeY,
                    holeSize,
                    holeSize
            );

            //LEGEND
            int legendX =
                    x
                            +
                            diameter
                            +
                            25;

            if (legendX + 130 > width) {

                legendX =
                        Math.max(
                                10,
                                width - 130
                        );
            }

            int legendY = 45;

            drawLegend(
                    g2,
                    legendX,
                    legendY,
                    UITheme.CARD_AVAILABLE,
                    "Completed  " + completed
            );

            drawLegend(
                    g2,
                    legendX,
                    legendY + 38,
                    UITheme.CARD_OCCUPIED,
                    "Active  " + active
            );

            drawLegend(
                    g2,
                    legendX,
                    legendY + 76,
                    UITheme.CARD_SESSIONS,
                    "Other  " + other
            );

            g2.dispose();
        }

        private void drawLegend(
                Graphics2D g2,
                int x,
                int y,
                Color color,
                String text
        ) {

            g2.setColor(color);

            g2.fillRoundRect(
                    x,
                    y - 11,
                    14,
                    14,
                    4,
                    4
            );

            g2.setColor(
                    UITheme.TEXT_COLOR
            );

            g2.setFont(
                    UITheme.regular(12)
            );

            g2.drawString(
                    text,
                    x + 22,
                    y + 1
            );
        }
    }
}