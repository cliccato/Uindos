package System.app.Clock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import System.Desktop.DesktopFrame;

public class ClockThread extends Thread {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DesktopFrame DF;

    public ClockThread(DesktopFrame DF) {
        this.DF = DF;
        this.start();
    }
    
    public void run() {
        while(true) {

            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            String todaysdate = dateFormat.format(date);
            DF.getLblClock().setText("   " + sdf.format(new Date()) + "   ");
            DF.getLblDate().setText(todaysdate);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ; //TMCH
            }
        }
    }
}