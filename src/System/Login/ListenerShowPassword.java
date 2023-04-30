/**
 * Listener Login
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.1
 */
package System.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ListenerShowPassword implements ActionListener{
    private JButton btnShow;
    private JPasswordField txtPassword;

    public ListenerShowPassword(JButton btnShow, JPasswordField txtPassword) {
        this.btnShow = btnShow;
        this.txtPassword = txtPassword;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Show")) {
            txtPassword.setEchoChar((char) 0);
            btnShow.setText("Hide");
        } else {
            txtPassword.setEchoChar('*');
            btnShow.setText("Show");
        }
    }
}
