/**
 * Classe che implementa l'interfaccia ActionListener per gestire gli eventi della barra laterale.
 * Si occupa di aggiornare il pannello dei contenuti in base all'opzione selezionata.
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since 2023-05-12
 */

package app.UserClock;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerSideBar implements ActionListener {

    private ClockFrame clockFrame;

    /**
     * Costruttore della classe ListenerSideBar.
     *
     * @param clockFrame il frame principale dell'applicazione
     */
    public ListenerSideBar(ClockFrame clockFrame) {
        this.clockFrame = clockFrame;
    }

    /**
     * Metodo che viene chiamato quando si verifica un evento.
     * Gestisce l'opzione selezionata e aggiorna il pannello dei contenuti di conseguenza.
     *
     * @param e l'evento generato
     */
    public void actionPerformed(ActionEvent e) {
        String choosedOption = e.getActionCommand();
        GridBagConstraints gbc = new GridBagConstraints();

        clockFrame.getPnlContent().removeAll(); // Rimuovi tutti i componenti dal pannello dei contenuti
        clockFrame.getPnlContent().setLayout(new GridBagLayout()); // Imposta il layout come GridBagLayout

        switch (choosedOption) {
            case "Timer":
                CustomTimer timer = new CustomTimer(clockFrame.getFont());
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.CENTER;
                clockFrame.getPnlContent().add(timer, gbc); // Aggiungi il timer al pannello dei contenuti con le restrizioni di centratura
                break;
            case "Stopwatch":
                CustomStopWatch stopWatch = new CustomStopWatch(clockFrame.getFont());
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.CENTER;
                clockFrame.getPnlContent().add(stopWatch, gbc); // Aggiungi il cronometro al pannello dei contenuti con le restrizioni di centratura
                break;
            case "World Clock":
                WorldClockPanel worldClockPanel = new WorldClockPanel(clockFrame.getFont());
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.CENTER;
                clockFrame.getPnlContent().add(worldClockPanel, gbc); // Aggiungi l'orologio mondiale al pannello dei contenuti con le restrizioni di centratura
                break;
        }

        clockFrame.getPnlContent().revalidate(); // Aggiorna la GUI
        clockFrame.getPnlContent().repaint();
    }
}
