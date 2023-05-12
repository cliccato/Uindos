package app.UserClock;

/**
 * Avvia tempo
 * 
 * @author Giorgio justin fasullo
 * classe: 4f
 */

/* --- Package --- */
import java.awt.event.*;

public class AvviaTempo implements ActionListener{

    CustomStopWatch timer;
    Thread thread;
    public AvviaTempo(CustomStopWatch timer) {
        this.timer = timer;
        // timer.isRunning = true;
        thread = new Thread(new ContaSecondi(timer));
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Avvia":
                timer.run();
                if (!thread.isAlive()) {
                    thread = new Thread(new ContaSecondi(timer));
                    thread.start();
                    System.out.println("Tempo");
                }
                break;
            case "Stop":
                timer.stop();
                System.out.println("Stop");
                break;
        }
    }
}
