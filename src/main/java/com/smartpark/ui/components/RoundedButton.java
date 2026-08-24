package com.smartpark.ui.components;

//IMPORTS
import com.smartpark.ui.UITheme;

import javax.swing.*;
import java.awt.*;

//ROUNDED BUTTON CLASS
public class RoundedButton extends JButton {

    //DECLARE ATTRIBUTES
    //BUTTON COLOR
    private Color normalColor = UITheme.BUTTON_COLOR;

    //HOVER COLOR
    private final Color hoverColor = UITheme.BUTTON_SELECTED_COLOR;

    //BORDER COLOR
    private final Color borderColor = UITheme.BORDER_COLOR;

    //DECLARE CONSTRUCTOR
    public RoundedButton() {

        super();

        //FONT
        setFont(UITheme.regular(16));

        //TEXT COLOR
        setForeground(UITheme.TEXT_COLOR);

        //BUTTON SETTINGS
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        //SET CURSOR
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        //ALIGN CENTER
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    //DECLARE SETTERS
    public void setButtonColor(Color color) {

        normalColor = color;
        repaint();
    }

    //DECLARE METHODS
    //PAINT BUTTON
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //BACKGROUND COLOR
        if (getModel().isRollover()) {

            g2.setColor(hoverColor);
        }
        else {

            g2.setColor(normalColor);
        }

        //ROUNDED BACKGROUND
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

        //BORDER
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
        g2.dispose();

        //DRAW BUTTON TEXT
        super.paintComponent(g);
    }
}