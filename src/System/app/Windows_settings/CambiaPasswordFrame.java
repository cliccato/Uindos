package System.app.Windows_settings;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import utils.GestoreFrame;
import utils.UindosPath;
import utils.WindowsStyleComponents;

public class CambiaPasswordFrame extends JFrame {

    private JPanel inputPanel;
    private JButton btnAnnulla;
    private JButton btnConferma;
    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private String passwordUtente;
    private JLabel lblRequisitiPassword;
    private JButton btnMostraNascondiOldPassword;
    private JButton btnMostraNascondiNewPassword;
    private JButton btnMostraNascondiConfirmPassword;

    public CambiaPasswordFrame(ImpostazioniWindowsFrame impostazioniWindowsFrame) {
        super("Cambia password");

        passwordUtente = impostazioniWindowsFrame.getPasswordUtente();

        setIconImage(new ImageIcon(UindosPath.IMPOSTAZIONI_LOGO_PATH).getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        inputPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(UindosPath.BACKGROUND_SETTINGS_PATH);
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 5);

        // Creazione delle label per i campi di input
        JLabel lblOldPassword = new JLabel("Vecchia Password:");
        lblOldPassword.setFont(impostazioniWindowsFrame.getFont());
        JLabel lblNewPassword = new JLabel("Nuova Password:");
        lblNewPassword.setFont(impostazioniWindowsFrame.getFont());
        JLabel lblConfirmPassword = new JLabel("Conferma Password:");
        lblConfirmPassword.setFont(impostazioniWindowsFrame.getFont());

        // Creazione dei campi di input
        txtOldPassword = new JPasswordField(20);
        txtOldPassword.setFont(impostazioniWindowsFrame.getFont());
        txtNewPassword = new JPasswordField(20);
        txtNewPassword.setFont(impostazioniWindowsFrame.getFont());
        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setFont(impostazioniWindowsFrame.getFont());

        // Aggiunta dei componenti al pannello di input
        inputPanel.add(lblOldPassword, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtOldPassword, gbc);
        gbc.gridx = 2;
        btnMostraNascondiOldPassword = new JButton("Mostra Password");
        btnMostraNascondiOldPassword.setFont(impostazioniWindowsFrame.getFont());

        inputPanel.add(btnMostraNascondiOldPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblNewPassword, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtNewPassword, gbc);
        gbc.gridx = 2;
        btnMostraNascondiNewPassword = new JButton("Mostra Password");
        btnMostraNascondiNewPassword.setFont(impostazioniWindowsFrame.getFont());
        inputPanel.add(btnMostraNascondiNewPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblConfirmPassword, gbc);
        gbc.gridx = 1;
        inputPanel.add(txtConfirmPassword, gbc);
        gbc.gridx = 2;
        btnMostraNascondiConfirmPassword = new JButton("Mostra Password");
        btnMostraNascondiConfirmPassword.setFont(impostazioniWindowsFrame.getFont());
        inputPanel.add(btnMostraNascondiConfirmPassword, gbc);

        // Creazione dei bottoni
        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setFont(impostazioniWindowsFrame.getFont());
        btnConferma = new JButton("Conferma");
        btnConferma.setFont(impostazioniWindowsFrame.getFont());
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints buttonGBC = new GridBagConstraints();
        buttonGBC.gridx = 0;
        buttonGBC.gridy = 0;
        buttonGBC.insets = new Insets(10, 30, 0, 5); // Modifica dell'inset per spostare i bottoni leggermente a destra
        buttonPanel.add(btnAnnulla, buttonGBC);
        buttonGBC.gridx = 1;
        buttonGBC.insets = new Insets(10, 5, 0, 0); // Modifica dell'inset per spostare i bottoni leggermente a destra
        buttonPanel.add(btnConferma, buttonGBC);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        WindowsStyleComponents.customizeButton(btnAnnulla);
        WindowsStyleComponents.customizeButton(btnConferma);
        WindowsStyleComponents.customizeButton(btnMostraNascondiOldPassword);
        WindowsStyleComponents.customizeButton(btnMostraNascondiNewPassword);
        WindowsStyleComponents.customizeButton(btnMostraNascondiConfirmPassword);

        // Aggiunta Listener
        btnMostraNascondiOldPassword.addActionListener(new ListenerMostraNascondiPassword(txtOldPassword, btnMostraNascondiOldPassword));
        btnMostraNascondiNewPassword.addActionListener(new ListenerMostraNascondiPassword(txtNewPassword, btnMostraNascondiNewPassword));
        btnMostraNascondiConfirmPassword.addActionListener(new ListenerMostraNascondiPassword(txtConfirmPassword, btnMostraNascondiConfirmPassword));
        btnConferma.addActionListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnConferma.addKeyListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnAnnulla.addActionListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnAnnulla.addKeyListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));

        lblRequisitiPassword = new JLabel("<html><font color='red'>Requisiti della password:</font><br>- Deve contenere almeno 8 caratteri<br>- Deve contenere almeno una lettera minuscola<br>- Deve contenere almeno una lettera maiuscola<br>- Deve contenere almeno un numero<br>- Deve contenere almeno un carattere speciale</html>");
        lblRequisitiPassword.setFont(impostazioniWindowsFrame.getFont());
        lblRequisitiPassword.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        inputPanel.add(lblRequisitiPassword, gbc);

        // Aggiunta del pannello di input al frame
        add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
        GestoreFrame.aggiungiFrame(this);
    }

    public void clearInput() {
        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public JButton getBtnAnnulla() {
        return btnAnnulla;
    }

    public JButton getBtnConferma() {
        return btnConferma;
    }

    public String getOldPassword() {
        String oldPassword = new String(txtOldPassword.getPassword());
        return oldPassword;
    }

    public JPasswordField getTxtOldPassword() {
        return txtOldPassword;
    }

    public JPasswordField getTxtNewPassword() {
        return txtNewPassword;
    }

    public JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public String getNewPassword() {
        String newPassword = new String(txtNewPassword.getPassword());
        return newPassword;
    }

    public String getConfirmPassword() {
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        return confirmPassword;
    }

    public String getPasswordUtente() {
        return passwordUtente;
    }
}