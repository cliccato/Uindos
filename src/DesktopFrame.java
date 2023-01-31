import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DesktopFrame {
    static int HEIGHT, WIDTH, APP_HEIGHT, APP_WIDTH; {
        HEIGHT = 1280;
        WIDTH = 720;
        APP_HEIGHT = APP_WIDTH = 16;
    }

    Dimension frameDimension, appBarDimension;
    JFrame frame;
    JPanel imagePanel, appBarPanel;
    JPopupMenu appMenu;
    JMenu systemMenu, utilsMenu;
    JMenuItem terminalItem, notepadItem;
    JButton homeButton;
    BufferedImage img;

    public DesktopFrame() {
        createElements();

        frame.setSize(frameDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("images\\win-logo.png").getImage());

        appMenu.setOpaque(true);
        appMenu.setBackground(Color.WHITE);

        appBarPanel.setLayout(new FlowLayout());
        appBarPanel.setSize(appBarDimension);
        appBarPanel.setBackground(new Color(0, 0, 0));

        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appMenu.show(homeButton, 0, 0 - (homeButton.getBounds().height * appMenu.getComponentCount()));
            }
        });

        appBarPanel.add(homeButton);
        appBarPanel.add(new JButton("Test"));

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(appBarPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createElements() {
        frame = new JFrame("Uindos");
        homeButton = new JButton(new ImageIcon("images\\win-logo.png"));
        appBarPanel = new JPanel();
        frameDimension = new Dimension(HEIGHT, WIDTH);
        appBarDimension = new Dimension(APP_HEIGHT, APP_WIDTH);
        appMenu = new JPopupMenu();
        systemMenu = new JMenu("System");
        utilsMenu = new JMenu("Utils");

        systemMenu.add(new JMenuItem(new AbstractAction("Terminal") {
            public void actionPerformed(ActionEvent e) {
                new TerminalFrame();
            }
        }));

        
        utilsMenu.add(new JMenuItem(new AbstractAction("Notepad") {
            public void actionPerformed(ActionEvent e) {
                new NotepadFrame();
            }
        }));
        
        appMenu.add(systemMenu);
        appMenu.add(utilsMenu);

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
