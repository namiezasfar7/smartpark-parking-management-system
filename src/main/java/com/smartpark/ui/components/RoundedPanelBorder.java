package com.smartpark.ui.components;

//IMPORTS
import javax.swing.border.AbstractBorder;
import java.awt.*;

//ROUNDED PANEL BORDER CLASS
public class RoundedPanelBorder extends AbstractBorder {

    //DECLARE ATTRIBUTES
    private final Color color;
    private final int radius;

    //DECLARE CONSTRUCTOR
    public RoundedPanelBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    //DECLARE GETTERS
    //GET BORDER
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {

        insets.top = 1;
        insets.left = 1;
        insets.bottom = 1;
        insets.right = 1;

        return insets;
    }

    //DECLARE METHODS
    //PAINT BORDER
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //ROUNDED CARD COLOR
        g2.setColor(color);

        //ROUNDED SHAPE
        g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2.dispose();
    }
}