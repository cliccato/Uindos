import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Desktop {
    static int HEIGHT, WIDTH, APP_HEIGHT, APP_WIDTH, APPS_NUMBER; {
        HEIGHT = 1280;
        WIDTH = 720;
        APP_HEIGHT = APP_WIDTH = 16;
        APPS_NUMBER = 0;
    }

    Dimension frameDimension, appBarDimension;
    JFrame frame;
    JPanel imagePanel, appBarPanel;
    JPopupMenu appMenu;
    Vector<JMenuItem> appMenuItems;
    JButton homeButton;
    BufferedImage img;

    public Desktop() {
        createElements();

        frame.setSize(frameDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        appBarPanel.setLayout(new FlowLayout());
        appBarPanel.setSize(appBarDimension);
        appBarPanel.setBackground(new Color(0, 0, 0));

        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setComponentPopupMenu(appMenu);

        for (JMenuItem jMenuItem : appMenuItems) { appMenu.add(jMenuItem); }

        appBarPanel.add(homeButton);

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(appBarPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createElements() {
        frame = new JFrame("Uindos");
        homeButton = new JButton("APPS", new ImageIcon("images\\win-logo.png"));
        appBarPanel = new JPanel();
        frameDimension = new Dimension(HEIGHT, WIDTH);
        appBarDimension = new Dimension(APP_HEIGHT, APP_WIDTH);
        appMenu = new JPopupMenu();
        appMenuItems = new Vector<JMenuItem>();
        for (int i = 0; i < APPS_NUMBER; i++) {
            appMenuItems.add(new JMenuItem());
        }

        try {
            img = ImageIO.read(new File("images\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        };
    }
}
