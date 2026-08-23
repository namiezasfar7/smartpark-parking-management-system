package com.smartpark.ui;

//IMPORTS
import java.awt.*;

//UI THEME CLASS
public final class UITheme {

    //DECLARE ATTRIBUTES
    //COLORS - COMMON
    public static final Color BACKGROUND_COLOR = new Color(15, 17, 23);
    public static final Color SIDEBAR_COLOR = new Color(21, 25, 34);
    public static final Color CARD_COLOR = new Color(21, 25, 34);
    public static final Color BUTTON_COLOR = new Color(26, 34, 51);
    public static final Color BUTTON_SELECTED_COLOR = new Color(37, 99, 235);
    public static final Color BORDER_COLOR = new Color(42, 51, 69);
    public static final Color TEXT_COLOR = new Color(255, 255, 255);
    public static final Color SECONDARY_TEXT_COLOR = new Color(156, 163, 175);

    //COLORS - DASHBOARD
    public static final Color CARD_TOTAL = new Color(37, 99, 235);
    public static final Color CARD_OCCUPIED = new Color(220, 76, 76);
    public static final Color CARD_AVAILABLE = new Color(34, 197, 94);
    public static final Color CARD_SESSIONS = new Color(139, 92, 246);

    //COLORS - PARKING
    public static final Color PARKING_AVAILABLE = new Color(34, 197, 94);
    public static final Color PARKING_OCCUPIED = new Color(220, 76, 76);
    public static final Color PARKING_RESERVED = new Color(245, 158, 11);
    public static final Color PARKING_MAINTENANCE = new Color(139, 92, 246);
    public static final Color PARKING_CARD_COLOR = new Color(21, 25, 34);
    public static final Color PARKING_HOVER_COLOR = new Color(30, 41, 59);

    //FONT
    public static final String FONT_NAME = "Poppins";

    //FONT METHODS
    public static Font font(float size, int style) {
        return new Font(FONT_NAME, style, (int) size);
    }

    public static Font regular(float size) {
        return font(size, Font.PLAIN);
    }

    public static Font bold(float size) {
        return font(size, Font.BOLD);
    }

    //PREVENT INSTANTIATION
    private UITheme() {
    }
}