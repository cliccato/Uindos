package System.app.AppBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Calendar.CalendarApp;
import app.Notepad.NotepadFrame;
import app.Paint.PaintApp;

public class AppBarListener implements ActionListener {
    private String name;
    private String username;

    public AppBarListener(String name, String username) {
        this.name = name;
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (name) {
            case "virtualbox":
                // new VirtualBoxApp(username);
                break;
            case "notepad":
                new NotepadFrame(username);
                break;
            case "paint":
                new PaintApp(username);
                break;
            case "calendar":
                new CalendarApp(username);
        }
    }
}