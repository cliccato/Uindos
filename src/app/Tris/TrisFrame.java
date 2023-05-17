package app.Tris;
import javax.swing.*;

import utils.GestoreFrame;
import utils.UindosPath;

import java.awt.*;
import java.util.Random;

public class TrisFrame{

    private JFrame frmTris;
    private JPanel pnlBtnTris;
    private JPanel pnlPunteggio;
    private JButton[][] btnTris;
    private JLabel lblPunteggioX;
    private JLabel lblPunteggioO;
    private JLabel lblTurnoGiocatore;
    private int punteggioX;
    private int punteggioO;
    private boolean vittoriaX;
    private boolean vittoriaO;
    private int mosse;
    private String turno;
    private static final String[] LETTERE_TRIS = {"X","O"};
    public static final int NUM_BOTTONI_TRIS = 9;
    private static final int NUM_BOTTONI_TRIS_RIGA_COLONNA = 3;
    private final Font fntBtn;

    public TrisFrame(){

        frmTris = new JFrame("Tris");
        frmTris.setLayout(new BorderLayout());
        frmTris.setIconImage(new ImageIcon(UindosPath.TRIS_LOGO_PATH).getImage());

        fntBtn = new Font("Serif", Font.BOLD, 40);

        vittoriaX = false;
        vittoriaO = false;
        punteggioX = 0;
        punteggioO = 0;
        mosse = 0;
        turno = LETTERE_TRIS[new Random().nextInt(2)];

        lblTurnoGiocatore = new JLabel("Turno del giocatore: " + turno);
        lblPunteggioX = new JLabel("Punteggio X: 0");
        lblPunteggioO = new JLabel("Punteggio O: 0");
        lblTurnoGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
        lblPunteggioX.setHorizontalAlignment(SwingConstants.LEFT);
        lblPunteggioO.setHorizontalAlignment(SwingConstants.RIGHT);

        pnlPunteggio = new JPanel(new GridLayout(1,3));
        pnlPunteggio.add(lblPunteggioX);
        pnlPunteggio.add(lblTurnoGiocatore);
        pnlPunteggio.add(lblPunteggioO);

        pnlBtnTris = new JPanel(new GridLayout(NUM_BOTTONI_TRIS_RIGA_COLONNA,NUM_BOTTONI_TRIS_RIGA_COLONNA));
        btnTris = new JButton[NUM_BOTTONI_TRIS_RIGA_COLONNA][NUM_BOTTONI_TRIS_RIGA_COLONNA];
        for (int i = 0; i < NUM_BOTTONI_TRIS_RIGA_COLONNA; i++) {
            for (int j = 0; j < NUM_BOTTONI_TRIS_RIGA_COLONNA; j++) {
                btnTris[i][j] = new JButton("");
                btnTris[i][j].addActionListener(new TrisListener(this));
                btnTris[i][j].setFont(fntBtn);
                pnlBtnTris.add(btnTris[i][j]);
            }
        }

        frmTris.add(pnlPunteggio, BorderLayout.NORTH);
        frmTris.add(pnlBtnTris, BorderLayout.CENTER);
        frmTris.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmTris.setSize(new Dimension(600,600));
        frmTris.setVisible(true);
        GestoreFrame.aggiungiFrame(frmTris);
    }

    public void setMosse(int mosse) {
        this.mosse = mosse;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setVittoriaO(boolean vittoriaO) {
        this.vittoriaO = vittoriaO;
    }

    public void setVittoriaX(boolean vittoriaX) {
        this.vittoriaX = vittoriaX;
    }

    public void setPunteggioO(int punteggioO) {
        this.punteggioO = punteggioO;
    }

    public void setPunteggioX(int punteggioX) {
        this.punteggioX = punteggioX;
    }

    public int getMosse() {
        return mosse;
    }

    public String getTurno(){
        return turno;
    }

    public boolean isVittoriaO() {
        return vittoriaO;
    }

    public boolean isVittoriaX() {
        return vittoriaX;
    }

    public int getPunteggioO() {
        return punteggioO;
    }

    public int getPunteggioX() {
        return punteggioX;
    }

    public JLabel getLblTurnoGiocatore() {
        return lblTurnoGiocatore;
    }

    public JLabel getLblPunteggioO() {
        return lblPunteggioO;
    }

    public JLabel getLblPunteggioX() {
        return lblPunteggioX;
    }

    public JButton[][] getBtnTris() {
        return btnTris;
    }

    public String[] getLettereTris(){
        return LETTERE_TRIS;
    }
}