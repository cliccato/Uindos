import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

public class DesktopFrame {
    static int HEIGHT, WIDTH, APP_HEIGHT, APP_WIDTH; {
        HEIGHT = 1280;
        WIDTH = 720;
        APP_HEIGHT = APP_WIDTH = 16;
    }

    ClockThread clock;
    Dimension frameDimension, appBarDimension;
    JFrame frame;
    JPanel imagePanel, southPanel, appBarPanel;
    JPopupMenu appMenu;
    JMenu systemMenu, utilsMenu;
    JMenuItem terminalItem, notepadItem;
    JButton homeButton;
    JLabel clockLabel;
    BufferedImage img;

    public DesktopFrame() {
        createElements();
        createApp();
        clock = new ClockThread(this);

        frame.setSize(frameDimension);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("images\\win-logo.png").getImage());

        imagePanel.setLayout(new GridLayout(3, 5, 1, 1));

        for(int i=0;i<15;i++) {
            JButton b = new JButton(new ImageIcon("images\\file-icon.png"));
            b.setText(""+i);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new NotepadFrame();
                }
            });
            
    
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            imagePanel.add(b);
        }

        appMenu.setOpaque(true);
        appMenu.setBackground(Color.WHITE);

        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(new Color(0, 0, 0));

        appBarPanel.setLayout(new GridLayout());
        appBarPanel.setSize(appBarDimension);
        appBarPanel.setBackground(new Color(0, 0, 0));
        appBarPanel.setOpaque(true);

        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appMenu.show(homeButton, 0, 0 - (homeButton.getBounds().height * appMenu.getComponentCount()));
            }
        });

        clockLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        clockLabel.setForeground(Color.WHITE);
        
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));


        southPanel.add(homeButton, BorderLayout.WEST);
        southPanel.add(appBarPanel, BorderLayout.CENTER);
        southPanel.add(clockLabel, BorderLayout.EAST);

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void createElements() {
        frame = new JFrame("Uindos");
        homeButton = new JButton(new ImageIcon("images\\win-logo.png"));
        appBarPanel = new JPanel();
        southPanel = new JPanel();
        frameDimension = new Dimension(HEIGHT, WIDTH);
        appBarDimension = new Dimension(APP_HEIGHT, WIDTH - APP_WIDTH);
        clockLabel = new JLabel("");
        appMenu = new JPopupMenu();
        systemMenu = new JMenu("System");
        utilsMenu = new JMenu("Utils");

        

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

    public void createApp() {
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
    }
}
