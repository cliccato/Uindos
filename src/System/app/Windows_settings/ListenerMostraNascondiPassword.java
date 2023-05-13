package System.app.Windows_settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class ListenerMostraNascondiPassword implements ActionListener {
    private JPasswordField passwordField;
    private boolean passwordVisibile;
    private JButton btnMostraNascondi;

    public ListenerMostraNascondiPassword(JPasswordField passwordField, JButton btnMostraNascondi) {
        this.passwordField = passwordField;
        this.passwordVisibile = false;
        this.btnMostraNascondi = btnMostraNascondi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (passwordVisibile) {
            passwordField.setEchoChar('*');
            btnMostraNascondi.setText("Mostra Password");
            passwordVisibile = false;
        } else {
            passwordField.setEchoChar((char) 0);
            btnMostraNascondi.setText("Nascondi Password");
            passwordVisibile = true;
        }
    }
}