/*
AUTORE: NICOLO' MEZZANZANICA
DATA: 06/02/2023
LUOGO: CASA
OGGETTO: CLASSE IMPICCATO
*/

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class Impiccato {

    // path
    private static final String PATH_PAROLE_SEMPLICI = "file/paroleSemplici.txt";
    private static final String PATH_PAROLE_NORMALI = "file/paroleNormali.txt";
    private static final String PATH_PAROLE_DIFFICILI = "file/paroleDifficili.txt";
    //finestra per impiccato
    JFrame frmImpiccato;
    //menu bar
    JMenuBar menuImpiccato;
    //menu
    JMenu menuPartita;
    JMenuItem voceParoleSemplici;
    JMenuItem voceParoleNormali;
    JMenuItem voceParoleDifficili;
    JMenuItem menuItemNuovaPartita;
    //etichette
    JLabel lblInformazioniParolaNascosta;
    JLabel lblParolaNascosta;
    JLabel lblParolaUtente;
    JLabel lblIndovinato;
    JLabel lblImmagine;
    //pannelli
    JPanel pnlParole;
    JLabel pnlIndovinato;
    //casella di testo per indovinare la parola
    JTextField cslParolaUtente;
    //array d'immagini per impiccato
    ImageIcon[] images;
    //bottone per confermare
    JButton btnConferma;
    //font
    Font fnt;
    //file
    File file;
    //parola estratta
    private String parolaEstratta;
    //tentativi
    private int tentativi = 7;
    //vettore lettere
    private Vector<Character> lettereGiaDette;
    //vettore parole
    private Vector<String> paroleGiaDette;
    //variabile booleana
    boolean giocoFinito = false;

    //metodo costruttore della classe Impiccato
    public Impiccato(){

        //creazione finestra per il gioco dell'impiccato
        frmImpiccato = new JFrame("Impiccato");
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
        voceParoleSemplici.addActionListener(new ListenerSceltaDifficolta(this));
        voceParoleNormali.addActionListener(new ListenerSceltaDifficolta(this));
        voceParoleDifficili.addActionListener(new ListenerSceltaDifficolta(this));
        menuImpiccato.add(menuPartita);
        menuPartita.add(voceParoleSemplici);
        menuPartita.add(voceParoleNormali);
        menuPartita.add(voceParoleDifficili);
        //creazione delle immagini per l'impiccato
        images = new ImageIcon[tentativi];
        images[0] = new ImageIcon("images/1 tentativo sbagliato.png");
        images[1] = new ImageIcon("images/2 tentativo sbagliato.png");
        images[2] = new ImageIcon("images/3 tentativo sbagliato.png");
        images[3] = new ImageIcon("images/4 tentativo sbagliato.png");
        images[4] = new ImageIcon("images/5 tentativo sbagliato.png");
        images[5] = new ImageIcon("images/6 tentativo sbagliato.png");
        images[6] = new ImageIcon("images/7 tentativo sbagliato.png");
        //creazione del font
        fnt = new Font("Serif", Font.BOLD, 22);
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
        lblImmagine = new JLabel(new ImageIcon("images/inizio.png"));
        cslParolaUtente = new JTextField("");
        //creazione del bottone conferma
        btnConferma = new JButton("CONFERMA");
        btnConferma.addActionListener(new ListenerImpiccato(this));
        //aggiunta del font ai vari componenti del frame
        cslParolaUtente.setFont(fnt);
        lblInformazioniParolaNascosta.setFont(fnt);
        lblParolaNascosta.setFont(fnt);
        lblParolaUtente.setFont(fnt);
        lblIndovinato.setFont(fnt);
        btnConferma.setFont(fnt);
        menuPartita.setFont(fnt);
        voceParoleSemplici.setFont(fnt);
        voceParoleNormali.setFont(fnt);
        voceParoleDifficili.setFont(fnt);
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
        frmImpiccato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmImpiccato.setVisible(true);
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
}