package System.app.Windows_settings;

import System.Desktop.DesktopFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ImpostazioniWindowsFrame extends JFrame {

    private DesktopFrame desktopFrame;
    private JPanel pnlInfoUtente;
    private JScrollPane scrollPane;
    private JLabel lblNomeUtente;
    private JLabel lblPasswordUtente;
    private JCheckBox checkBoxMostraPassword;
    private JLabel lblCambiaPassword;
    private String passwordUtente;
    private String nomeUtente;

    public ImpostazioniWindowsFrame(String username, String password, DesktopFrame desktopFrame) {
        super("Impostazioni");

        this.desktopFrame = desktopFrame;
        setIconImage(new ImageIcon("images/logo/impostazioni-logo.png").getImage());
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creazione dei componenti 
        JLabel lblTitolo = new JLabel("Impostazioni");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlInfoUtente = new JPanel(new GridLayout(6, 2));

        passwordUtente = password;
        nomeUtente = username;

        char[] pass = passwordUtente.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i] = '*';
        }

        lblNomeUtente = new JLabel(username);
        lblPasswordUtente = new JLabel(new String(pass));

        checkBoxMostraPassword = new JCheckBox("Mostra password");
        checkBoxMostraPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBoxMostraPassword.isSelected()) {
                    lblPasswordUtente.setText(passwordUtente);
                } else {
                    String password = lblPasswordUtente.getText();
                    char[] pass = password.toCharArray();
                    for (int i = 0; i < pass.length; i++) {
                        pass[i] = '*';
                    }
                    lblPasswordUtente.setText(new String(pass));
                }
            }
        });

        lblCambiaPassword = new JLabel("<html><u>Cambia password</u></html>");
        lblCambiaPassword.setHorizontalAlignment(SwingConstants.LEFT);
        lblCambiaPassword.setForeground(Color.BLUE);
        lblCambiaPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImpostazioniWindowsFrame myImpostazioniWindowsFrame = this;
        lblCambiaPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new CambiaPasswordFrame(myImpostazioniWindowsFrame);
            }
        });

        pnlInfoUtente.add(new JLabel("Nome utente ->"));
        pnlInfoUtente.add(lblNomeUtente);
        pnlInfoUtente.add(checkBoxMostraPassword);
        pnlInfoUtente.add(lblPasswordUtente);
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(new JSeparator());
        pnlInfoUtente.add(new JSeparator());
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(new JLabel());
        pnlInfoUtente.add(lblCambiaPassword);
        pnlInfoUtente.add(new JLabel());

        scrollPane = new JScrollPane(pnlInfoUtente);

        add(scrollPane, BorderLayout.CENTER);
        add(lblTitolo, BorderLayout.NORTH);
    }

    public void setPasswordUtente(String passwordUtente) {
        this.passwordUtente = passwordUtente;
    }

    public String getPasswordUtente() {
        return passwordUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public JCheckBox getCheckBoxMostraPassword() {
        return checkBoxMostraPassword;
    }

    public JLabel getLblPasswordUtente() {
        return lblPasswordUtente;
    }

    public DesktopFrame getDesktopFrame() {
        return desktopFrame;
    }
}