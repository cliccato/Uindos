/**
 * Listener for showing or hiding a password field.
 * 
 * This class implements the ActionListener interface and listens for actions
 * performed on a button. When the button is clicked, it toggles the visibility
 * of the password characters in a JPasswordField.
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.0
 */

package System.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class ListenerShowPassword implements ActionListener, KeyListener {

    private JButton btnShow;
    private JPasswordField txtPassword;
    private boolean isShowingPassword;

    /**
     * Constructs a new ListenerShowPassword object with the specified JButton and
     * JPasswordField.
     * 
     * @param btnShow     the JButton that toggles the password visibility
     * @param txtPassword the JPasswordField that contains the password characters
     */
    public ListenerShowPassword(JButton btnShow, JPasswordField txtPassword) {
        this.btnShow = btnShow;
        this.txtPassword = txtPassword;
        this.isShowingPassword = false;
    }

    /**
     * Toggles the visibility of the password characters in the JPasswordField.
     * 
     * When the button is clicked, this method toggles the visibility of the
     * password
     * characters in the JPasswordField by setting the echo character to a null
     * character (0) if the password is currently hidden, or to '*' if the password
     * is currently visible. It also updates the text of the button to reflect the
     * current state.
     * 
     * @param e the ActionEvent that triggered the method call
     */
    public void actionPerformed(ActionEvent e) {
        isShowingPassword = !isShowingPassword;
        if (isShowingPassword) {
            txtPassword.setEchoChar((char) 0);
            btnShow.setText("Hide");
        } else {
            txtPassword.setEchoChar('*');
            btnShow.setText("Show");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isShowingPassword = !isShowingPassword;
        if (e.getKeyCode() == KeyEvent.VK_ENTER && isShowingPassword) {
            txtPassword.setEchoChar((char) 0);
            btnShow.setText("Hide");
        } else {
            txtPassword.setEchoChar('*');
            btnShow.setText("Show");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}