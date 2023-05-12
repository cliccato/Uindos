package app.indovina_immagini.src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerConfermaSceltaNomeImmagine implements ActionListener {

    private IndovinaImmagineGUI indovinaImmagineGUI;        // GUI

    // costruttore
    public ListenerConfermaSceltaNomeImmagine(IndovinaImmagineGUI indovinaImmagineGUI){
        this.indovinaImmagineGUI = indovinaImmagineGUI;
    }

    // metodo actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        if(indovinaImmagineGUI.getBg().getSelection() == null) {        // segnala all'utente che non ha selezionato il nome di un gioco
            indovinaImmagineGUI.getLblStato().setText("Stato: selezionare un opzione!");
            indovinaImmagineGUI.getLblStato().setForeground(Color.RED);
        } else if (indovinaImmagineGUI.getBg().getSelection().getActionCommand().equals(indovinaImmagineGUI.getNomeImmagineUscita())){      // altrimenti se l'utente ha indovinato lo informa
            indovinaImmagineGUI.getLblStato().setText("Stato: Corretto!");
            indovinaImmagineGUI.getLblStato().setForeground(Color.GREEN);
            indovinaImmagineGUI.getT().interrupt();
            indovinaImmagineGUI.getBtnCambiaImg().setVisible(true);
            indovinaImmagineGUI.getBtnConferma().setVisible(false);
            for (int i=0; i < IndovinaImmagineGUI.NUM_RADIOBUTTON; i++){
                indovinaImmagineGUI.getRb()[i].setEnabled(false);
            }
        } else {            // altrimenti avvisa l'utente che non ha indovinato
            indovinaImmagineGUI.getLblStato().setText("Stato: Sbagliato! non è " + indovinaImmagineGUI.getBg().getSelection().getActionCommand() + "!");
            indovinaImmagineGUI.getLblStato().setForeground(Color.RED);
        }
    }
}