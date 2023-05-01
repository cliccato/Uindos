package app.indovina_immagini.source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerConfermaSceltaCategoriaImmagini implements ActionListener {

    private GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;        // GUI

    // costruttore
    public ListenerConfermaSceltaCategoriaImmagini(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI) {
        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;
    }

    // metodo actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gestioneIndovinaImmagineGUI.getUtenteCorrente() == null) {
            gestioneIndovinaImmagineGUI.getLblStato().setText("Errore, prima di giocare eseguire l'accesso ad un account!");
            gestioneIndovinaImmagineGUI.getLblStato().setForeground(Color.RED);
        } else {
            gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(false);
            gestioneIndovinaImmagineGUI.setCategoriaScelta(e.getActionCommand());
            new IndovinaImmagineGUI(gestioneIndovinaImmagineGUI, "nomi_immagini/" + gestioneIndovinaImmagineGUI.getCategoriaScelta() + ".txt");
        }
    }
}