package System.app.AppBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.VirtualBox.VirtualBoxApp;
import app.Calendar.CalendarApp;
import app.Notepad.NotepadFrame;
import app.Paint.PaintApp;

public class AppBarListener implements ActionListener {
    String name;

    public AppBarListener(String name) {
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (name) {
            case "virtualbox":
                new VirtualBoxApp();
                break;
            case "notepad":
                new NotepadFrame();
                break;
            case "paint":
                new PaintApp();
                break;
            case "calendar":
                new CalendarApp();
        }
    }
}