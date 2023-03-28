import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CronometerFrame extends JFrame implements ActionListener, Runnable {
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private volatile boolean running;

    public CronometerFrame() {
        super("Cronometro");
        super.setSize(new Dimension(500, 500));
        super.setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        label = new JLabel("0");
        this.add(label, BorderLayout.NORTH);

        startButton = new JButton("Start");
        panel.add(startButton);
        startButton.addActionListener(this);

        stopButton = new JButton("Stop");
        panel.add(stopButton);
        stopButton.addActionListener(this);

        this.add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == startButton) {
            Thread t = new Thread(this);
            t.start();
        } else if (evt.getSource() == stopButton) {
            running = false;
        }
    }

    public void run() {
        running = true;
        int count = 1;
        while (running) {
            label.setText(String.valueOf(count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //TMCH
            }
            count++;
        }
    }

}