/**
    The ListenerLogin class represents an ActionListener that handles the login process.
    It checks the user's credentials by reading them from a configuration file and
    authenticates the user if their credentials are valid.
    If authentication is successful, it opens a DesktopFrame for the user.
    If authentication fails, it alerts the user and clears the input fields.

    @author Giorgio Justin Fasullo
    @version 1.1
*/

package System.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import System.Desktop.DesktopFrame;

public class ListenerLogin implements ActionListener{
    private LoginFrame loginFrame;
    private final String USERS_FILE_PATH = "system69/config/SAM_IL_POMPIERE.conf";
    private final String FIELD_DELIMITATOR = "|"; //da spostare

    /**
     * Constructs a new ListenerLogin object with the given LoginFrame.
     * 
     * @param loginFrame the LoginFrame to use
     */
    public ListenerLogin(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    /**
     * Authenticates the user by checking if their credentials are valid.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    private boolean authenticateUser() {
        String usernameInsert = loginFrame.getUsername();
        String passwordInsert = loginFrame.getPassword();
        try (BufferedReader fIN = new BufferedReader(new FileReader(new File(USERS_FILE_PATH)))) {
            String fileLine;
            while ((fileLine = fIN.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(fileLine, FIELD_DELIMITATOR);
                String username = stringTokenizer.nextToken();
                String password = stringTokenizer.nextToken();
                if (usernameInsert.equals(username) && passwordInsert.equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Called when the user clicks the "Login" button.
     * Authenticates the user and opens the DesktopFrame if successful,
     * otherwise alerts the user and clears the input fields.
     * 
     * @param e the ActionEvent that occurred
     */
    public void actionPerformed(ActionEvent e) {
        if (authenticateUser()) {
            loginFrame.setFrameNotVisible();
            new DesktopFrame(loginFrame.getUsername());
        } else {
            loginFrame.alertUserNotFound();
            loginFrame.clearInput();
        }
    }
}