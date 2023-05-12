package app.UserClock;
import java.awt.*;
import javax.swing.*;
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
    private ArrayList<Double> parziali;

    public CustomStopWatch() {
        isRunning = false;
        lblTempo = new JLabel("Tempo: 0.0 sec");
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        btnAvvia = new JButton("Avvia");
        btnPausa = new JButton("Pausa");
        btnReset = new JButton("Reset/Stop");
        btnParziale = new JButton("Parziale");
        txtParziali = new JTextArea(10, 15);
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

        parziali = new ArrayList<>();
    }

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

    public void updateTempo() {
        long elapsedTime = System.currentTimeMillis() - startTime - pausedTime;
        double seconds = elapsedTime / 1000.0;
        int hours = (int) (seconds / 3600);
        int minutes = (int) ((seconds % 3600) / 60);
        seconds %= 60;

        DecimalFormat decimalFormat = new DecimalFormat("00");
        lblTempo.setText("Tempo: " + decimalFormat.format(hours) + " hr " +
                decimalFormat.format(minutes) + " min " + new DecimalFormat("0.0").format(seconds) + " sec");
    }

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

    private void registraParziale() {
        if (isRunning) {
            long currentTime = System.currentTimeMillis();
            double elapsedTime = (currentTime - startTime - pausedTime) / 1000.0;
            parziali.add(elapsedTime);
            txtParziali.append(formatTempo(elapsedTime) + "\n");
        }
    }

    private String formatTempo(double tempo) {
        int hours = (int) (tempo / 3600);
        int minutes = (int) ((tempo % 3600) / 60);
        double seconds = tempo % 60;

        DecimalFormat decimalFormat = new DecimalFormat("00.0");
        return decimalFormat.format(hours) + ":" + decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void run() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public JLabel getLblTempo() {
        return lblTempo;
    }

    public long getSecondi() {
        return startTime;
    }
}
