package System.app.Windows_settings;

import System.Desktop.DesktopFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImpostazioniWindowsFrame extends JFrame{

    public ImpostazioniWindowsFrame(String username, String password) {
        super("Impostazioni");

        setIconImage(new ImageIcon("images/logo/impostazioni-logo.png").getImage());
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
        // Creazione dei componenti dell'impostazioni dialog
        JLabel lblTitolo = new JLabel("Impostazioni");
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitolo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel pnlInfoUtente = new JPanel(new GridLayout(2,2));
        JLabel lblNomeUtente = new JLabel(username);
        String passwordString = password;
        char[] pass = passwordString.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i] = '*';
        }
        JLabel lblPasswordUtente = new JLabel(new String(pass));
        JCheckBox checkBoxMostraPassword = new JCheckBox("Mostra password");
        checkBoxMostraPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkBoxMostraPassword.isSelected()) {
                    lblPasswordUtente.setText(password);
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
        
        pnlInfoUtente.add(new JLabel("Nome utente ->"));
        pnlInfoUtente.add(lblNomeUtente);
        pnlInfoUtente.add(checkBoxMostraPassword);
        pnlInfoUtente.add(lblPasswordUtente);

        add(pnlInfoUtente, BorderLayout.CENTER);
        add(lblTitolo, BorderLayout.NORTH);
    }
}