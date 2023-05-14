package System.Desktop;

import javax.swing.*;
import System.Desktop.DesktopListener;
import System.Login.LoginFrame;
import System.app.Clock.ClockThread;
import System.app.Windows_settings.ImpostazioniWindowsFrame;
import app.Calculator.CalculatorFrame;
import app.Notepad.NotepadFrame;
import app.Terminal.TerminalFrame;
import app.UserClock.ClockFrame;
import utils.GestoreFrame;
import System.app.AppBar.AppBarListener;
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
    // cambiare
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final int APP_HEIGHT = 16;
    public static final int APP_WIDTH = 16;

    public static final String WINDOWS_LOGO_PATH = "images/logo/win-logo.png";
    public static final String FILE_ICON_PATH = "images/icon/file-icon.png";
    public static final String APP_LIST_PATH = "src/applist.csv";
    public static final String DESKTOP_LIST_PATH = "src/desktoplist.csv";
    public static final String DEFAULT_BACKGROUND_PATH = "images/background/background01.png";

    private ClockThread clock;
    private static String username;
    private String password;
    private Dimension frameDimension, appBarDimension;
    private JFrame frame;
    private JPanel imagePanel, southPanel, appBarPanel;
    private JPopupMenu appMenu;
    private JMenu systemMenu, utilsMenu;
    private JMenuItem itemLogOut, itemExit, itemSettings;
    private JButton homeButton;
    private JLabel lblClock;
    private BufferedImage img;
    private Point initialLocation;
    private JMenu user;

    public DesktopFrame(String username, String password) {// implementare desktop con aree di file distinte per ogni utente
        DesktopFrame.username = username;
        this.password = password;
        createElements();
        createApp();
        setAppBar();

        frame.setSize(frameDimension);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(WINDOWS_LOGO_PATH).getImage());

        imagePanel.setLayout(new GridLayout(3, 5, 1, 1));

        setDesktop();

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
                appMenu.show(homeButton, 0, 0 - (homeButton.getBounds().height * appMenu.getComponentCount() - 50));
            }
        });

        lblClock.setFont(new Font("Arial", Font.PLAIN, 20));
        lblClock.setForeground(Color.WHITE);

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

        clock = new ClockThread(this);

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

    public void createApp() {
        systemMenu.add(new JMenuItem(new AbstractAction("Terminal") {
            public void actionPerformed(ActionEvent e) {
                new TerminalFrame();
            }
        }));

        systemMenu.add(new JMenuItem(new AbstractAction("Notepad") {
            public void actionPerformed(ActionEvent e) {
                new NotepadFrame();
            }
        }));

        utilsMenu.add(new JMenuItem(new AbstractAction("Calculator") {
            public void actionPerformed(ActionEvent e) {
                new CalculatorFrame();
            }
        }));
        utilsMenu.add(new JMenuItem(new AbstractAction("Clock") {
            public void actionPerformed(ActionEvent e) {
                new ClockFrame();
            }
        }));

        itemLogOut = new JMenuItem(new AbstractAction("Logout") {
            public void actionPerformed(ActionEvent e) {
                GestoreFrame.chiudiTuttiFrame();
                new LoginFrame();
            }
        });

        itemExit = new JMenuItem(new AbstractAction("Power off") {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        DesktopFrame myDesktopFrame = this;
        itemSettings = new JMenuItem(new AbstractAction("Settings") {
            public void actionPerformed(ActionEvent e) {
                new ImpostazioniWindowsFrame(username, password, myDesktopFrame);
            }
        });

        user = new JMenu(DesktopFrame.getUsername());
        user.add(itemSettings);
        user.add(new JSeparator());
        user.add(itemLogOut);
        user.add(itemExit);

        appMenu.add(user);
        appMenu.add(systemMenu);
        appMenu.add(utilsMenu);
    }

    public void setDesktop() {
        File file = new File(DESKTOP_LIST_PATH);
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
                b.setText(name);
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);

                b.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        initialLocation = e.getPoint();
                    }
                });

                b.addMouseMotionListener(new MouseAdapter() {
                    public void mouseDragged(MouseEvent e) {
                        int dx = e.getX() - initialLocation.x;
                        int dy = e.getY() - initialLocation.y;
                        int newX = b.getX() + dx;
                        int newY = b.getY() + dy;
    
                        if (newX < 0) {
                            newX = 0;
                        }
                        if (newY < 0) {
                            newY = 0;
                        }
                        if (newX + b.getWidth() > frame.getWidth()) {
                            newX = frame.getWidth() - b.getWidth();
                        }
                        if (newY + b.getHeight() > frame.getHeight()) {
                            newY = frame.getHeight() - b.getHeight();
                        }
                        
                        b.setLocation(newX, newY);
                    }
                });

                b.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        if(e.getClickCount()==2){
                            new DesktopListener(name);
                        }
                    }
                });

                imagePanel.add(b);
            }

            for (int i = 0; i < 15-v.size(); i++) {
                JButton b = new JButton("");
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                imagePanel.add(b);
            }
        } catch (FileNotFoundException e) {
            ; //TMCH
        }
        GestoreFrame.aggiungiFrame(frame);  
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
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                b.addActionListener(new AppBarListener(name));
                appBarPanel.add(b);
            }
        } catch (FileNotFoundException e) {
            ; //TMCH
        }
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public JLabel getLblClock() {
        return lblClock;
    }

    public JPanel getImagePanel(){
        return imagePanel;
    }
    
    // public void disableVisibility() {
    //     frame.setVisible(false);
    // }
}