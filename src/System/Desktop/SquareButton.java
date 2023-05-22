package System.Desktop;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SquareButton extends JButton {
    public SquareButton(ImageIcon icon) {
        super(icon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }
}