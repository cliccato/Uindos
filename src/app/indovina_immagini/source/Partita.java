package app.indovina_immagini.source;
public class Partita {

    private String nomeUtente;              // nome utente
    private String categoria_immagini;      // categoria d'immagini scelta dall'utente
    private int punteggio;                  // punteggio utente

    // costruttore
    public Partita(String nomeUtente, String categoria_immagini, int punteggio){
        this.nomeUtente = nomeUtente;
        this.categoria_immagini = categoria_immagini;
        this.punteggio = punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getCategoria_immagini() {
        return categoria_immagini;
    }

    public int getPunteggio() {
        return punteggio;
    }
}