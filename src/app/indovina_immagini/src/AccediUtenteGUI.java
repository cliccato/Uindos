package app.indovina_immagini.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AccediUtenteGUI {

    private GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI;        // gestione indovina immagine gui
    private JFrame frmAccediUtente;         // frame accedi utente
    private JPanel pnlAccediUtente;         // pannello accedi utente
    private JLabel lblStato;                // label per dare informazioni all'utente
    private JLabel lblNomeUtente;           // label nome utente
    private JLabel lblPassword;             // label password
    private JTextField cslNomeUtente;       // casella nome utente
    private JTextField cslPassword;         // casella password
    private JButton btnConferma;            // bottone di conferma
    private JButton btnIndietro;            // bottone per ritornare alla home

    // costruttore
    public AccediUtenteGUI(GestioneIndovinaImmagineGUI gestioneIndovinaImmagineGUI){

        this.gestioneIndovinaImmagineGUI = gestioneIndovinaImmagineGUI;

        //creazione finestra
        frmAccediUtente = new JFrame("Accedi");
        Container c = frmAccediUtente.getContentPane();
        c.setLayout(new BorderLayout());
        //creazione pannelli
        pnlAccediUtente = new JPanel(new GridLayout(4,1));
        //creazione bottoni
        btnConferma = new JButton("CONFERMA");
        btnIndietro = new JButton("INDIETRO");
        //aggiunta font e ActionListener ai bottoni
        btnConferma.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        btnIndietro.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        btnConferma.addActionListener(new ListenerConfermaAccedi(gestioneIndovinaImmagineGUI, this));
        btnIndietro.addActionListener((ActionEvent e) -> {
            gestioneIndovinaImmagineGUI.getFrmPrincipale().setVisible(true);
            frmAccediUtente.setVisible(false);
        });

        //creazione label
        lblStato = new JLabel("Stato: in corso");
        lblNomeUtente = new JLabel("Nome utente:");
        lblPassword = new JLabel("Password:");

        //creazione caselle di testo
        cslNomeUtente = new JTextField("");
        cslPassword = new JTextField("");

        //aggiunta del font a label e caselle di testo
        lblStato.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        lblNomeUtente.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        lblPassword.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
        cslNomeUtente.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        cslPassword.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
        //aggiunta label e caselle di testo al pannello
        pnlAccediUtente.add(lblNomeUtente);
        pnlAccediUtente.add(cslNomeUtente);
        pnlAccediUtente.add(lblPassword);
        pnlAccediUtente.add(cslPassword);
        //aggiunta dei bottoni e pannello al container
        c.add(pnlAccediUtente, BorderLayout.CENTER);
        c.add(btnConferma, BorderLayout.SOUTH);
        c.add(btnIndietro, BorderLayout.WEST);
        c.add(lblStato, BorderLayout.NORTH);

        //creazione dimension per il frame
        Dimension dimension = new Dimension(600, 600);
        frmAccediUtente.setSize(dimension);
        frmAccediUtente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmAccediUtente.setVisible(true);
    }

    public JFrame getFrmAccediUtente() {
        return frmAccediUtente;
    }

    public JLabel getLblStato() {
        return lblStato;
    }

    public JTextField getCslNomeUtente() {
        return cslNomeUtente;
    }

    public JTextField getCslPassword() {
        return cslPassword;
    }
}