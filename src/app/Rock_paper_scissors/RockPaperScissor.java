package app.Rock_paper_scissors;

/**
 * RockPaperScissor è un gioco di "Rock Paper Scissor" con GUI.
 * 
 * @author Giorgio Justin Fasullo
 */

import javax.swing.*;

import utils.GestoreFrame;
import utils.UindosPath;

import java.awt.*;

public class RockPaperScissor {
    public static final String IMG_ROCK_PATH = "src/app/Rock_paper_scissors/img/rock.jpeg";
    public static final String IMG_PAPER_PATH = "src/app/Rock_paper_scissors/img/paper.jpeg";
    public static final String IMG_SCISSOR_PATH = "src/app/Rock_paper_scissors/img/scissor.jpeg";
    public static final int N_MOVES = 3;
    public static final int N_PLAYERS = 2;
    public static final String[] MOVES = {
        "Rock",
        "Paper",
        "Scissor"
    };

    private JFrame frame;
    private Container container;
    private JLabel[] imgRock;
    private JLabel[] imgScissor;
    private JLabel[] imgPaper;
    private JPanel pnlMoves;
    private JPanel pnlStatistics;
    private JPanel pnlSummary;
    private JPanel pnlGame;

    private JLabel lblChooseMove;
    private JButton btnPlayAgain;

    private JLabel lblUserWins;
    private int userWins = 0;
    private JLabel lblComputerWins;
    private int computerWins = 0;
    private JLabel lblDraws;
    private int draws = 0;

    private JLabel lblUser;
    private JLabel lblComputer;

    /**
     * Costruttore della classe RockPaperScissor.
     * Crea e inizializza i componenti GUI del gioco.
     */
    public RockPaperScissor() {
        createComponents();
        setFrame();
        GestoreFrame.aggiungiFrame(frame);
    }

    /**
     * Crea i componenti GUI del gioco.
     */
    private void createComponents() {
        frame = new JFrame("Rock Paper Scissor");
        container = this.frame.getContentPane();

        container.setLayout(new BorderLayout());

        pnlSummary = new JPanel(new GridLayout(2, 1));
        pnlStatistics = new JPanel(new GridLayout(1, 3));

        lblUserWins = new JLabel("User wins: " + userWins);
        lblComputerWins = new JLabel("Computer wins: " + computerWins);
        lblDraws = new JLabel("Draws: " + draws);

        pnlStatistics.add(lblUserWins);
        pnlStatistics.add(lblComputerWins);
        pnlStatistics.add(lblDraws);

        btnPlayAgain = new JButton("Play Again");
        btnPlayAgain.setVisible(false);
        btnPlayAgain.addActionListener(new ListenerAnotherGame(this));
        pnlSummary.add(pnlStatistics);
        pnlSummary.add(btnPlayAgain);

        pnlGame = new JPanel(new BorderLayout());

        imgRock = new JLabel[N_PLAYERS];
        imgScissor = new JLabel[N_PLAYERS];
        imgPaper = new JLabel[N_PLAYERS];

        for (int i = 0; i < N_PLAYERS; i++) {
            imgRock[i] = new JLabel(new ImageIcon(IMG_ROCK_PATH));
            imgPaper[i] = new JLabel(new ImageIcon(IMG_PAPER_PATH));
            imgScissor[i] = new JLabel(new ImageIcon(IMG_SCISSOR_PATH));
        }

        imgRock[1].addMouseListener(new ListenerMove(RockPaperScissor.MOVES[0], this));
        imgPaper[1].addMouseListener(new ListenerMove(RockPaperScissor.MOVES[1], this));
        imgScissor[1].addMouseListener(new ListenerMove(RockPaperScissor.MOVES[2], this));

        pnlMoves = new JPanel(new GridLayout(3, 3));
        lblChooseMove = new JLabel("Choose move");
        lblChooseMove.setHorizontalAlignment(JLabel.CENTER);

        pnlMoves.add(imgRock[0]);
        pnlMoves.add(imgPaper[0]);
        pnlMoves.add(imgScissor[0]);
        pnlMoves.add(new JLabel(""));
        pnlMoves.add(lblChooseMove);
        pnlMoves.add(new JLabel(""));

        pnlMoves.add(imgRock[1]);
        pnlMoves.add(imgPaper[1]);
        pnlMoves.add(imgScissor[1]);
        lblUser = new JLabel("User"); // User name
        lblUser.setHorizontalAlignment(JLabel.CENTER);
        lblComputer = new JLabel("Computer"); // Computer name
        lblComputer.setHorizontalAlignment(JLabel.CENTER);

        pnlGame.add(lblComputer, BorderLayout.NORTH);
        pnlGame.add(pnlMoves, BorderLayout.CENTER);
        pnlGame.add(lblUser, BorderLayout.SOUTH);

        container.add(pnlGame, BorderLayout.CENTER);
        container.add(pnlSummary, BorderLayout.NORTH);
    }

