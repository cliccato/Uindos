package System.app.AppBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Hanged.Hanged;
import app.Tris.TrisFrame;
import app.VirtualBox.VirtualBoxApp;
import app.indovina_immagini.src.GestioneIndovinaImmagineGUI;
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
            case "indovina_immagine":
                new GestioneIndovinaImmagineGUI();
                break;
            case "hanged":
                new Hanged();
                break;
            case "tris":
                new TrisFrame();
                break;
            case "paint":
                new PaintApp();
                break;
        }
    }
}
