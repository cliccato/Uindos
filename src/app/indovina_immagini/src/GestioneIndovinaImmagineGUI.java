package app.indovina_immagini.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class GestioneIndovinaImmagineGUI {

    private JFrame frmPrincipale;                   // frame principale
    private static final String INDOVINA_IMMAGINE_LOGO_PATH = "images/schermata_iniziale.jpg";
    private JLabel lblUtente;                       // label utente
    private JLabel lblStato;                        // label stato
    private JMenuBar menuBar;                       // menu bar
    private JMenu voceUtente;                       // voce dedicata all'utente
    private JMenuItem voceUtenteAccedi;             // voce per accedere presente nella sezione utente
    private JMenuItem voceUtenteRegistrati;         // voce per registrarsi presente nella sezione utente
    private JMenuItem voceUtenteLogout;             // voce per fare logout presente nella sezione utente
    private JMenu voceIndovinaImmagini;             // voce per indovina immagini
    private JMenuItem voceIndovinaImmaginiVideogiochi;      // voce indovina immagini di videogiochi
    private JMenuItem voceIndovinaImmaginiCalciatori;       // voce indovina immagini di calciatori
    private JMenuItem voceIndovinaImmaginiStarCinema;       // voce indovina immagini di star del cinema
    private JMenu voceClassifica;                           // voce per la classifica
    private JMenuItem voceClassificaGenerale;               // voce per mostrare la classifica generale (quindi con tutte le categorie d'immagini)
    private JMenu voceClassificaSpecificaCategoriaImmagini;         // voce per la classifica specifica
    private JMenuItem voceClassificaCategoriaImmaginiVideogiochi;   // voce per mostrare la classifica su immagini di videogiochi
    private JMenuItem voceClassificaCategoriaImmaginiCalciatori;    // voce per mostrare la classifica su immagini di calciatori
    private JMenuItem voceClassificaCategoriaImmaginiStarCinema;    // voce per mostrare la classifica su immagini di star del cinema
    private JLabel lblImgIniziale;          // immagine della home
    private String categoriaScelta;         // categoria d'immagini scelta dall'utente
    private Utente utenteCorrente;          // utente che ha fatto il login attualmente
    private Vector<Utente> utenti;          // utenti registrati
    private Vector<Integer> IDGiaDetti;     // ID degli utenti
    private Vector<Partita> classifica;     // classifica generale
    // font
    public static Font fntBtnCslTesto = new Font("Arial", Font.BOLD, 12);
    public static Font fntLblMenuItem = new Font("Serif", Font.ITALIC, 12);

    // costruttore
    public GestioneIndovinaImmagineGUI(){

        // creazione frame
        frmPrincipale = new JFrame("Applicazione indovina nome dall'immagine");
        frmPrincipale.setIconImage((new ImageIcon(INDOVINA_IMMAGINE_LOGO_PATH).getImage()));
        Container c = frmPrincipale.getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(Color.DARK_GRAY);

        // creazione array
        utenti = new Vector<>();
        IDGiaDetti = new Vector<>();
        classifica = new Vector<>();

        // creazione label
        lblImgIniziale = new JLabel(new ImageIcon("images/schermata_iniziale.jpg"));
        lblStato = new JLabel("");
        lblUtente = new JLabel("Nessun account registrato");
        lblStato.setForeground(Color.WHITE);
        lblUtente.setForeground(Color.WHITE);
        lblStato.setHorizontalAlignment(SwingConstants.LEFT);
        lblUtente.setHorizontalAlignment(SwingConstants.RIGHT);

        // creazione menuBar
        menuBar = new JMenuBar();
        frmPrincipale.setJMenuBar(menuBar);

        // creazione menu e menuItem
        voceUtente = new JMenu("Utente");
        voceUtenteAccedi = new JMenuItem("Accedi");
        voceUtenteRegistrati = new JMenuItem("Registrati");
        voceUtenteLogout = new JMenuItem("Logout");
        voceIndovinaImmagini = new JMenu("Indovina immagini");
        voceIndovinaImmaginiVideogiochi = new JMenuItem("videogiochi");
        voceIndovinaImmaginiCalciatori = new JMenuItem("calciatori");
        voceIndovinaImmaginiStarCinema = new JMenuItem("star_cinema");
        voceClassifica = new JMenu("Classifica");
        voceClassificaGenerale = new JMenuItem("Classifica generale");
        voceClassificaSpecificaCategoriaImmagini = new JMenu("Classifica su");
        voceClassificaCategoriaImmaginiVideogiochi = new JMenuItem("Immagini di videogiochi");
        voceClassificaCategoriaImmaginiCalciatori = new JMenuItem("Immagini di calciatori");
        voceClassificaCategoriaImmaginiStarCinema = new JMenuItem("Immagini di star del cinema");

        // aggiunta font
        voceUtente.setFont(fntLblMenuItem);
        voceUtenteAccedi.setFont(fntLblMenuItem);
        voceUtenteRegistrati.setFont(fntLblMenuItem);
        voceUtenteLogout.setFont(fntLblMenuItem);
        voceIndovinaImmagini.setFont(fntLblMenuItem);
        voceIndovinaImmaginiVideogiochi.setFont(fntLblMenuItem);
        voceIndovinaImmaginiCalciatori.setFont(fntLblMenuItem);
        voceIndovinaImmaginiStarCinema.setFont(fntLblMenuItem);
        voceClassifica.setFont(fntLblMenuItem);
        voceClassificaGenerale.setFont(fntLblMenuItem);
        voceClassificaSpecificaCategoriaImmagini.setFont(fntLblMenuItem);
        voceClassificaCategoriaImmaginiVideogiochi.setFont(fntLblMenuItem);
        voceClassificaCategoriaImmaginiCalciatori.setFont(fntLblMenuItem);
        voceClassificaCategoriaImmaginiStarCinema.setFont(fntLblMenuItem);
        lblStato.setFont(fntLblMenuItem);
        lblUtente.setFont(fntLblMenuItem);

        voceUtente.add(voceUtenteAccedi);
        voceUtente.add(voceUtenteRegistrati);
        voceUtente.add(voceUtenteLogout);
        voceIndovinaImmagini.add(voceIndovinaImmaginiVideogiochi);
        voceIndovinaImmagini.add(voceIndovinaImmaginiCalciatori);
        voceIndovinaImmagini.add(voceIndovinaImmaginiStarCinema);
        voceClassificaSpecificaCategoriaImmagini.add(voceClassificaCategoriaImmaginiVideogiochi);
        voceClassificaSpecificaCategoriaImmagini.add(voceClassificaCategoriaImmaginiCalciatori);
        voceClassificaSpecificaCategoriaImmagini.add(voceClassificaCategoriaImmaginiStarCinema);
        voceClassifica.add(voceClassificaGenerale);
        voceClassifica.add(voceClassificaSpecificaCategoriaImmagini);

        menuBar.add(voceUtente);
        menuBar.add(voceIndovinaImmagini);
        menuBar.add(voceClassifica);

        voceUtenteAccedi.setVisible(false);
        voceUtenteLogout.setVisible(false);

        // aggiunta actionListener ai menuItem
        voceUtenteAccedi.addActionListener((ActionEvent e) -> {
            lblStato.setText("");
            frmPrincipale.setVisible(false);
            new AccediUtenteGUI(this);
        });

        voceUtenteRegistrati.addActionListener((ActionEvent e) -> {
            lblStato.setText("");
            frmPrincipale.setVisible(false);
            new RegistrazioneUtenteGUI(this);
        });

        voceUtenteLogout.addActionListener((ActionEvent e) -> {
            utenteCorrente = null;
            lblUtente.setText("Accesso effettuato come: nessuno");
            lblStato.setText("Logout eseguito correttamente!");
            lblStato.setForeground(Color.GREEN);
            voceUtenteAccedi.setVisible(true);
            voceUtenteRegistrati.setVisible(true);
            voceUtenteLogout.setVisible(false);
        });

        voceClassificaGenerale.addActionListener((ActionEvent e) -> {
            lblStato.setText("");
            JFrame frame = new JFrame(voceClassificaGenerale.getText());

            JButton btnIndietro = new JButton("INDIETRO");
            btnIndietro.setFont(GestioneIndovinaImmagineGUI.fntBtnCslTesto);
            btnIndietro.addActionListener((ActionEvent event) -> {
                frmPrincipale.setVisible(true);
                frame.setVisible(false);
            });

            String[] campi = {"Nome utente", "Categoria di immagini", "Punteggio"};

            String[][] classificaGenerale = new String[classifica.size()][3];
            for (int i = 0; i < classifica.size(); i++) {
                classificaGenerale[i][0] = classifica.get(i).getNomeUtente();
                classificaGenerale[i][1] = classifica.get(i).getCategoria_immagini();
                classificaGenerale[i][2] = String.valueOf(classifica.get(i).getPunteggio());
            }

            JTable tabellaClassifica = new JTable(classificaGenerale, campi);
            tabellaClassifica.setFont(GestioneIndovinaImmagineGUI.fntLblMenuItem);
            tabellaClassifica.setEnabled(false);
            tabellaClassifica.setGridColor(Color.YELLOW);
            tabellaClassifica.setBackground(Color.CYAN);
            JScrollPane scrollPane = new JScrollPane(tabellaClassifica);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            frame.add(btnIndietro, BorderLayout.WEST);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frmPrincipale.setVisible(false);
        });

        voceIndovinaImmaginiVideogiochi.setActionCommand(voceIndovinaImmaginiVideogiochi.getText());
        voceIndovinaImmaginiCalciatori.setActionCommand(voceIndovinaImmaginiCalciatori.getText());
        voceIndovinaImmaginiStarCinema.setActionCommand(voceIndovinaImmaginiStarCinema.getText());
        voceClassificaCategoriaImmaginiVideogiochi.setActionCommand(voceIndovinaImmaginiVideogiochi.getText());
        voceClassificaCategoriaImmaginiCalciatori.setActionCommand(voceIndovinaImmaginiCalciatori.getText());
        voceClassificaCategoriaImmaginiStarCinema.setActionCommand(voceIndovinaImmaginiStarCinema.getText());

        voceIndovinaImmaginiVideogiochi.addActionListener(new ListenerConfermaSceltaCategoriaImmagini(this));
        voceIndovinaImmaginiCalciatori.addActionListener(new ListenerConfermaSceltaCategoriaImmagini(this));
        voceIndovinaImmaginiStarCinema.addActionListener(new ListenerConfermaSceltaCategoriaImmagini(this));
        voceClassificaCategoriaImmaginiVideogiochi.addActionListener(new ListenerClassificaCategoriaImmagini(this));
        voceClassificaCategoriaImmaginiCalciatori.addActionListener(new ListenerClassificaCategoriaImmagini(this));
        voceClassificaCategoriaImmaginiStarCinema.addActionListener(new ListenerClassificaCategoriaImmagini(this));

        // aggiunta componenti al container
        c.add(lblUtente, BorderLayout.NORTH);
        c.add(lblStato, BorderLayout.SOUTH);
        c.add(lblImgIniziale, BorderLayout.CENTER);

        //creazione dimension per il frame
        Dimension dimension = new Dimension(800, 600);
        frmPrincipale.setSize(dimension);
        frmPrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPrincipale.setVisible(true);
    }

    public void setClassifica(Vector<Partita> classifica) {
        this.classifica = classifica;
    }

    public void setUtenteCorrente(Utente utenteCorrente) {
        this.utenteCorrente = utenteCorrente;
    }

    public void setUtenti(Vector<Utente> utenti) {
        this.utenti = utenti;
    }

    public void setCategoriaScelta(String categoriaScelta) {
        this.categoriaScelta = categoriaScelta;
    }

    public JFrame getFrmPrincipale() {
        return frmPrincipale;
    }

    public Vector<Utente> getUtenti() {
        return utenti;
    }

    public Vector<Integer> getIDGiaDetti() {
        return IDGiaDetti;
    }

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    public JLabel getLblUtente() {
        return lblUtente;
    }

    public JLabel getLblStato() {
        return lblStato;
    }

    public JMenuItem getVoceUtenteAccedi() {
        return voceUtenteAccedi;
    }

    public JMenuItem getVoceUtenteRegistrati() {
        return voceUtenteRegistrati;
    }

    public JMenuItem getVoceUtenteLogout() {
        return voceUtenteLogout;
    }

    public String getCategoriaScelta() {
        return categoriaScelta;
    }

    public Vector<Partita> getClassifica() {
        return classifica;
    }
}