    /**
     * Imposta le dimensioni e le impostazioni del frame del gioco.
     */
    private void setFrame() {
        Dimension frameDimension = new Dimension(1000, 1000);
        frame.setSize(frameDimension);
        frame.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon(UindosPath.ROCK_PAPER_SCISSOR_LOGO_PATH);

        frame.setIconImage(img.getImage());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // TODO uncomment when implemented in the project
        frame.setVisible(true);
    }

    /**
     * Restituisce le immagini dei movimenti del computer.
     *
     * @return un array di JLabel contenente le immagini dei movimenti del computer
     */
    public JLabel[] getComputerImages() {
        JLabel[] computerImages = new JLabel[N_MOVES];
        computerImages[0] = imgRock[0];
        computerImages[1] = imgPaper[0];
        computerImages[2] = imgScissor[0];
        return computerImages;
    }

    /**
     * Restituisce le immagini dei movimenti dell'utente.
     *
     * @return un array di JLabel contenente le immagini dei movimenti dell'utente
     */
    public JLabel[] getUserImages() {
        JLabel[] userImages = new JLabel[N_MOVES];
        userImages[0] = imgRock[1];
        userImages[1] = imgPaper[1];
        userImages[2] = imgScissor[1];
        return userImages;
    }

    /**
     * Restituisce il bottone "Play Again".
     *
     * @return il bottone "Play Again"
     */
    public JButton getBtnPlayAgain() {
        return btnPlayAgain;
    }

    /**
     * Incrementa il conteggio delle vittorie dell'utente e aggiorna l'etichetta corrispondente.
     */
    public void incrementUserWins() {
        userWins++;
        lblUserWins.setText("User wins: " + userWins);
    }

    /**
     * Incrementa il conteggio delle vittorie del computer e aggiorna l'etichetta corrispondente.
     */
    public void incrementComputerWins() {
        computerWins++;
        lblComputerWins.setText("Computer wins: " + computerWins);
    }

    /**
     * Incrementa il conteggio dei pareggi e aggiorna l'etichetta corrispondente.
     */
    public void incrementDraws() {
        draws++;
        lblDraws.setText("Draws: " + draws);
    }

    /**
     * Mostra le immagini dei movimenti del gioco.
     */
    public void showImages() {
        for (int i = 0; i < N_PLAYERS; i++) {
            imgRock[i].setVisible(true);
        }

        for (int i = 0; i < N_PLAYERS; i++) {
            imgPaper[i].setVisible(true);
        }
        for (int i = 0; i < N_PLAYERS; i++) {
            imgScissor[i].setVisible(true);
        }
    }

    /**
     * Restituisce l'etichetta "Choose move".
     *
     * @return l'etichetta "Choose move"
     */
    public JLabel getLblChooseMove() {
        return lblChooseMove;
    }
}