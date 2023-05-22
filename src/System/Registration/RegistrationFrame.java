package System.Registration;

import javax.swing.*;
import System.app.Windows_settings.*;
import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.PlaceHolder;
import utils.UindosPath;
import utils.WindowsStyleComponents;
import System.Login.LoginFrame;
import java.awt.*;

public class RegistrationFrame extends JFrame {

    private JLabel username, password, confirmPassword;
    private JTextField usernameTextField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, cancelButton;
    private JLabel lblRequisitiPassword;
    private JButton btnMostraNascondiOldPassword;
    private JButton btnMostraNascondiNewPassword;
    private Font font;

    public RegistrationFrame(LoginFrame loginFrame) {
        setTitle("Registrazione Utente");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(UindosPath.REGISTRATION_LOGO_PATH).getImage());
        setLocationRelativeTo(null);
        font = GestoreConfig.loadDefaultConfig().getFont();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(UindosPath.BACKGROUND_REGISTRATION_PATH);
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        username = new JLabel("Username:");
        username.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(username, gbc);

        usernameTextField = new JTextField();
        usernameTextField.setFont(font);
        PlaceHolder.addPlaceHolder(usernameTextField, "Inserisci username");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(usernameTextField, gbc);

        password = new JLabel("Password:");
        password.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(password, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(passwordField, gbc);

        btnMostraNascondiOldPassword = new JButton("Mostra Password");
        btnMostraNascondiOldPassword.setFont(font);
        WindowsStyleComponents.customizeButton(btnMostraNascondiOldPassword);
        btnMostraNascondiOldPassword.addActionListener(new ListenerMostraNascondiPassword(passwordField, btnMostraNascondiOldPassword));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(btnMostraNascondiOldPassword, gbc);

        confirmPassword = new JLabel("Conferma password:");
        confirmPassword.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(confirmPassword, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(confirmPasswordField, gbc);

        btnMostraNascondiNewPassword = new JButton("Mostra Password");
        btnMostraNascondiNewPassword.setFont(font);
        WindowsStyleComponents.customizeButton(btnMostraNascondiNewPassword);
        btnMostraNascondiNewPassword.addActionListener(new ListenerMostraNascondiPassword(confirmPasswordField, btnMostraNascondiNewPassword));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(btnMostraNascondiNewPassword, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        cancelButton = new JButton("Annulla");
        cancelButton.setFont(font);
        WindowsStyleComponents.customizeButton(cancelButton);
        cancelButton.addActionListener(new ListenerRegistration(this, loginFrame));
        cancelButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        buttonPanel.add(cancelButton);

        registerButton = new JButton("Registrati");
        registerButton.setFont(font);
        WindowsStyleComponents.customizeButton(registerButton);
        registerButton.addActionListener(new ListenerRegistration(this, loginFrame));
        registerButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(buttonPanel, gbc);

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(separator1, gbc);

        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(separator2, gbc);

        lblRequisitiPassword = new JLabel("<html><font color='red'>Requisiti della password:</font><br>- Deve contenere almeno 8 caratteri<br>- Deve contenere almeno una lettera minuscola<br>- Deve contenere almeno una lettera maiuscola<br>- Deve contenere almeno un numero<br>- Deve contenere almeno un carattere speciale</html>");
        Font italicFont = font.deriveFont(Font.ITALIC, 12);
        lblRequisitiPassword.setFont(italicFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblRequisitiPassword, gbc);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
        GestoreFrame.aggiungiFrame(this);
    }

    public void clearInput() {
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public JPasswordField getPassword() {
        return passwordField;
    }

    public JPasswordField getConfirmPassword() {
        return confirmPasswordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}