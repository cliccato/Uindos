package app.UserClock;

/**
 * Conta secondi
 * 
 * @author Giorgio justin fasullo
 * classe: 4f
 */

public class ContaSecondi implements Runnable{
   
    CustomStopWatch timer; 
    public ContaSecondi(CustomStopWatch timer) {
        this.timer = timer;
    }

    public void run() {
        try {
            while(timer.getIsRunning()) {
                // Thread.sleep(1000);
                timer.updateTempo();
                timer.getLblTempo().setText("Avvia:" + timer.getSecondi());
            }
        } catch(Exception e) {
            System.out.println("Errore " + e);
        }
    }
}
