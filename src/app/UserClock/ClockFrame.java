/**
 * ClockFrame è una classe che rappresenta la finestra principale dell'applicazione Clock.
 * 
 * La finestra contiene un pannello laterale con pulsanti per le diverse funzionalità dell'applicazione
 * e un pannello di contenuto per visualizzare il contenuto selezionato.
 * 
 * La classe utilizza la libreria Swing per la creazione dell'interfaccia grafica.
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since 2023-05-12
 */

package app.UserClock;

import javax.swing.*;
import java.awt.*;

public class ClockFrame {
    public static final Dimension FRAME_DIMENSION = new Dimension(800, 600);
    private static final String LOGO_PATH = "images/logo/clock-logo.png";

    private JFrame frame;
    private Container container;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton btnTimer;
    private JButton btnStopwatch;
    private JButton btnWorldClock;

    private JLabel contentLabel = new JLabel();

    /**
     * Costruttore della classe ClockFrame.
     * Crea e inizializza i componenti dell'interfaccia grafica.
     */
    public ClockFrame() {
        createComponents();
        setFrame();
    }

    /**
     * Imposta le dimensioni e le impostazioni del frame principale.
     */
    private void setFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(FRAME_DIMENSION);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon(LOGO_PATH).getImage());
    }

    /**
     * Crea e inizializza i componenti dell'interfaccia grafica.
     */
    private void createComponents() {
        frame = new JFrame("Clock");
        container = this.frame.getContentPane();

        // Creazione del pannello laterale
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.DARK_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        sidebarPanel.setLayout(new GridLayout(3, 1));

        // Aggiunta dei pulsanti al pannello laterale
        btnTimer = createSidebarButton("Timer");
        btnStopwatch = createSidebarButton("Stopwatch");
        btnWorldClock = createSidebarButton("World Clock");

        sidebarPanel.add(btnTimer);
        sidebarPanel.add(btnStopwatch);
        sidebarPanel.add(btnWorldClock);

        // Creazione del pannello contenuto
        contentPanel = new JPanel();
        contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Aggiunta dei pannelli alla finestra principale
        container.add(sidebarPanel, BorderLayout.WEST);
        container.add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Crea un pulsante per il pannello laterale.
     * 
     * @param text il testo del pulsante
     * @return il pulsante creato
     */
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

    /**
     * Restituisce l'etichetta di contenuto.
     * 
     * @return l'etichetta di contenuto
     */
    public JLabel getLblContent() {
        return contentLabel;
    }

    /**
     * Restituisce il pannello di contenuto.
     *
     * @return il pannello di contenuto
     */
    public JPanel getPnlContent() {
        return contentPanel;
    }
}