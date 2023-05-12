package app.Rock_paper_scissors;

/**
 *
 * ListenerAnotherGame è una classe che implementa l'interfaccia ActionListener e gestisce l'evento di un pulsante per un altro gioco in Rock Paper Scissor.
 * Quando il pulsante viene premuto, il gioco viene riavviato mostrando nuovamente le immagini e ripristinando lo stato iniziale.
 * @author Giorgio Justin Fasullo
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerAnotherGame implements ActionListener {
    private RockPaperScissor rockPaperScissor;

    /**
     * Costruttore della classe ListenerAnotherGame.
     * 
     * @param rockPaperScissor l'istanza di RockPaperScissor a cui il listener è associato
     */
    public ListenerAnotherGame(RockPaperScissor rockPaperScissor) {
        this.rockPaperScissor = rockPaperScissor;
    }

    /**
     * Gestisce l'evento di azione quando il pulsante per un altro gioco viene premuto.
     * Riavvia il gioco mostrando nuovamente le immagini, nascondendo il pulsante e ripristinando lo stato iniziale.
     * 
     * @param e l'evento di azione generato dal pulsante
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        rockPaperScissor.showImages();
        rockPaperScissor.getBtnPlayAgain().setVisible(false);
        rockPaperScissor.getLblChooseMove().setVisible(true);
    }
}
