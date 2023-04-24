package System.Login;

import System.Desktop.DesktopFrame;
import javax.swing.*;
import java.awt.*;

public class LoginFrame {
    public LoginFrame(String username) {
        //implementare interfaccia per inserimento password
        //se password corretta avviare un nuovo desktop
        new DesktopFrame(username);
    }
}
