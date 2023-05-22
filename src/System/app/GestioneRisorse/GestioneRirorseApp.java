package System.app.GestioneRisorse;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import utils.UindosPath;
import utils.GestoreConfig;
import utils.GestoreFrame;

public class GestioneRirorseApp {

    private JFrame mainFrame;
    private JTextArea processTextArea;
    private JScrollPane scrollPane;
    private Font font;

    public GestioneRirorseApp(String username) {
        mainFrame = new JFrame("Gestione Risorse");
        mainFrame.setIconImage(new ImageIcon(UindosPath.CONTROLPANEL_LOGO_PATH).getImage());
        GestoreFrame.aggiungiFrame(mainFrame);
        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);

        createElements();
        updateProcessList();
    }

    private void createElements() {
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(400, 300);

        processTextArea = new JTextArea();
        processTextArea.setEditable(false);
        processTextArea.setFont(font);

        scrollPane = new JScrollPane(processTextArea);
        mainFrame.add(scrollPane);

        mainFrame.setVisible(true);
    }

    private void updateProcessList() {
        List <JFrame> allFrames = GestoreFrame.getAllFrames();
        StringBuilder sb = new StringBuilder();

        for (Frame frame: allFrames) {
            sb.append(frame.getTitle()).append("\n");
        }

        processTextArea.setText(sb.toString());
    }
}