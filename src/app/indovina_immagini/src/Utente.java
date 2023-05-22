package app.indovina_immagini.src;
public class Utente extends Persona {

    private String nomeUtente;      // nome utente
    private String password;        // password

    // costruttore
    public Utente(String nomeUtente, String nomeReale, Data dataNascita, String sesso, String password, int ID) {
        super(nomeReale, dataNascita, sesso);
        this.nomeUtente = nomeUtente;
        this.password = password;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }
}