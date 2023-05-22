package app.indovina_immagini.src;
import javax.swing.*;

import utils.GestoreConfig;
import utils.GestoreFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrazioneUtenteGUI {

    private GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;        // gui
    private JFrame frmRegistrazioneUtente;      // frm registrazione utente
    private JPanel pnlRegistrazioneUtente;      // panel per la registrazione
    private JPanel pnlSud;                      // pnl sud
    private JLabel lblStato;                    // label per l'utente
    private JLabel lblNomeUtente;               // label nome utente
    private JLabel lblNomeReale;                // label nome reale
    private JLabel lblPassword;                 // label password
    private JLabel lblGiornoNascitaUtente;      // label giorno nascita utente
    private JLabel lblMeseNascitaUtente;        // label mese nascita utente
    private JLabel lblAnnoNascitaUtente;        // label anno nascita utente
    private JLabel lblSessoUtente;              // label sesso utente
    private JTextField cslNomeUtente;           // casella nome utente
    private JTextField cslNomeReale;            // casella nome reale
    private JTextField cslPassword;             // casella password
    private JTextField cslGiornoNascitaUtente;  // casella giorno nascita utente
    private JTextField cslMeseNascitaUtente;    // casella mese nascita utente
    private JTextField cslAnnoNascitaUtente;    // casella anno nascita utente
    private JTextField cslSessoUtente;          // casella sesso utente
    private JButton btnConferma;                // button per confermare la registrazione
    private JButton btnIndietro;                // button per tornare all home
    private JButton btnNuovaRegistrazione;      // button per nuova registrazione
    private Font font;
    // costruttore
    public RegistrazioneUtenteGUI(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI){

        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;

        //creazione finestra
        frmRegistrazioneUtente = new JFrame("Registrazione utente");
        Container c = frmRegistrazioneUtente.getContentPane();
        c.setLayout(new BorderLayout());
        //creazione pannelli
        pnlRegistrazioneUtente = new JPanel(new GridLayout(3,6));
        pnlSud = new JPanel(new GridLayout(1,2));
        //creazione bottoni
        btnConferma = new JButton("CONFERMA");
        btnIndietro = new JButton("INDIETRO");
        btnNuovaRegistrazione = new JButton("NUOVO");
        //aggiunta font e ActionListener ai bottoni
        font = (Font) GestoreConfig.getConfig(gestioneIndovinaImmagineGUI.getUsername(), GestoreConfig.FONT);
        btnConferma.setFont( font);
        btnIndietro.setFont( font);
        btnNuovaRegistrazione.setFont( font);
        btnConferma.addActionListener(new ListenerConfermaNuovaRegistrazione(gestioneIndovinaImmagineGUI, this));
        btnIndietro.addActionListener((ActionEvent e) -> {
            gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(true);
            frmRegistrazioneUtente.setVisible(false);
        });
        btnNuovaRegistrazione.addActionListener((ActionEvent e) ->{
            cslNomeUtente.setText("");
            cslNomeReale.setText("");
            cslPassword.setText("");
            cslGiornoNascitaUtente.setText("");
            cslMeseNascitaUtente.setText("");
            cslAnnoNascitaUtente.setText("");
            cslSessoUtente.setText("");
            lblStato.setText("Stato: in corso");
            lblStato.setForeground(Color.BLACK);
        });

        //creazione label
        lblStato = new JLabel("Stato: in corso");
        lblNomeUtente = new JLabel("Nome utente:");
        lblNomeReale = new JLabel("Nome reale:");
        lblPassword = new JLabel("Password:");
        lblGiornoNascitaUtente = new JLabel("Giorno nascita:");
        lblMeseNascitaUtente = new JLabel("Mese nascita:");
        lblAnnoNascitaUtente = new JLabel("Anno nascita:");
        lblSessoUtente = new JLabel("Sesso (facoltativo):");
        //creazione caselle di testo
        cslNomeUtente = new JTextField("");
        cslNomeReale = new JTextField("");
        cslPassword = new JTextField("");
        cslGiornoNascitaUtente = new JTextField("");
        cslMeseNascitaUtente = new JTextField("");
        cslAnnoNascitaUtente = new JTextField("");
        cslSessoUtente = new JTextField("");
        //aggiunta del font a label e caselle di testo
        lblStato.setFont( font);
        lblNomeUtente.setFont( font);
        lblNomeReale.setFont( font);
        lblPassword.setFont( font);
        lblGiornoNascitaUtente.setFont( font);
        lblMeseNascitaUtente.setFont( font);
        lblAnnoNascitaUtente.setFont( font);
        lblSessoUtente.setFont( font);
        cslNomeUtente.setFont( font);
        cslNomeReale.setFont( font);
        cslPassword.setFont( font);
        cslGiornoNascitaUtente.setFont( font);
        cslMeseNascitaUtente.setFont( font);
        cslAnnoNascitaUtente.setFont( font);
        cslSessoUtente.setFont( font);
        //aggiunta label e caselle di testo al pannello
        pnlRegistrazioneUtente.add(lblNomeUtente);
        pnlRegistrazioneUtente.add(cslNomeUtente);
        pnlRegistrazioneUtente.add(lblNomeReale);
        pnlRegistrazioneUtente.add(cslNomeReale);
        pnlRegistrazioneUtente.add(lblPassword);
        pnlRegistrazioneUtente.add(cslPassword);
        pnlRegistrazioneUtente.add(lblGiornoNascitaUtente);
        pnlRegistrazioneUtente.add(cslGiornoNascitaUtente);
        pnlRegistrazioneUtente.add(lblMeseNascitaUtente);
        pnlRegistrazioneUtente.add(cslMeseNascitaUtente);
        pnlRegistrazioneUtente.add(lblAnnoNascitaUtente);
        pnlRegistrazioneUtente.add(cslAnnoNascitaUtente);
        pnlRegistrazioneUtente.add(lblSessoUtente);
        pnlRegistrazioneUtente.add(cslSessoUtente);
        pnlRegistrazioneUtente.add(new JLabel());
        pnlRegistrazioneUtente.add(new JLabel());
        pnlRegistrazioneUtente.add(new JLabel());
        pnlRegistrazioneUtente.add(new JLabel());
        pnlSud.add(btnConferma);
        pnlSud.add(btnNuovaRegistrazione);

        //aggiunta dei bottoni e pannello al container
        c.add(pnlRegistrazioneUtente, BorderLayout.CENTER);
        c.add(pnlSud, BorderLayout.SOUTH);
        c.add(btnIndietro, BorderLayout.WEST);
        c.add(lblStato, BorderLayout.NORTH);
        //creazione dimension per il frame
        Dimension dimension = new Dimension(850, 500);
        frmRegistrazioneUtente.setSize(dimension);
        frmRegistrazioneUtente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmRegistrazioneUtente.setVisible(true);
        GestoreFrame.aggiungiFrame(frmRegistrazioneUtente);
    }

    public JLabel getLblStato() {
        return lblStato;
    }

    public JTextField getCslPassword() {
        return cslPassword;
    }

    public JTextField getCslNomeUtente() {
        return cslNomeUtente;
    }

    public JTextField getCslAnnoNascitaUtente() {
        return cslAnnoNascitaUtente;
    }

    public JTextField getCslGiornoNascitaUtente() {
        return cslGiornoNascitaUtente;
    }

    public JTextField getCslNomeReale() {
        return cslNomeReale;
    }

    public JTextField getCslMeseNascitaUtente() {
        return cslMeseNascitaUtente;
    }

    public JTextField getCslSessoUtente() {
        return cslSessoUtente;
    }

    public JFrame getFrmRegistrazioneUtente() {
        return frmRegistrazioneUtente;
    }
}