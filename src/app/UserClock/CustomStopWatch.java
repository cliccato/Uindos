/**
 * CustomStopWatch è una classe che estende JPanel e rappresenta un cronometro personalizzato.
 * 
 * Fornisce funzionalità per avviare, mettere in pausa, ripristinare e registrare parziali durante la misurazione del tempo.
 * Il tempo viene visualizzato su un'etichetta e i parziali vengono mostrati in una JTextArea.
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since 2023-05-12
 */

package app.UserClock;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class CustomStopWatch extends JPanel {

    private JLabel lblTempo;
    private JButton btnAvvia;
    private JButton btnPausa;
    private JButton btnReset;
    private JButton btnParziale;
    private JTextArea txtParziali;
    private boolean isRunning;
    private long startTime;
    private long pausedTime;
    private Timer updateTimer;
    private ArrayList <Double> parziali;

    /**
     * Crea un'istanza di CustomStopWatch.
     */
    public CustomStopWatch(Font font) {
        isRunning = false;
        lblTempo = new JLabel("Tempo: 0.0 sec");
        lblTempo.setFont(font);
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        btnAvvia = new JButton("Avvia");
        btnAvvia.setFont(font);
        btnPausa = new JButton("Pausa");
        btnPausa.setFont(font);
        btnReset = new JButton("Reset/Stop");
        btnReset.setFont(font);
        btnParziale = new JButton("Parziale");
        btnParziale.setFont(font);
        txtParziali = new JTextArea(10, 15);
        txtParziali.setFont(font);
        txtParziali.setEditable(false);
        txtParziali.setLineWrap(true);
        txtParziali.setWrapStyleWord(true);

        btnAvvia.addActionListener(e -> avviaTempo());
        btnPausa.addActionListener(e -> pausaTempo());
        btnReset.addActionListener(e -> resetTempo());
        btnParziale.addActionListener(e -> registraParziale());

        setLayout(new BorderLayout());
        add(lblTempo, BorderLayout.NORTH);

        JPanel pnlBottoni = new JPanel();
        pnlBottoni.add(btnAvvia);
        pnlBottoni.add(btnPausa);
        pnlBottoni.add(btnReset);
        pnlBottoni.add(btnParziale);
        add(pnlBottoni, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(txtParziali);
        add(scrollPane, BorderLayout.SOUTH);

        Dimension frameDimension = new Dimension(300, 400);
        setPreferredSize(frameDimension);

        parziali = new ArrayList < > ();
    }

    /**
     * Avvia il cronometro.
     */
    private void avviaTempo() {
        if (!isRunning) {
            isRunning = true;
            if (pausedTime == 0) {
                startTime = System.currentTimeMillis();
            } else {
                startTime = System.currentTimeMillis() - pausedTime;
                pausedTime = 0;
            }
            updateTimer = new Timer(100, e -> updateTempo());
            updateTimer.start();
            btnAvvia.setEnabled(false);
            btnPausa.setEnabled(true);
            btnParziale.setEnabled(true);
        }
    }

    /**
     * Aggiorna il tempo visualizzato sull'etichetta del cronometro.
     */
    public void updateTempo() {
        long elapsedTime = System.currentTimeMillis() - startTime - pausedTime;
        double seconds = elapsedTime / 1000.0;
        int hours = (int)(seconds / 3600);
        int minutes = (int)((seconds % 3600) / 60);
        seconds %= 60;

        DecimalFormat decimalFormat = new DecimalFormat("00");
        lblTempo.setText("Tempo: " + decimalFormat.format(hours) + " hr " +
            decimalFormat.format(minutes) + " min " + new DecimalFormat("0.0").format(seconds) + " sec");
    }

    /**
     * Mette in pausa il cronometro.
     */
    private void pausaTempo() {
        if (isRunning) {
            isRunning = false;
            pausedTime = System.currentTimeMillis() - startTime;
            updateTimer.stop();
            btnAvvia.setEnabled(true);
            btnPausa.setEnabled(false);
            btnParziale.setEnabled(false);
        }
    }

    /**
     * Resettta il cronometro e cancella i parziali registrati.
     */
    private void resetTempo() {
        isRunning = false;
        if (updateTimer != null)
            updateTimer.stop();
        lblTempo.setText("Tempo: 0.0 sec");
        btnAvvia.setEnabled(true);
        btnPausa.setEnabled(false);
        btnParziale.setEnabled(false);
        pausedTime = 0;
        parziali.clear();
        txtParziali.setText("");
    }

    /**
     * Registra un parziale nel cronometro.
     */
    private void registraParziale() {
        if (isRunning) {
            long currentTime = System.currentTimeMillis();
            double elapsedTime = (currentTime - startTime - pausedTime) / 1000.0;
            parziali.add(elapsedTime);
            txtParziali.append(formatTempo(elapsedTime) + "\n");
        }
    }

    /**
     * Formatta il tempo nel formato hh:mm:ss.
     *
     * @param tempo il tempo da formattare in secondi
     * @return il tempo formattato come stringa
     */
    private String formatTempo(double tempo) {
        int hours = (int)(tempo / 3600);
        int minutes = (int)((tempo % 3600) / 60);
        double seconds = tempo % 60;

        DecimalFormat decimalFormat = new DecimalFormat("00.0");
        return decimalFormat.format(hours) + ":" + decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }

    /**
     * Restituisce lo stato di esecuzione del cronometro.
     *
     * @return true se il cronometro è in esecuzione, false altrimenti
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Avvia il cronometro.
     */
    public void run() {
        isRunning = true;
    }

    /**
     * Ferma il cronometro.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Restituisce l'etichetta del tempo del cronometro.
     *
     * @return l'etichetta del tempo
     */
    public JLabel getLblTempo() {
        return lblTempo;
    }

    /**
     * Restituisce il tempo iniziale del cronometro.
     *
     * @return il tempo iniziale del cronometro
     */
    public long getSecondi() {
        return startTime;
    }
}