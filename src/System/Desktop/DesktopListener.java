package System.Desktop;

import java.io.File;

import javax.swing.JFileChooser;

import app.Browser.BrowserApp;
import app.Hanged.Hanged;
import app.Rock_paper_scissors.RockPaperScissor;
import app.Tris.TrisFrame;
import app.indovina_immagini.src.GestioneIndovinaImmagineGUI;
import utils.UindosPath;

public class DesktopListener{
    private String name;
    private String username;

    public DesktopListener(String name, String username) {
        this.name = name;
        
        switch (name) {
            case "brouser":
                new BrowserApp(username);
                break;
            case "cartella giochi":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator + UindosPath.GAMES_PATH)); // Imposta la directory di lavoro come cartella iniziale
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String games_names = selectedFile.getName();
                    
                    switch (games_names) {
                        case "Hanged":
                            new Hanged(username);
                            break;
                        case "Indovina immagini":
                            new GestioneIndovinaImmagineGUI(username);
                            break;
                        case "Rock paper scissors":
                            new RockPaperScissor(username);
                            break;
                        case "Tris":
                            new TrisFrame(username);
                            break;
                    }
                }
                break;
        }
    }
}