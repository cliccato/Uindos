
/**
 * Classe che implementa l'interfaccia MouseListener per gestire gli eventi dei clic del mouse per il gioco Rock Paper Scissor.
 * Quando un giocatore fa clic su una mossa, viene determinato il risultato del gioco confrontando la mossa del giocatore con la mossa generata casualmente dal computer.
 * Viene visualizzato un messaggio di vittoria, sconfitta o pareggio e vengono aggiornate le statistiche di vittorie per l'utente e il computer.
 * Inoltre, le immagini delle mosse vengono rese invisibili tranne quella selezionata dal giocatore, e viene mostrato il pulsante per giocare di nuovo.
 * 
 * @Author Giorgio Justin Fasullo
 */
package app.Rock_paper_scissors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Classe che implementa l'interfaccia MouseListener per gestire gli eventi del mouse.
 */
public class ListenerMove implements MouseListener {

    private String Move;
    private RockPaperScissor rockPaperScissor;

    /**
     * Costruttore della classe ListenerMove.
     * @param Move la mossa selezionata dall'utente
     * @param rockPaperScissor l'istanza della classe RockPaperScissor
     */
    public ListenerMove(String Move, RockPaperScissor rockPaperScissor) {
        this.Move = Move;
        this.rockPaperScissor = rockPaperScissor;
    }

    /**
     * Genera una mossa casuale per il computer.
     * @return la mossa generata per il computer
     */
    private String MoveComputer() {
        String[] mosse = {
            "Rock",
            "Paper",
            "Scissor"
        };
        return mosse[new Random().nextInt(RockPaperScissor.N_MOVES)];
    }

    /**
     * Mostra il risultato del gioco e aggiorna i conteggi delle vittorie.
     * @param MoveComputer la mossa generata per il computer
     */
    private void resultGame(String MoveComputer) {
        if ((Move.equals("Scissor") && MoveComputer.equals("Paper")) ||
            (Move.equals("Paper") && MoveComputer.equals("Rock")) ||
            (Move.equals("Rock") && MoveComputer.equals("Scissor"))) {
            JOptionPane.showMessageDialog(null, "Hai vinto!", "Messaggio di vittoria", JOptionPane.INFORMATION_MESSAGE);
            rockPaperScissor.incrementUserWins();
        } else if ((Move.equals("Scissor") && MoveComputer.equals("Rock")) ||
            (Move.equals("Paper") && MoveComputer.equals("Scissor")) ||
            (Move.equals("Rock") && MoveComputer.equals("Paper"))) {
            JOptionPane.showMessageDialog(null, "Il bro ha perso", "Messaggio di sconfitta", JOptionPane.INFORMATION_MESSAGE);
            rockPaperScissor.incrementComputerWins();
        } else {
            JOptionPane.showMessageDialog(null, "Pareggio", "Messaggio di parità", JOptionPane.INFORMATION_MESSAGE);
            rockPaperScissor.incrementDraws();
        }
    }

    /**
     * Rende invisibili le immagini delle mosse non selezionate.
     * @param MoveComputer la mossa generata per il computer
     */
    private void invisibleImg(String MoveComputer) {
        JLabel[] imgUtente = rockPaperScissor.getUserImages();
        JLabel[] imgComputer = rockPaperScissor.getComputerImages();

        switch (Move) {
            case "Rock":
                imgUtente[1].setVisible(false);
                imgUtente[2].setVisible(false);
                break;

            case "Paper":
                imgUtente[0].setVisible(false);
                imgUtente[2].setVisible(false);
                break;

            case "Scissor":
                imgUtente[0].setVisible(false);
                imgUtente[1].setVisible(false);
                break;
        }

        switch (MoveComputer) {
            case "Rock":
                imgComputer[1].setVisible(false);
                imgComputer[2].setVisible(false);
                break;

            case "Paper":
                imgComputer[0].setVisible(false);
                imgComputer[2].setVisible(false);
                break;

            case "Scissor":
                imgComputer[0].setVisible(false);
                imgComputer[1].setVisible(false);
                break;
        }
    }

    /**
     * Mostra il pulsante per giocare di nuovo.
     */
    private void showButtonAnotherGame() {
        rockPaperScissor.getBtnPlayAgain().setVisible(true);
    }

    /**
     * Gestisce l'evento di clic del mouse.
     * @param e l'evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String MoveComputer = MoveComputer();
        rockPaperScissor.getLblChooseMove().setVisible(false);
        invisibleImg(MoveComputer);
        resultGame(MoveComputer);
        showButtonAnotherGame();
    }

    /**
     * Metodo non implementato.
     * @param e l'evento del mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // Non implementato
    }

    /**
     * Metodo non implementato.
     * @param e l'evento del mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // Non implementato
    }

    /**
     * Metodo non implementato.
     * @param e l'evento del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Non implementato
    }

    /**
     * Metodo non implementato.
     * @param e l'evento del mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // Non implementato
    }
}
