package app.indovina_immagini.src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import utils.GestoreCartelle;

public class ListenerClassificaCategoriaImmagini implements ActionListener {

    private GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;        // gestione indovina immagine gui

    // costruttore
    public ListenerClassificaCategoriaImmagini(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI){
        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;
    }

    // metodo per restituire il numero di partite giocate su una categoria d'immagini
    private int numeroPartiteGiocateCategoriaImmagini(String categoriaScelta){
        int cont = 0;
        for (int i = 0; i < gestioneIndovinaImmagineGUI.getClassifica().size(); i++) {
            if (gestioneIndovinaImmagineGUI.getClassifica().get(i).getCategoria_immagini().equals(categoriaScelta))
                cont ++;
        }
        return cont;
    }

    // metodo per restituire una classifica specifica su una categoria d'immagini
    private String[][] ottieniClassificaCategoriaImmagini(String categoriaScelta){
        String[][] classificaCategoriaImmagini = new String[numeroPartiteGiocateCategoriaImmagini(categoriaScelta)][2];
        int j=0;
        for (int i = 0; i < gestioneIndovinaImmagineGUI.getClassifica().size(); i++) {
            if (gestioneIndovinaImmagineGUI.getClassifica().get(i).getCategoria_immagini().equals(categoriaScelta)){
                classificaCategoriaImmagini[j][0] = gestioneIndovinaImmagineGUI.getClassifica().get(i).getNomeUtente();
                classificaCategoriaImmagini[j][1] = String.valueOf(gestioneIndovinaImmagineGUI.getClassifica().get(i).getPunteggio());
                j++;
            }
        }
        return classificaCategoriaImmagini;
    }

    // metodo actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        gestioneIndovinaImmagineGUI.getLblStato().setText("");
        new ClassificaCategoriaImmaginiGUI(gestioneIndovinaImmagineGUI.getFrmPrincipale(), ottieniClassificaCategoriaImmagini(e.getActionCommand()), e.getActionCommand(), gestioneIndovinaImmagineGUI.getUsername());
    }
}