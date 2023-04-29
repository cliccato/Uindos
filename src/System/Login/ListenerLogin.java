/**
 * Listener Login
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.1
 */

/* --- Package --- */
package System.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;

import System.Desktop.DesktopFrame;

public class ListenerLogin implements ActionListener{

    private LoginFrame loginFrame;
    private final String USERS_FILE_PATH = "users/users.csv";
    public ListenerLogin(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    private boolean checkCredentials() {
        String usernameInsert = loginFrame.getUsername();
        String passwordInsert = loginFrame.getPassword();
        String username;
        String password;
        boolean isUserFound = false;
        StringTokenizer stringTokenizer;
        try  {
            FileReader f = new FileReader(new File(USERS_FILE_PATH));
            BufferedReader fIN = new BufferedReader(f);

            stringTokenizer = new StringTokenizer(fIN.readLine(), ";");
            while (stringTokenizer.hasMoreTokens() && !isUserFound) {
                username = stringTokenizer.nextToken();
                password = stringTokenizer.nextToken();
                if (usernameInsert.equals(username) && passwordInsert.equals(password)) {
                    isUserFound = true;
                }
                stringTokenizer = new StringTokenizer(fIN.readLine(), ";");
            }
            fIN.close();
        } catch(Exception e) {

        }
        if (isUserFound) {
            return true;
        }    
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (checkCredentials()) {
            loginFrame.setFrameNotVisible();
            new DesktopFrame(loginFrame.getUsername());
        } else {
            loginFrame.alertUserNotFound();
        }
    }
}
