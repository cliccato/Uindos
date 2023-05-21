/**
 * Questa classe rappresenta un timer personalizzato.
 * Mostra il tempo rimanente e fornisce funzionalità per avviare, mettere in pausa e ripristinare il timer.
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since 2023-05-12
 */

package app.UserClock;
import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
public class CustomTimer extends JPanel {

    private JLabel lblTempo;
    private JButton btnAvvia;
    private JButton btnPausa;
    private boolean isRunning;
    private long startTime;
    private Timer updateTimer;

    /**
     * Crea un'istanza di CustomTimer.
     * Inizializza i componenti grafici e imposta il layout.
     */
    public CustomTimer(Font font) {
        isRunning = false;
        lblTempo = new JLabel("Tempo: 0.0 sec");
        lblTempo.setFont(font);
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        btnAvvia = new JButton("Avvia");
        btnAvvia.setFont(font);
        btnPausa = new JButton("Pausa");
        btnPausa.setFont(font);

        btnAvvia.addActionListener(e -> avviaTempo());
        btnPausa.addActionListener(e -> pausaTempo());

        setLayout(new BorderLayout());
        add(lblTempo, BorderLayout.NORTH);

        JPanel pnlBottoni = new JPanel();
        pnlBottoni.add(btnAvvia);
        pnlBottoni.add(btnPausa);
        add(pnlBottoni, BorderLayout.CENTER);

        Dimension frameDimension = new Dimension(300, 400);
        setPreferredSize(frameDimension);
    }

    /**
     * Avvia il timer.
     * Se il timer non è in esecuzione, imposta il tempo di inizio e avvia il timer.
     * Se il tempo di inizio non è stato impostato, richiede all'utente di inserirlo.
     */
    private void avviaTempo() {
        if (!isRunning) {
            isRunning = true;
            if (startTime == 0) {
                String input = JOptionPane.showInputDialog("Inserisci il tempo di inizio (secondi):");
                try {
                    long tempoInizio = Long.parseLong(input) * 1000;
                    if (tempoInizio >= 0) {
                        startTime = System.currentTimeMillis() + tempoInizio;
                    } else {
                        JOptionPane.showMessageDialog(this, "Il tempo di inizio deve essere un numero positivo.");
                        isRunning = false;
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Inserisci un valore numerico valido per il tempo di inizio.");
                    isRunning = false;
                    return;
                }
            }
            updateTimer = new Timer(100, e -> updateTempo());
            updateTimer.start();
            btnAvvia.setEnabled(false);
            btnPausa.setEnabled(true);
        }
    }

    /**
     * Aggiorna il tempo rimanente del timer.
     * Se il tempo rimanente è scaduto, ferma il timer e mostra un messaggio di avviso.
     */
    public void updateTempo() {
        long remainingTime = startTime - System.currentTimeMillis();
        if (remainingTime <= 0) {
            isRunning = false;
            startTime = 0;
            updateTimer.stop();
            btnAvvia.setEnabled(true);
            lblTempo.setText("Tempo scaduto!");
            Toolkit.getDefaultToolkit().beep(); //aggiunta di alert
            JOptionPane.showMessageDialog(this, "Tempo scaduto!"); // Mostra l'alert
        } else {
            double seconds = remainingTime / 1000.0;
            int hours = (int)(seconds / 3600);
            int minutes = (int)((seconds % 3600) / 60);
            seconds %= 60;
            DecimalFormat decimalFormat = new DecimalFormat("00");
            lblTempo.setText("Tempo: " + decimalFormat.format(hours) + " hr " +
            decimalFormat.format(minutes) + " min " + new DecimalFormat("0.0").format(seconds) + " sec");
        }
    }

    /**
     * Mette in pausa il timer.
     * Se il timer è in esecuzione, lo mette in pausa e disabilita il pulsante di pausa.
     */
    private void pausaTempo() {
        if (isRunning) {
            isRunning = false;
            updateTimer.stop();
            btnAvvia.setEnabled(true);
            btnPausa.setEnabled(false);
        }
    }

    /**
     * Restituisce lo stato di esecuzione del timer.
     * @return true se il timer è in esecuzione, false altrimenti
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Avvia il timer.
     * Imposta lo stato di esecuzione a true.
     */
    public void run() {
        isRunning = true;
    }

    /**
     * Ferma il timer.
     * Imposta lo stato di esecuzione a false e ferma il timer.
     */
    public void stop() {
        isRunning = false;
        if (updateTimer != null)
            updateTimer.stop();

        updateTimer = new Timer(100, e -> updateTempo());
        lblTempo.setText("Tempo: 0.0 sec");
        btnAvvia.setEnabled(true);
        btnPausa.setEnabled(false);
    }

    /**
     * Restituisce l'etichetta del tempo del timer.
     * @return l'etichetta del tempo
     */
    public JLabel getLblTempo() {
        return lblTempo;
    }
}