import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

class Login {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Student Performance Monitoring System - Login");
        frame.setSize(1919, 1006);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Create a custom panel with a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("wallpaper.jpg")));
                // Replace with your image path
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexibility
        frame.setContentPane(backgroundPanel);

        // Create a GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

        // Title label
        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 56));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(titleLabel, gbc);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(usernameField, gbc);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 28));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setEchoChar('*'); // Masking the password input
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(46, 204, 113)); // Green button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this::loginAction);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(loginButton, gbc);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setBackground(new Color(231, 76, 60)); // Red button
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(clearButton, gbc);

        frame.setVisible(true);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in both fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Authentication check
        if (username.equals("mayank") && password.equals("2025")) {
            frame.dispose();
            new StudentPerformanceSystem(); // Launch the main system
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
