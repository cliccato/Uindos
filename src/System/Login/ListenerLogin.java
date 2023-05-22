package System.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import System.Desktop.DesktopFrame;
import utils.UindosPath;

public class ListenerLogin implements ActionListener, KeyListener {
    private LoginFrame loginFrame;
    public static final String FIELD_DELIMITATOR = "|";

    public ListenerLogin(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    private boolean authenticateUser() {
        String usernameInsert = loginFrame.getUsername();
        String passwordInsert = loginFrame.getPassword();
        try (BufferedReader fIN = new BufferedReader(new FileReader(new File(UindosPath.USERS_FILE_PATH)))) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (authenticateUser()) {
            loginFrame.closeFrame();
            new DesktopFrame(loginFrame.getUsername(), loginFrame.getPassword());
        } else {
            loginFrame.alertUserNotFound();
            loginFrame.clearInput();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && authenticateUser()) {
            loginFrame.closeFrame();
            new DesktopFrame(loginFrame.getUsername(), loginFrame.getPassword());
        } else {
            loginFrame.alertUserNotFound();
            loginFrame.clearInput();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}