package System.Desktop;
import javax.swing.*;

import System.app.Clock.ClockThread;
import app.Calculator.CalculatorFrame;
import app.Cronometer.CronometerFrame;
import app.Notepad.NotepadFrame;
import app.Terminal.TerminalFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;

public class DesktopFrame {
    //cambiare
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final int APP_HEIGHT = 16;
    public static final int APP_WIDTH = 16;

    public static final String WINDOWS_LOGO_PATH = "images/logo/win-logo.png";
    public static final String FILE_ICON_PATH = "images/icon/file-icon.png";
    public static final String DEFAULT_BACKGROUND_PATH = "images/background/background.jpg";
    public static final String APP_LIST_PATH = "src/applist.csv";

    private ClockThread clock;
    private String username;
    private Dimension frameDimension, appBarDimension;
    private JFrame frame;
    private JPanel imagePanel, southPanel, appBarPanel;
    private JPopupMenu appMenu;
    private JMenu systemMenu, utilsMenu;
    private JMenuItem terminalItem, notepadItem;
    private JButton homeButton;
    private JLabel lblClock;
    private BufferedImage img;

    public DesktopFrame(String username) {//implementare desktop con aree di file distinte per ogni utente
        this.username = username;
        createElements();
        createApp();
        setAppBar();
        clock = new ClockThread(this);

        frame.setSize(frameDimension);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(WINDOWS_LOGO_PATH).getImage());

        imagePanel.setLayout(new GridLayout(3, 5, 1, 1));

        for(int i=0;i<15;i++) {
            JButton b = new JButton(new ImageIcon(FILE_ICON_PATH));
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

        lblClock.setFont(new Font("Arial", Font.PLAIN, 20));
        lblClock.setForeground(Color.WHITE);
        
        /*
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));
        appBarPanel.add(new JButton("Test"));*/


        southPanel.add(homeButton, BorderLayout.WEST);
        southPanel.add(appBarPanel, BorderLayout.CENTER);
        southPanel.add(lblClock, BorderLayout.EAST);

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createElements() {
        frame = new JFrame("Uindos");
        homeButton = new JButton(new ImageIcon(WINDOWS_LOGO_PATH));
        appBarPanel = new JPanel();
        southPanel = new JPanel();
        frameDimension = new Dimension(WIDTH, HEIGHT);
        appBarDimension = new Dimension(APP_HEIGHT, WIDTH - APP_WIDTH);
        lblClock = new JLabel("");
        appMenu = new JPopupMenu();
        systemMenu = new JMenu("System");
        utilsMenu = new JMenu("Utils");

        try {
            img = ImageIO.read(new File(DEFAULT_BACKGROUND_PATH));
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

    // TODO rimpiazzarlo con un ciclo for e un array di stringhe contenente il nome dell'app
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

        utilsMenu.add(new JMenuItem(new AbstractAction("Calculator") {
            public void actionPerformed(ActionEvent e) {
                new CalculatorFrame();
            }
        }));

        utilsMenu.add(new JMenuItem(new AbstractAction("Cronometer") {
            public void actionPerformed(ActionEvent e) {
                new CronometerFrame();
            }
        }));
        
        appMenu.add(new JMenuItem(this.getUsername()));
        appMenu.add(systemMenu);
        appMenu.add(utilsMenu);
    }

    public void setAppBar() {
        File file = new File(APP_LIST_PATH);
        Scanner scanner;

        try {
            Vector<String> v = new Vector<>();
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                v.add(scanner.nextLine());
            }
            scanner.close();

            for (String s : v) {
                String name = s.split(";")[0];
                String logoPath = s.split(";")[1];
                JButton b = new JButton(new ImageIcon(logoPath));
                System.out.println(logoPath);
                b.setText(name);
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                appBarPanel.add(b);
            }
        } catch (FileNotFoundException e) {
            //TMCH
            ;
        }
    }

    public String getUsername() {
        return username;
    }

    public JLabel getLblClock() {
        return lblClock;
    }
}
