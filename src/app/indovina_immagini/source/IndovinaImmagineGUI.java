package app.indovina_immagini.source;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class IndovinaImmagineGUI {

    private static final int WIDTH = 900;           // larghezza per il frame
    private static final int HEIGHT = 600;          // altezza per il frame
    public static final int NUM_RADIOBUTTON = 4;   // numeri di radio button
    private JFrame frmIndovinaImmagine;             // frame indovina immagine
    private File file;                          // file
    private JPanel pnlIndovinaImmagine;         // pannello composto dalle varie opzioni che ha l'utente per indovinare il nome dall'immagine
    private JPanel pnlBtn;                      // pannello dei bottoni
    private JPanel pnlNord;                     // pannello nord
    private JPanel pnlSud;                      // pannello sud
    private Vector<String> nomi_immagini;       // array dinamico formato dai nomi delle immagini
    private String nomeImmagineUscita;          // nome dell'immagine uscita
    private ButtonGroup bg;                     // button group
    private JRadioButton[] rb;                  // radio button ciascuno dei quali rappresenta il nome di un'immagine
    private JLabel lblImg;                      // label contenente l'immagine uscita
    private JButton btnCambiaImg;               // bottone per passare a un altra immagine
    private JButton btnConferma;                // bottone per confermare l'opzione scelta dall'utente
    private JLabel lblStato;                    // label per dare informazioni all'utente
    private JLabel lblPunteggio;                // label punteggio
    private JLabel lblTempoRimanente;           // label tempo rimanente
    private int punteggio;                      // punteggio
    private ThreadTempoRimanente t;             // thread per aggiornare il tempo
    private EstrazioneNumeriCasualiSenzaRipetizioni indiciRadioButton;      // oggetto per gestire l'estrazione d'indici casuali senza ripetizioni
                                                                            // (serve per cambiare di posizione i radio button)
    private EstrazioneNumeriCasualiSenzaRipetizioni indiciImg;              // oggetto per gestire l'estrazione d'indici casuali senza ripetizioni
                                                                            // (serve per non generare le stesse immagini)

    // metodo per prelevare tutti i nomi di una categoria d'immagini dal file
    private Vector<String> ottieniNomiImmaginiDaFile(){
        Vector<String> nomi_immagini = new Vector<>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null){
                nomi_immagini.add(line);
                line = bufferedReader.readLine();

            }
            bufferedReader.close();
        } catch (IOException ioException){
            ;
        }
        return nomi_immagini;
    }

    // metodo che restituisce true se il nome dell'immagine uscita è presente nei radiobutton
    private boolean nomeImmagineUscitaPresente(){
        for (int i = 0; i < NUM_RADIOBUTTON; i++) {
            if (rb[i].getText().equals(nomeImmagineUscita))
                return true;
        }
        return false;
    }

    // metodo per cambiare di posizione i radiobutton presenti nel pannello
    private void cambioPosizioneRadioButton(){
        indiciRadioButton = new EstrazioneNumeriCasualiSenzaRipetizioni(0, nomi_immagini.size());
        for (int i = 0; i < NUM_RADIOBUTTON; i++) {
            int indiceEstratto = indiciRadioButton.estrai();
            rb[i].setText(nomi_immagini.get(indiceEstratto));
            rb[i].setActionCommand(nomi_immagini.get(indiceEstratto));
        }
        if (!nomeImmagineUscitaPresente()) {
            int indiceEstratto = (int)(Math.random()*NUM_RADIOBUTTON);
            rb[indiceEstratto].setText(nomeImmagineUscita);
            rb[indiceEstratto].setActionCommand(nomeImmagineUscita);
        }
    }

    // metodo che restituisce true se l'utente ha giocato una partita più volte (quindi stessa categoria d'immagini)
    public boolean utenteHaGiocatoUnaPartitaPiuVolte(Vector<Partita> classifica, Partita partita){
        for (int i = 0; i < classifica.size(); i++) {
            if (classifica.get(i).getNomeUtente().equals(partita.getNomeUtente()) &&
                classifica.get(i).getCategoria_immagini().equals(partita.getCategoria_immagini())) {
                return true;
            }
        }
        return false;
    }

    // metodo per aggiornare il punteggio del player nel caso ha registrato un punteggio migliore
    public boolean aggiornaPunteggioPartita(Vector<Partita> classifica, Partita partita){
        for (int i = 0; i < classifica.size(); i++) {
            if (classifica.get(i).getNomeUtente().equals(partita.getNomeUtente()) &&
                classifica.get(i).getCategoria_immagini().equals(partita.getCategoria_immagini()) &&
                classifica.get(i).getPunteggio() < partita.getPunteggio()) {
                classifica.get(i).setPunteggio(partita.getPunteggio());
                return true;
            }
        }
        return false;
    }

    // costruttore
    public IndovinaImmagineGUI(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI, String nomeFile){

        // file
        file = new File(nomeFile);
        // creazione frame
        frmIndovinaImmagine = new JFrame("Indovina il nome di " + file.getName().substring(0, file.getName().lastIndexOf('.')) + " dall'immagine");
        Container c = frmIndovinaImmagine.getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(Color.BLACK);

        // nomi dei giochi presi da file
        nomi_immagini = ottieniNomiImmaginiDaFile();

        // creazione oggetti per estrarre senza ripetizioni indici casuali
        indiciRadioButton = new EstrazioneNumeriCasualiSenzaRipetizioni(0, nomi_immagini.size());
        indiciImg = new EstrazioneNumeriCasualiSenzaRipetizioni(0, nomi_immagini.size());

        // creazione dei panel
        pnlSud = new JPanel(new GridLayout(2,1));
        pnlNord = new JPanel(new GridLayout(1,3));
        pnlIndovinaImmagine = new JPanel(new GridLayout(1,NUM_RADIOBUTTON));
        pnlBtn = new JPanel(new GridLayout(1,4));
        // modifica sfondo dei panel
        pnlNord.setBackground(Color.DARK_GRAY);
        pnlIndovinaImmagine.setBackground(Color.DARK_GRAY);
        pnlBtn.setBackground(Color.LIGHT_GRAY);

        // creazione ButtonGroup e radioButton
        bg = new ButtonGroup();
        rb = new JRadioButton[NUM_RADIOBUTTON];

        // creazione label
        lblStato = new JLabel("Stato: in corso");
        lblPunteggio = new JLabel("Punteggio: 0");
        lblTempoRimanente = new JLabel("Tempo rimanente: ");
        // modifica colore del testo presente nelle label e allineamento
        lblStato.setForeground(Color.WHITE);
        lblPunteggio.setForeground(Color.WHITE);
        lblTempoRimanente.setForeground(Color.WHITE);
        lblStato.setHorizontalAlignment(SwingConstants.LEFT);
        lblPunteggio.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempoRimanente.setHorizontalAlignment(SwingConstants.RIGHT);

        //creazione bottoni con aggiunta actionListener
        btnConferma = new JButton("Conferma scelta");
        btnConferma.addActionListener(new ListenerConfermaSceltaNomeImmagine(this));
        btnCambiaImg = new JButton("Prossima immagine");
        btnCambiaImg.addActionListener((ActionEvent e )-> {
            int indice = indiciImg.estrai();
            if(indice == -1) {

                Vector<Partita> classifica = gestioneIndovinaImmagineGUI.getClassifica();
                Partita partita = new Partita(gestioneIndovinaImmagineGUI.getUtenteCorrente().getNomeUtente(), gestioneIndovinaImmagineGUI.getCategoriaScelta(), punteggio);

                if (!aggiornaPunteggioPartita(classifica, partita) && !utenteHaGiocatoUnaPartitaPiuVolte(classifica, partita))
                    classifica.add(partita);

                Ordinamento.selectionSort(classifica);
                gestioneIndovinaImmagineGUI.setClassifica(classifica);

                JOptionPane.showMessageDialog(null,
                        "Il gioco è terminato! Punteggio: " + punteggio,"Fine",
                        JOptionPane.WARNING_MESSAGE);
                frmIndovinaImmagine.setVisible(false);
                gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(true);

            } else {
                c.remove(lblImg);
                nomeImmagineUscita = nomi_immagini.get(indice);
                lblImg = new JLabel(new ImageIcon("images/" + file.getName().substring(0, file.getName().lastIndexOf('.')) + "/" + nomeImmagineUscita + ".jpg"));
                c.add(lblImg, BorderLayout.CENTER);
                lblStato.setText("Stato: in corso");
                lblStato.setForeground(Color.WHITE);
                btnCambiaImg.setVisible(false);
                btnConferma.setVisible(true);
                bg.clearSelection();
                t = new ThreadTempoRimanente(this);
                t.start();
                for (int i=0; i < NUM_RADIOBUTTON; i++){
                    rb[i].setEnabled(true);
                }
                cambioPosizioneRadioButton();
            }
        });

        // modifica sfondo e colore del testo dei button
        btnConferma.setBackground(Color.DARK_GRAY);
        btnCambiaImg.setBackground(Color.DARK_GRAY);
        btnConferma.setForeground(Color.WHITE);
        btnCambiaImg.setForeground(Color.WHITE);
        btnCambiaImg.setVisible(false);

        // inizializzazione punteggio
        punteggio = 0;

        // aggiunta componenti ai panel
        pnlNord.add(lblStato);
        pnlNord.add(lblPunteggio);
        pnlNord.add(lblTempoRimanente);
        pnlBtn.add(new JLabel());
        pnlBtn.add(btnConferma);
        pnlBtn.add(btnCambiaImg);
        pnlBtn.add(new JLabel());
        pnlSud.add(pnlIndovinaImmagine);
        pnlSud.add(pnlBtn);

        // creazione radio button
        for (int i = 0; i < NUM_RADIOBUTTON; i++) {
            rb[i] = new JRadioButton();
            rb[i].setForeground(Color.WHITE);
            rb[i].setBackground(Color.DARK_GRAY);
            bg.add(rb[i]);
            pnlIndovinaImmagine.add(rb[i]);
        }

        //aggiunta font ai componenti
        lblStato.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        lblPunteggio.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        lblTempoRimanente.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        btnConferma.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        btnCambiaImg.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        for (int i = 0; i < NUM_RADIOBUTTON; i++) {
            rb[i].setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        }

        // generazione casuale della prima immagine
        int indice = indiciImg.estrai();
        nomeImmagineUscita = nomi_immagini.get(indice);
        lblImg = new JLabel(new ImageIcon("images/" + file.getName().substring(0, file.getName().lastIndexOf('.')) + "/" + nomeImmagineUscita + ".jpg"));
        cambioPosizioneRadioButton();

        //aggiunta componenti al container
        c.add(lblImg, BorderLayout.CENTER);
        c.add(pnlNord, BorderLayout.NORTH);
        c.add(pnlSud, BorderLayout.SOUTH);

        frmIndovinaImmagine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmIndovinaImmagine.setSize(new Dimension(WIDTH,HEIGHT));
        frmIndovinaImmagine.setVisible(true);

        // creazione e avvio del thread timer
        t = new ThreadTempoRimanente(this);
        t.start();
    }

    public void setPunteggio(int punteggio) {
        this.punteggio += punteggio;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public JLabel getLblStato() {
        return lblStato;
    }

    public JLabel getLblPunteggio() {
        return lblPunteggio;
    }

    public JLabel getLblTempoRimanente() {
        return lblTempoRimanente;
    }

    public JButton getBtnCambiaImg() {
        return btnCambiaImg;
    }

    public JButton getBtnConferma() {
        return btnConferma;
    }

    public String getNomeImmagineUscita() {
        return nomeImmagineUscita;
    }

    public ButtonGroup getBg() {
        return bg;
    }

    public File getFile() {
        return file;
    }

    public JRadioButton[] getRb() {
        return rb;
    }

    public ThreadTempoRimanente getT() {
        return t;
    }
}