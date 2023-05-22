package System.Desktop;

import javax.swing.*;
import System.Login.LoginFrame;
import System.app.Clock.ClockThread;
import System.app.Windows_settings.ImpostazioniWindowsFrame;
import app.Calculator.CalculatorFrame;
import app.Notepad.NotepadFrame;
import app.Terminal.TerminalFrame;
import app.UserClock.ClockFrame;
import utils.Config;
import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosPath;
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

    private static String username;
    private String password;
    private Dimension frameDimension, appBarDimension;
    private JFrame frame;
    private JPanel imagePanel, southPanel, appBarPanel, dateClockPanel;
    private JPopupMenu appMenu;
    private JMenu systemMenu, utilsMenu, user;
    private JMenuItem itemLogOut, itemExit, itemSettings;
    private JButton homeButton;
    private JLabel lblClock, lblDate;
    private BufferedImage img;
    private Point initialLocation;
    private Config config;

    public DesktopFrame(String username, String password) {// implementare desktop con aree di file distinte per ogni utente
        DesktopFrame.username = username;
        this.password = password;
        config = GestoreConfig.loadConfig(username);
        createElements();
        createApp();
        setAppBar();

        frame.setSize(frameDimension);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(UindosPath.WINDOWS_LOGO_PATH).getImage());

        imagePanel.setLayout(new GridLayout(3, 5, 1, 1));

        setDesktop();

        appMenu.setOpaque(true);
        appMenu.setBackground(Color.WHITE);

        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(new Color(0, 0, 0));

        appBarPanel.setLayout(new FlowLayout());
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
        lblClock.setPreferredSize(new Dimension(10, 10));
        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDate.setForeground(Color.WHITE);
        lblDate.setPreferredSize(new Dimension(10, 10));
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);

        dateClockPanel.setPreferredSize(new Dimension(115, 30));
        dateClockPanel.setBackground(Color.BLACK);
        dateClockPanel.add(lblClock);
        dateClockPanel.add(lblDate);

        southPanel.add(homeButton, BorderLayout.WEST);
        southPanel.add(appBarPanel, BorderLayout.CENTER);
        southPanel.add(dateClockPanel, BorderLayout.EAST);

        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createElements() {
        frame = new JFrame("Uindos");
        homeButton = new JButton(new ImageIcon(UindosPath.WINDOWS_LOGO_PATH));
        appBarPanel = new JPanel();
        southPanel = new JPanel();
        dateClockPanel = new JPanel(new GridLayout(2, 1));
        frameDimension = new Dimension(WIDTH, HEIGHT);
        appBarDimension = new Dimension(APP_HEIGHT, WIDTH - APP_WIDTH);
        lblClock = new JLabel("");
        lblClock.setFont(config.getFont());
        lblDate = new JLabel("");
        lblDate.setFont(config.getFont());
        appMenu = new JPopupMenu();
        appMenu.setFont(config.getFont());
        systemMenu = new JMenu("System");
        systemMenu.setFont(config.getFont());
        utilsMenu = new JMenu("Utils");
        utilsMenu.setFont(config.getFont());

        new ClockThread(this);

        try {
            System.out.println(config.getBackground()); 
            img = ImageIO.read(new File(config.getBackground()));
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
                new TerminalFrame(username);
            }
        })).setFont(config.getFont());;

        systemMenu.add(new JMenuItem(new AbstractAction("Notepad") {
            public void actionPerformed(ActionEvent e) {
                new NotepadFrame(username);
            }
        })).setFont(config.getFont());;

        utilsMenu.add(new JMenuItem(new AbstractAction("Calculator") {
            public void actionPerformed(ActionEvent e) {
                new CalculatorFrame(username);
            }
        })).setFont(config.getFont());;
        utilsMenu.add(new JMenuItem(new AbstractAction("Clock") {
            public void actionPerformed(ActionEvent e) {
                new ClockFrame(username);
            }
        })).setFont(config.getFont());;
        
        itemLogOut = new JMenuItem(new AbstractAction("Logout") {
            public void actionPerformed(ActionEvent e) {
                GestoreFrame.chiudiTuttiFrame();
                new LoginFrame();
            }
        });
        itemLogOut.setFont(config.getFont());

        itemExit = new JMenuItem(new AbstractAction("Power off") {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        itemExit.setFont(config.getFont());

        DesktopFrame myDesktopFrame = this;
        itemSettings = new JMenuItem(new AbstractAction("Settings") {
            public void actionPerformed(ActionEvent e) {
                new ImpostazioniWindowsFrame(username, password, myDesktopFrame);
            }
        });
        itemSettings.setFont(config.getFont());

        user = new JMenu(DesktopFrame.getUsername());
        user.setFont(config.getFont());
        user.add(itemSettings);
        user.add(new JSeparator());
        user.add(itemLogOut);
        user.add(itemExit);

        appMenu.add(user);
        appMenu.add(systemMenu);
        appMenu.add(utilsMenu);
    }

    public void setDesktop() {
        File file = new File(UindosPath.DESKTOP_LIST_PATH);
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
                JButton b = new JButton(new ImageIcon(logoPath.replace("/", File.separator)));
                b.setText(name);
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                b.setFont(config.getFont());
                
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
                            new DesktopListener(name, username);
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
        File file = new File(UindosPath.APP_LIST_PATH);
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
                JButton b = new JButton(new ImageIcon(logoPath.replace("/", File.separator)));
                b.setPreferredSize(new Dimension(40, 30));
                b.setMaximumSize(new Dimension(40, 30));
                b.setMinimumSize(new Dimension(40, 30));
                b.setAlignmentY(Component.CENTER_ALIGNMENT);
                b.addActionListener(new AppBarListener(name, username));
                appBarPanel.add(b);
            }
        } catch (FileNotFoundException e) {
            // TMCH
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

    public JLabel getLblDate(){
        return lblDate;
    }

    public JPanel getImagePanel(){
        return imagePanel;
    }
}