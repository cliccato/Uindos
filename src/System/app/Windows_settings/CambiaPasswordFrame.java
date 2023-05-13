package System.app.Windows_settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class CambiaPasswordFrame extends JFrame {
    
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton btnAnnulla;
    private JButton btnConferma;
    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private String passwordUtente;

    public void clearInput(){
        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public CambiaPasswordFrame(ImpostazioniWindowsFrame impostazioniWindowsFrame) {
        super("Cambia Password");

        passwordUtente = impostazioniWindowsFrame.getPasswordUtente();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Creazione del pannello per i campi di input
        inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Creazione delle label per i campi di input
        JLabel lblOldPassword = new JLabel("Vecchia Password:");
        JLabel lblNewPassword = new JLabel("Nuova Password:");
        JLabel lblConfirmPassword = new JLabel("Conferma Password:");

        // Creazione dei campi di input
        txtOldPassword = new JPasswordField();
        txtNewPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();

        // Aggiunta dei componenti al pannello di input
        inputPanel.add(lblOldPassword);
        inputPanel.add(txtOldPassword);
        inputPanel.add(lblNewPassword);
        inputPanel.add(txtNewPassword);
        inputPanel.add(lblConfirmPassword);
        inputPanel.add(txtConfirmPassword);

        // Creazione del pannello per i bottoni
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        // Creazione dei bottoni
        btnAnnulla = new JButton("Annulla");
        btnAnnulla.addActionListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnAnnulla.addKeyListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnConferma = new JButton("Conferma");
        btnConferma.addActionListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));
        btnConferma.addKeyListener(new ListenerCambiaPassword(this, impostazioniWindowsFrame));

        // Aggiunta dei bottoni al pannello dei bottoni
        buttonPanel.add(btnAnnulla);
        buttonPanel.add(btnConferma);

        // Aggiunta dei pannelli al frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnAnnulla(){
        return btnAnnulla;
    }

    public JButton getBntConferma(){
        return btnConferma;
    }

    public String getOldPassword(){
        String oldPassword = new String(txtOldPassword.getPassword());
        return oldPassword;
    }

    public String getNewPassword(){
        String newPassword = new String(txtNewPassword.getPassword());
        return newPassword;
    }

    public String getConfirmPassword(){
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        return confirmPassword;
    }

    public String getPasswordUtente(){
        return passwordUtente;
    }
}