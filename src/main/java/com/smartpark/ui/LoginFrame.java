package com.smartpark.ui;

//IMPORTS
import com.smartpark.ui.components.RoundedButton;
import com.smartpark.ui.components.RoundedPanelBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;

//LOGIN FRAME CLASS
public class LoginFrame extends JFrame {

    //ALLOWED ACCOUNTS
    private static final Map<String, String> ALLOWED_CREDENTIALS = Map.of(
            "Namiez", "admin01",
            "Sheshanth", "admin02",
            "Lakmina", "admin03",
            "Amasha", "admin04",
            "Admin", "admin05"
    );

    //DECLARE ATTRIBUTES
    private final Consumer<String> loginSuccessAction;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    //DECLARE CONSTRUCTOR
    public LoginFrame(Consumer<String> loginSuccessAction) {

        this.loginSuccessAction = loginSuccessAction;

        //FRAME SETTINGS
        setTitle("SmartPark - Sign In");

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/smartpark-icon.png"));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //MATCH MAIN APPLICATION SIZE
        setSize(1200, 750);
        setMinimumSize(new Dimension(1200, 750));

        //CENTER WINDOW ON SCREEN
        setLocationRelativeTo(null);

        //CREATE UI
        setupLoginFrame();
    }

    //SETUP LOGIN FRAME
    private void setupLoginFrame() {

        //BACKGROUND PANEL
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(UITheme.BACKGROUND_COLOR);
        backgroundPanel.setBorder(new EmptyBorder(40, 55, 40, 55));

        //LOGIN CARD
        JPanel loginCard = new JPanel();
        loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
        loginCard.setBackground(UITheme.CARD_COLOR);
        loginCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedPanelBorder(UITheme.BORDER_COLOR, 22),
                new EmptyBorder(42, 42, 42, 42)
        ));

        //INCREASED HEIGHT SO THE BUTTON IS NOT CUT OFF
        loginCard.setMinimumSize(new Dimension(450, 650));
        loginCard.setPreferredSize(new Dimension(450, 650));
        loginCard.setMaximumSize(new Dimension(450, 650));

        //LOGO AND TEXT
        JLabel logoLabel = createCenteredLabel(
                "SmartPark",
                UITheme.bold(38),
                UITheme.TEXT_COLOR
        );

        JLabel subtitleLabel = createCenteredLabel(
                "Parking Management System",
                UITheme.regular(15),
                UITheme.SECONDARY_TEXT_COLOR
        );

        JLabel welcomeLabel = createCenteredLabel(
                "Welcome back",
                UITheme.bold(23),
                UITheme.TEXT_COLOR
        );

        JLabel instructionLabel = createCenteredLabel(
                "Sign in to continue to your dashboard",
                UITheme.regular(14),
                UITheme.SECONDARY_TEXT_COLOR
        );

        loginCard.add(logoLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 7)));
        loginCard.add(subtitleLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 42)));
        loginCard.add(welcomeLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 8)));
        loginCard.add(instructionLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 30)));

        //LOGIN FORM
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(UITheme.CARD_COLOR);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMinimumSize(new Dimension(360, 172));
        formPanel.setPreferredSize(new Dimension(360, 172));
        formPanel.setMaximumSize(new Dimension(360, 172));

        //USERNAME
        formPanel.add(createFieldLabel("Username"));
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        usernameField = new JTextField();
        configureInputField(usernameField);

        formPanel.add(usernameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        //PASSWORD
        formPanel.add(createFieldLabel("Password"));
        formPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        passwordField = new JPasswordField();
        configureInputField(passwordField);

        formPanel.add(passwordField);

        //ADD FORM
        loginCard.add(formPanel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 14)));

        //ERROR MESSAGE
        messageLabel = createCenteredLabel(
                " ",
                UITheme.regular(13),
                UITheme.CARD_OCCUPIED
        );

        loginCard.add(messageLabel);
        loginCard.add(Box.createRigidArea(new Dimension(0, 10)));

        //SIGN IN BUTTON
        RoundedButton signInButton = new RoundedButton();

        signInButton.setText("Sign In");
        signInButton.setFont(UITheme.bold(16));
        signInButton.setButtonColor(UITheme.BUTTON_SELECTED_COLOR);
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMinimumSize(new Dimension(360, 52));
        signInButton.setPreferredSize(new Dimension(360, 52));
        signInButton.setMaximumSize(new Dimension(360, 52));

        signInButton.addActionListener(e -> attemptLogin());

        loginCard.add(signInButton);

        //ALLOW ENTER KEY TO SIGN IN
        getRootPane().setDefaultButton(signInButton);

        //ADD LOGIN CARD
        backgroundPanel.add(loginCard);

        //SET CONTENT PANE
        setContentPane(backgroundPanel);
    }

    //CREATE CENTERED LABEL
    private JLabel createCenteredLabel(String text, Font font, Color color) {

        JLabel label = new JLabel(text, SwingConstants.CENTER);

        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        return label;
    }

    //CREATE FIELD LABEL
    private JLabel createFieldLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(UITheme.regular(14));
        label.setForeground(UITheme.TEXT_COLOR);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setMaximumSize(new Dimension(360, 22));
        label.setPreferredSize(new Dimension(360, 22));

        return label;
    }

    //CONFIGURE INPUT FIELD
    private void configureInputField(JTextField field) {

        field.setFont(UITheme.regular(15));
        field.setForeground(UITheme.TEXT_COLOR);
        field.setCaretColor(UITheme.TEXT_COLOR);
        field.setBackground(UITheme.BUTTON_COLOR);

        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedPanelBorder(UITheme.BORDER_COLOR, 12),
                new EmptyBorder(0, 14, 0, 14)
        ));

        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMinimumSize(new Dimension(360, 46));
        field.setMaximumSize(new Dimension(360, 46));
        field.setPreferredSize(new Dimension(360, 46));
    }

    //ATTEMPT LOGIN
    private void attemptLogin() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        //CHECK CREDENTIALS
        if (ALLOWED_CREDENTIALS.containsKey(username)
                && ALLOWED_CREDENTIALS.get(username).equals(password)) {

            //CLOSE LOGIN WINDOW
            dispose();

            //OPEN MAIN APPLICATION WITH USERNAME
            loginSuccessAction.accept(username);

            return;
        }

        //SHOW ERROR MESSAGE
        messageLabel.setText("Incorrect username or password.");

        //CLEAR PASSWORD
        passwordField.setText("");
        passwordField.requestFocusInWindow();
    }
}