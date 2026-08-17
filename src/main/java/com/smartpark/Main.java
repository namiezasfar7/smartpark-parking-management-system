package com.smartpark;

//IMPORTS
import com.smartpark.ui.MainFrame;
import javax.swing.SwingUtilities;

//MAIN CLASS
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame mainFrame = new MainFrame();

            mainFrame.setVisible(true);
        });
    }
}