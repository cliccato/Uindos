package app.indovina_immagini.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerConfermaAccedi implements ActionListener {

    private  GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;       // gestione indovina immagine gui
    private AccediUtenteGUI accediUtenteGUI;                    // accedi utente gui

    // costruttore
    public ListenerConfermaAccedi(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI, AccediUtenteGUI accediUtenteGUI){
        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;
        this.accediUtenteGUI = accediUtenteGUI;
    }

    // restituisce la posizione dell'utente già registrato nell'array
    public int utenteGiaRegistrato(){
        for (int i = 0; i < gestioneIndovinaImmagineGUI.getUtenti().size(); i++) {
            if (gestioneIndovinaImmagineGUI.getUtenti().get(i).getNomeUtente().equals(accediUtenteGUI.getCslNomeUtente().getText()) &&
                gestioneIndovinaImmagineGUI.getUtenti().get(i).getPassword().equals(accediUtenteGUI.getCslPassword().getText())) {
                return i;
            }
        }
        return -1;
    }

    // metodo actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {

        if (gestioneIndovinaImmagineGUI.getUtenti().isEmpty()) {
            accediUtenteGUI.getCslNomeUtente().setEditable(false);
            accediUtenteGUI.getCslPassword().setEditable(false);
            accediUtenteGUI.getLblStato().setText("Stato: nessun utente registrato!");
            accediUtenteGUI.getLblStato().setForeground(Color.RED);
        } else {
            int indice = utenteGiaRegistrato();
            if (indice >= 0) {
                gestioneIndovinaImmagineGUI.setUtenteCorrente(gestioneIndovinaImmagineGUI.getUtenti().get(indice));
                gestioneIndovinaImmagineGUI.getLblUtente().setText("Accesso effettuato come: " + gestioneIndovinaImmagineGUI.getUtenti().get(indice).getNomeUtente());
                gestioneIndovinaImmagineGUI.getLblStato().setText("");
                gestioneIndovinaImmagineGUI.getVoceUtenteAccedi().setVisible(false);
                gestioneIndovinaImmagineGUI.getVoceUtenteRegistrati().setVisible(false);
                gestioneIndovinaImmagineGUI.getVoceUtenteLogout().setVisible(true);
                JOptionPane.showMessageDialog(null, "Accesso effettuato!", "Accedi", JOptionPane.INFORMATION_MESSAGE);
                gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(true);
                accediUtenteGUI.getFrmAccediUtente().setVisible(false);
            } else {
                accediUtenteGUI.getLblStato().setText("Stato: nome utente e/o password non corretti!");
                accediUtenteGUI.getLblStato().setForeground(Color.RED);
            }
        }
    }
}