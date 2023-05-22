package app.Hanged;
import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;
import utils.UindosPath;

import java.awt.*;
import java.io.File;
import java.util.Vector;

public class Hanged {

    public static final int NUM_TENTATIVI = 7;
    private JFrame frmImpiccato;
    private JMenuBar menuImpiccato;
    private JMenu menuPartita;
    private JMenuItem voceParoleSemplici;
    private JMenuItem voceParoleNormali;
    private JMenuItem voceParoleDifficili;
    private JLabel lblInformazioniParolaNascosta;
    private JLabel lblParolaNascosta;
    private JLabel lblParolaUtente;
    private JLabel lblIndovinato;
    private JLabel lblImmagine;
    private JPanel pnlParole;
    private JLabel pnlIndovinato;
    private JTextField cslParolaUtente;
    private ImageIcon[] images;
    private JButton btnConferma;
    private File file;
    private String parolaEstratta;
    private int tentativi = 7;
    private Vector<Character> lettereGiaDette;
    private Vector<String> paroleGiaDette;
    private boolean isGiocoFinito = false;
    private Font font;

    public Hanged(String username){

        //creazione finestra per il gioco dell'impiccato
        frmImpiccato = new JFrame("Impiccato");
        frmImpiccato.setIconImage((new ImageIcon(UindosPath.HANGED_LOGO_PATH)).getImage());
        Container c = frmImpiccato.getContentPane();
        c.setLayout(new BorderLayout());
        //creazione menu
        menuImpiccato = new JMenuBar();
        frmImpiccato.setJMenuBar(menuImpiccato);
        menuPartita = new JMenu("Partita");
        voceParoleSemplici = new JMenuItem("paroleSemplici");
        voceParoleNormali = new JMenuItem("paroleNormali");
        voceParoleDifficili = new JMenuItem("paroleDifficili");
        voceParoleSemplici.setActionCommand(voceParoleSemplici.getText());
        voceParoleNormali.setActionCommand(voceParoleNormali.getText());
        voceParoleDifficili.setActionCommand(voceParoleDifficili.getText());
        voceParoleSemplici.addActionListener(new ListenerChooseDifficult(this));
        voceParoleNormali.addActionListener(new ListenerChooseDifficult(this));
        voceParoleDifficili.addActionListener(new ListenerChooseDifficult(this));

        font = (Font) GestoreConfig.getConfig(username, GestoreConfig.FONT);

        menuImpiccato.add(menuPartita);
        menuPartita.add(voceParoleSemplici);
        menuPartita.add(voceParoleNormali);
        menuPartita.add(voceParoleDifficili);
        //creazione delle immagini per l'impiccato
        images = new ImageIcon[tentativi];
        images[0] = new ImageIcon("src/app/Hanged/images/1 tentativo sbagliato.png".replace("/", File.separator));
        images[1] = new ImageIcon("src/app/Hanged/images/2 tentativo sbagliato.png".replace("/", File.separator));
        images[2] = new ImageIcon("src/app/Hanged/images/3 tentativo sbagliato.png".replace("/", File.separator));
        images[3] = new ImageIcon("src/app/Hanged/images/4 tentativo sbagliato.png".replace("/", File.separator));
        images[4] = new ImageIcon("src/app/Hanged/images/5 tentativo sbagliato.png".replace("/", File.separator));
        images[5] = new ImageIcon("src/app/Hanged/images/6 tentativo sbagliato.png".replace("/", File.separator));
        images[6] = new ImageIcon("src/app/Hanged/images/7 tentativo sbagliato.png".replace("/", File.separator));
        //creazione del font
        //creazione dei pannelli
        pnlParole = new JPanel();
        pnlIndovinato = new JLabel();
        //aggiunta del layout ai pannelli
        pnlParole.setLayout(new GridLayout(1, 4));
        pnlIndovinato.setLayout(new GridLayout(1,2));
        //creazione delle etichette
        lblParolaNascosta = new JLabel();
        lblInformazioniParolaNascosta = new JLabel();
        lblParolaUtente = new JLabel("Parola utente: ");
        lblIndovinato = new JLabel();
        lblImmagine = new JLabel(new ImageIcon("src/app/Hanged/images/inizio.png".replace("/", File.separator)));
        cslParolaUtente = new JTextField("");
        //creazione del bottone conferma
        btnConferma = new JButton("CONFERMA");
        btnConferma.addActionListener(new ListenerHanged(this));
        //aggiunta del font ai vari componenti del frame
        cslParolaUtente.setFont(font);
        lblInformazioniParolaNascosta.setFont(font);
        lblParolaNascosta.setFont(font);
        lblParolaUtente.setFont(font);
        lblIndovinato.setFont(font);
        btnConferma.setFont(font);
        menuPartita.setFont(font);
        voceParoleSemplici.setFont(font);
        voceParoleNormali.setFont(font);
        voceParoleDifficili.setFont(font);
        //aggiunta dei componenti ai pannelli
        pnlParole.add(lblInformazioniParolaNascosta);
        pnlParole.add(lblParolaNascosta);
        pnlParole.add(lblParolaUtente);
        pnlParole.add(cslParolaUtente);
        pnlIndovinato.add(lblIndovinato);
        pnlIndovinato.add(lblImmagine);
        //visibilità a false iniziale
        lblParolaUtente.setVisible(false);
        cslParolaUtente.setVisible(false);
        lblImmagine.setVisible(false);
        btnConferma.setVisible(false);
        lblInformazioniParolaNascosta.setVisible(false);
        //aggiunta dei pannelli e del bottone al container
        c.add(pnlParole, BorderLayout.NORTH);
        c.add(pnlIndovinato, BorderLayout.CENTER);
        c.add(btnConferma, BorderLayout.SOUTH);
        //creazione dimension per frame
        Dimension dimension = new Dimension(900, 500);
        frmImpiccato.setSize(dimension);
        frmImpiccato.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmImpiccato.setVisible(true);
        GestoreFrame.aggiungiFrame(frmImpiccato);
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setTentativi(int tentativi) {
        this.tentativi = tentativi;
    }

    public int getTentativi() {
        return tentativi;
    }

    public void setLettereGiaDette(Vector<Character> lettereGiaDette) {
        this.lettereGiaDette = lettereGiaDette;
    }

    public Vector<Character> getLettereGiaDette() {
        return lettereGiaDette;
    }

    public void setParoleGiaDette(Vector<String> paroleGiaDette){
        this.paroleGiaDette = paroleGiaDette;
    }

    public Vector<String> getParoleGiaDette() {
        return paroleGiaDette;
    }

    public void setParolaEstratta(String parolaEstratta){
        this.parolaEstratta = parolaEstratta;
    }

    public String getParolaEstratta() {
        return parolaEstratta;
    }

    public JLabel getLblImmagine() {
        return lblImmagine;
    }

    public ImageIcon[] getImages() {
        return images;
    }

    public JTextField getCslParolaUtente() {
        return cslParolaUtente;
    }

    public JLabel getLblParolaNascosta() {
        return lblParolaNascosta;
    }

    public JLabel getLblIndovinato() {
        return lblIndovinato;
    }

    public void finisciGioco() {
        isGiocoFinito = true;
    }

    public void iniziaGioco() {
        isGiocoFinito = false;
    }

    public boolean getIsGiocoFinito() {
        return isGiocoFinito;
    }

    public File getFile() {
        return file;
    }

    public JLabel getLblInformazioniParolaNascosta() {
        return lblInformazioniParolaNascosta;
    }

    public JLabel getLblParolaUtente() {
        return lblParolaUtente;
    }

    public JButton getBtnConferma() {
        return btnConferma;
    }
}