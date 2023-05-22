package System.Start;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
                String name = "Uindos";
                int nameWidth = g.getFontMetrics().stringWidth(name);
                Image logo = new ImageIcon(UindosPath.WINDOWS_LOGO_PATH).getImage();
                int x = (getWidth()/2) - (logo.getWidth(null)*2) + (nameWidth);
                int y = (getHeight()/2) - (logo.getHeight(null)*2);
                g.drawImage(logo, x, y, logo.getWidth(null)*2, logo.getHeight(null)*2, null);
                
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
                g.setColor(Color.WHITE);
                
                int nameX = getWidth()/2 - nameWidth;
                int nameY = y - 20;
                g.drawString(name, nameX, nameY);
                g.drawString("© 2023 IlTeam", getWidth() -200, getHeight() -20);
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        playSound(UindosPath.WINDOWS_STARTUP_SOUND_PATH);

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginFrame();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    private static void playSound(String soundPath) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}