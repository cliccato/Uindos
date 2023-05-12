package app.UserClock;

import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class CustomTimer extends JPanel {

    private JLabel lblTempo;
    private JButton btnAvvia;
    private JButton btnPausa;
    private JButton btnReset;
    private boolean isRunning;
    private long startTime;
    private Timer updateTimer;

    public CustomTimer() {
        isRunning = false;
        lblTempo = new JLabel("Tempo: 0.0 sec");
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        btnAvvia = new JButton("Avvia");
        btnPausa = new JButton("Pausa");
        btnReset = new JButton("Reset/Stop");

        btnAvvia.addActionListener(e -> avviaTempo());
        btnPausa.addActionListener(e -> pausaTempo());
        btnReset.addActionListener(e -> resetTempo());

        setLayout(new BorderLayout());
        add(lblTempo, BorderLayout.NORTH);

        JPanel pnlBottoni = new JPanel();
        pnlBottoni.add(btnAvvia);
        pnlBottoni.add(btnPausa);
        pnlBottoni.add(btnReset);
        add(pnlBottoni, BorderLayout.CENTER);

        Dimension frameDimension = new Dimension(300, 400);
        setPreferredSize(frameDimension);
    }

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


    public void updateTempo() {
        long remainingTime = startTime - System.currentTimeMillis();
        if (remainingTime <= 0) {
            isRunning = false;
            updateTimer.stop();
            btnAvvia.setEnabled(true);
            btnPausa.setEnabled(false);
            lblTempo.setText("Tempo scaduto!");
            Toolkit.getDefaultToolkit().beep(); //aggiunta di alert
            JOptionPane.showMessageDialog(this, "Tempo scaduto!"); // Mostra l'alert
        } else {
            double seconds = remainingTime / 1000.0;
            int hours = (int) (seconds / 3600);
            int minutes = (int) ((seconds % 3600) / 60);
            seconds %= 60;

            DecimalFormat decimalFormat = new DecimalFormat("00");
            lblTempo.setText("Tempo: " + decimalFormat.format(hours) + " hr " +
                    decimalFormat.format(minutes) + " min " + new DecimalFormat("0.0").format(seconds) + " sec");
        }
    }

    private void pausaTempo() {
        if (isRunning) {
            isRunning = false;
            updateTimer.stop();
            btnAvvia.setEnabled(true);
            btnPausa.setEnabled(false);
        }
    }

    private void resetTempo() {
        isRunning = false;
        if (updateTimer != null)
            updateTimer.stop();
        lblTempo.setText("Tempo: " + startTime + "sec");
        btnAvvia.setEnabled(true);
        btnPausa.setEnabled(false);
    }

    public boolean getIsRunning() {
return isRunning;
}
public void run() {
    isRunning = true;
}

public void stop() {
    isRunning = false;
    if (updateTimer != null)
        updateTimer.stop();
    
    updateTimer = new Timer(100, e -> updateTempo());
    lblTempo.setText("Tempo: 0.0 sec");
    btnAvvia.setEnabled(true);
    btnPausa.setEnabled(false);
}

public JLabel getLblTempo() {
    return lblTempo;
}
}