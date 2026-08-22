package com.smartpark.ui;

//IMPORTS
import java.awt.*;

//UI THEME CLASS
public final class UITheme {

    //DECLARE ATTRIBUTES
    //COLORS
    public static final Color BACKGROUND_COLOR = new Color(15, 17, 23);
    public static final Color SIDEBAR_COLOR = new Color(21, 25, 34);
    public static final Color CARD_COLOR = new Color(21, 25, 34);
    public static final Color BUTTON_COLOR = new Color(26, 34, 51);
    public static final Color BUTTON_SELECTED_COLOR = new Color(37, 99, 235);
    public static final Color BORDER_COLOR = new Color(42, 51, 69);
    public static final Color TEXT_COLOR = new Color(255, 255, 255);
    public static final Color SECONDARY_TEXT_COLOR = new Color(156, 163, 175);

    //FONT
    public static final String FONT_NAME =
            "Poppins";

    //FONT METHODS
    public static Font font(
            float size,
            int style
    ) {

        return new Font(
                FONT_NAME,
                style,
                (int) size
        );
    }

    public static Font regular(
            float size
    ) {

        return font(
                size,
                Font.PLAIN
        );
    }

    public static Font bold(
            float size
    ) {

        return font(
                size,
                Font.BOLD
        );
    }

    //PREVENT INSTANTIATION
    private UITheme() {
    }
}