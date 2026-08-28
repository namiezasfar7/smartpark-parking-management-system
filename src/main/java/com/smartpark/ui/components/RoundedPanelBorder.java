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

    //GET BORDER INSETS
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

    //PAINT BORDER
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //BORDER COLOR
        g2.setColor(color);

        //DRAW ONLY THE BORDER
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

        g2.dispose();
    }
}