package System.Registration;

import javax.swing.*;
import System.app.Windows_settings.*;
import utils.GestoreFrame;
import utils.PlaceHolder;
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

    public RegistrationFrame(LoginFrame loginFrame) {
        setTitle("Registrazione Utente");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,3));

        username = new JLabel("Username:");
        panel.add(username);

        usernameTextField = new JTextField();
        PlaceHolder.addPlaceHolder(usernameTextField, "Inserisci username");
        panel.add(usernameTextField);
        panel.add(new JLabel());

        password = new JLabel("Password:");
        panel.add(password);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        btnMostraNascondiOldPassword = new JButton("Mostra Password");
        btnMostraNascondiOldPassword.addActionListener(new ListenerMostraNascondiPassword(passwordField, btnMostraNascondiOldPassword));
        panel.add(btnMostraNascondiOldPassword);

        confirmPassword = new JLabel("Conferma password:");
        panel.add(confirmPassword);

        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        btnMostraNascondiNewPassword = new JButton("Mostra Password");
        btnMostraNascondiNewPassword .addActionListener(new ListenerMostraNascondiPassword(confirmPasswordField, btnMostraNascondiNewPassword));
        panel.add(btnMostraNascondiNewPassword);

        registerButton = new JButton("Registrati");
        registerButton.addActionListener(new ListenerRegistration(this, loginFrame));
        registerButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        panel.add(registerButton);

        cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(new ListenerRegistration(this, loginFrame));
        cancelButton.addKeyListener(new ListenerRegistration(this, loginFrame));
        panel.add(cancelButton);


        lblRequisitiPassword = new JLabel("<html><font color='red'>Requisiti della password:</font><br>- Deve contenere almeno 8 caratteri<br>- Deve contenere almeno una lettera minuscola<br>- Deve contenere almeno una lettera maiuscola<br>- Deve contenere almeno un numero<br>- Deve contenere almeno un carattere speciale</html>");
        panel.add(lblRequisitiPassword);
        JPanel southPanel = new JPanel();
        southPanel.add(registerButton);
        southPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
        GestoreFrame.aggiungiFrame(this);
    }

    public void clearInput(){
        usernameTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
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