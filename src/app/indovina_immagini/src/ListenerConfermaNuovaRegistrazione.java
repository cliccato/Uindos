package app.indovina_immagini.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListenerConfermaNuovaRegistrazione implements ActionListener {

    GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;            // gestione indovina immagine gui
    RegistrazioneUtenteGUI registrazioneUtenteGUI;                      // registrazione utente gui

    // costruttore
    public ListenerConfermaNuovaRegistrazione(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI, RegistrazioneUtenteGUI registrazioneUtenteGUI){
        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;
        this.registrazioneUtenteGUI = registrazioneUtenteGUI;
    }

    // metodo per controllare se l'ID è univoco o meno
    private boolean isUnique(int ID){
        for(int i=0; i<gestioneIndovinaImmagineGUI.getIDGiaDetti().size(); i++){
            if(gestioneIndovinaImmagineGUI.getIDGiaDetti().get(i) == ID)
                return false;
        }
        return true;
    }

    // metodo per generare un ID univoco
    private int generaID(){
        boolean valido = true;
        int ID;
        do{
            ID = (int) (Math.random()*1000) + 1;
            valido = isUnique(ID);
        }while (!valido);
        return  ID;
    }

    // metodo che ritorna true se il nome utente è già utilizzato
    private boolean nomeUtenteGiaUsato(){
        for (int i = 0; i < gestioneIndovinaImmagineGUI.getUtenti().size(); i++) {
            if (gestioneIndovinaImmagineGUI.getUtenti().get(i).getNomeUtente().equals(registrazioneUtenteGUI.getCslNomeUtente().getText()))
                return true;
        }
        return false;
    }

    //metodo actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        String vuoto="";

        if (nomeUtenteGiaUsato()) {
            registrazioneUtenteGUI.getLblStato().setText("Stato: errore, nome utente gia usato!");
            registrazioneUtenteGUI.getLblStato().setForeground(Color.RED);
        } else if(registrazioneUtenteGUI.getCslNomeUtente().getText().equals(vuoto)
                || registrazioneUtenteGUI.getCslNomeReale().getText().equals(vuoto)
                || registrazioneUtenteGUI.getCslPassword().getText().equals(vuoto)
                || registrazioneUtenteGUI.getCslGiornoNascitaUtente().getText().equals(vuoto)
                || registrazioneUtenteGUI.getCslMeseNascitaUtente().getText().equals(vuoto)
                || registrazioneUtenteGUI.getCslAnnoNascitaUtente().getText().equals(vuoto)){
            registrazioneUtenteGUI.getLblStato().setText("Stato: errore, compilare tutti i campi obbligatori!");
            registrazioneUtenteGUI.getLblStato().setForeground(Color.red);
        } else {
            try {
                String nomeUtente = registrazioneUtenteGUI.getCslNomeUtente().getText();
                String nomeReale = registrazioneUtenteGUI.getCslNomeReale().getText();
                String password = registrazioneUtenteGUI.getCslPassword().getText();
                int giornoNascitaUtente = Integer.parseInt(registrazioneUtenteGUI.getCslGiornoNascitaUtente().getText());
                int meseNascitaUtente = Integer.parseInt(registrazioneUtenteGUI.getCslMeseNascitaUtente().getText());
                int annoNascitaUtente = Integer.parseInt(registrazioneUtenteGUI.getCslAnnoNascitaUtente().getText());
                String sesso = "";
                if(sesso.equals(registrazioneUtenteGUI.getCslSessoUtente().getText())) sesso = "Non specificato";
                else sesso = registrazioneUtenteGUI.getCslSessoUtente().getText();
                Data dataNascitaUtente = new Data(giornoNascitaUtente, meseNascitaUtente, annoNascitaUtente);
                int ID = generaID();
                Utente utente = new Utente(nomeUtente, nomeReale, dataNascitaUtente, sesso,  password,ID);
                Vector<Utente> utenti = gestioneIndovinaImmagineGUI.getUtenti();
                utenti.add(utente);
                gestioneIndovinaImmagineGUI.setUtenti(utenti);
                gestioneIndovinaImmagineGUI.setUtenteCorrente(utente);
                gestioneIndovinaImmagineGUI.getVoceUtenteAccedi().setVisible(false);
                gestioneIndovinaImmagineGUI.getVoceUtenteRegistrati().setVisible(false);
                gestioneIndovinaImmagineGUI.getVoceUtenteLogout().setVisible(true);
                gestioneIndovinaImmagineGUI.getLblUtente().setText("Accesso effettuato come: " + utente.getNomeUtente());
                gestioneIndovinaImmagineGUI.getLblStato().setText("");
                JOptionPane.showMessageDialog(null, "Registrazione effettuata!", "Registrazione", JOptionPane.INFORMATION_MESSAGE);
                gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(true);
                registrazioneUtenteGUI.getFrmRegistrazioneUtente().setVisible(false);
            } catch (NumberFormatException numberFormatException){
                registrazioneUtenteGUI.getLblStato().setText("Stato: errore, data non valida!");
                registrazioneUtenteGUI.getLblStato().setForeground(Color.red);
            }
        }
    }
}