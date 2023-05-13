package System.Registration;

import javax.swing.*;

import System.Login.LoginFrame;

import java.awt.*;

public class RegistrationFrame extends JFrame {

    private JLabel username, password, confirmPassword;
    private JTextField usernameTextField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, cancelButton;

    public void clearInput(){
        usernameTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public RegistrationFrame(LoginFrame loginFrame) {
        setTitle("Registrazione Utente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));

        username = new JLabel("Username:");
        panel.add(username);

        usernameTextField = new JTextField();
        panel.add(usernameTextField);

        password = new JLabel("Password:");
        panel.add(password);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        confirmPassword = new JLabel("Conferma password:");
        panel.add(confirmPassword);

        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        registerButton = new JButton("Registrati");
        registerButton.addActionListener(new ListenerRegistration(this, loginFrame));
        registerButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        panel.add(registerButton);

        cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(new ListenerRegistration(this, loginFrame));
        cancelButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        panel.add(cancelButton);

        JPanel southPanel = new JPanel();
        southPanel.add(registerButton);
        southPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public JPasswordField getPassword(){
        return passwordField;
    }

    public JPasswordField getConfirmPassword(){
        return confirmPasswordField;
    }

    public JButton getRegisterButton(){
        return registerButton;
    }
}