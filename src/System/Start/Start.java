package System.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import System.Login.LoginFrame;
import utils.UindosPath;

public class Start {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    private static final int DELAY = 5000;
    JFrame frame;
    JPanel panel;
    Timer timer;

    public Start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                Image logo = new ImageIcon(UindosPath.WINDOWS_LOGO_PATH).getImage();
                int x = (getWidth() - logo.getWidth(null)) / 2;
                int y = (getHeight() - logo.getHeight(null)) / 2;
                g.drawImage(logo, x, y, logo.getWidth(null)*2, logo.getHeight(null)*2, null);
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                frame.dispose();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}