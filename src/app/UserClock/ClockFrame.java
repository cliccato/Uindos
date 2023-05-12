package app.UserClock;

import javax.swing.*;
import java.awt.*;

public class ClockFrame {
    public static final Dimension FRAME_DIMENSION = new Dimension(800, 600);

    private JFrame frame;
    private Container container;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton btnTimer;
    private JButton btnStopwatch;
    private JButton btnWorldClock; 

    private JLabel contentLabel = new JLabel();
    public ClockFrame() {
        createComponents();
        setFrame();
    }

    private void setFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(FRAME_DIMENSION);
        frame.setVisible(true);
    }

    private void createComponents() {
        frame = new JFrame("Clock");
        container = this.frame.getContentPane();

        // Creazione del pannello laterale
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.DARK_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        sidebarPanel.setLayout(new GridLayout(3, 1));

        // Aggiunta dei pulsanti al pannello laterale
        // JButton homeButton = createSidebarButton("Home");
        // JButton settingsButton = createSidebarButton("Settings");
        // JButton helpButton = createSidebarButton("Help");
        btnTimer = createSidebarButton("Timer");
        btnStopwatch = createSidebarButton("Stopwatch");
        btnWorldClock = createSidebarButton("World Clock");

        sidebarPanel.add(btnTimer);
        sidebarPanel.add(btnStopwatch);
        sidebarPanel.add(btnWorldClock);

        // Creazione del pannello contenuto
        contentPanel = new JPanel();
        // contentPanel.setLayout(new BorderLayout());

        // Aggiunta del contenuto al pannello contenuto
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Aggiunta dei pannelli alla finestra principale
        container.add(sidebarPanel, BorderLayout.WEST);
        container.add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(180, 50));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(new ListenerSideBar(this));

        return button;
    }

    public JLabel getLblContent() {
        return contentLabel;
    }

    public JPanel getPnlContent() {
        return contentPanel;
    }
}