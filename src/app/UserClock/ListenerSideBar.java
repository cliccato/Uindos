package app.UserClock;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerSideBar implements ActionListener{

    private ClockFrame clockFrame;

    public ListenerSideBar(ClockFrame clockFrame) {
        this.clockFrame = clockFrame;
    }

    public void actionPerformed(ActionEvent e) {
    String choosedOption = e.getActionCommand();
    GridBagConstraints gbc = new GridBagConstraints();
    
    clockFrame.getPnlContent().removeAll(); // Rimuovi tutti i componenti dal pannello contenuto
    clockFrame.getPnlContent().setLayout(new GridBagLayout()); // Imposta il layout come GridBagLayout

    switch (choosedOption) {
        case "Timer":
            CustomTimer timer = new CustomTimer();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.CENTER;
            clockFrame.getPnlContent().add(timer, gbc); // Imposta il timer nel pannello dei contenuti con le restrizioni di centratura
            break;
        case "Stopwatch":
            CustomStopWatch stopWatch = new CustomStopWatch();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.CENTER;
            clockFrame.getPnlContent().add(stopWatch, gbc); // Imposta il cronometro nel pannello dei contenuti con le restrizioni di centratura
            break;
        case "World Clock":
            WorldClockPanel worldClockPanel = new WorldClockPanel();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.CENTER;
            clockFrame.getPnlContent().add(worldClockPanel, gbc); // Imposta l'orologio mondiale nel pannello dei contenuti con le restrizioni di centratura
            break;
    }

    clockFrame.getPnlContent().revalidate(); // Aggiorna la GUI
    clockFrame.getPnlContent().repaint();
}

}
