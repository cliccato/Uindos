import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockThread extends Thread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    DesktopFrame DF;

    public ClockThread(DesktopFrame DF) {
        this.DF = DF;
        this.start();
    }

    public void run() {
        while(true) {
            DF.clockLabel.setText("   " + sdf.format(new Date()) + "   ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
