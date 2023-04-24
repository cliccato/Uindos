package System.app.Clock;
import java.text.SimpleDateFormat;
import java.util.Date;

import System.Desktop.DesktopFrame;

public class ClockThread extends Thread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private DesktopFrame DF;

    public ClockThread(DesktopFrame DF) {
        this.DF = DF;
        this.start();
    }
    
    public void run() {
        while(true) {
            DF.getLblClock().setText("   " + sdf.format(new Date()) + "   ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ; //TMCH
            }
        }
    }
}
