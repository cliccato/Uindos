package app.indovina_immagini.src;
import java.awt.*;

public class ThreadTempoRimanente extends Thread {

    private IndovinaImmagineGUI indovinaImmagineGUI;        // GUI
    private int minuti;         // minuti
    private int secondi;        // secondi
    private boolean tempoFinito;        // tempo finito, si ha quando minuti e secondi sono pari a 0
    private boolean interrupted;        // interruzione del thread, si ha quando l'utente indovina il nome dall'immagine

    // costruttore
    public ThreadTempoRimanente(IndovinaImmagineGUI indovinaImmagineGUI){
        this.indovinaImmagineGUI = indovinaImmagineGUI;
        minuti = 0;
        secondi = 30;
        tempoFinito = false;
        interrupted = false;
    }

    // metodo per informare l'utente su un apposita label che non ha indovinato e il tempo è scaduto
    private void nonHaiIndovinato(){
        if (secondi <= 0 && minuti == 0 && !interrupted) {
            indovinaImmagineGUI.getBtnConferma().setVisible(false);
            indovinaImmagineGUI.getBtnCambiaImg().setVisible(true);
            indovinaImmagineGUI.getLblStato().setText("Stato: tempo scaduto! era: " + indovinaImmagineGUI.getNomeImmagineUscita() + "!");
            indovinaImmagineGUI.getLblStato().setForeground(Color.RED);
        }
    }

    // timer gestito dal thread
    public void timer(){

        try {
            do {
                tempoFinito = (minuti == 0 && secondi == 0);

                if (secondi >= 0) {
                    indovinaImmagineGUI.getLblTempoRimanente().setText(String.format("Tempo rimanente: %02d:%02d", minuti, secondi));
                    secondi--;
                    Thread.sleep(1000);
                } else if (minuti > 0) {
                    minuti--;
                    secondi = 59;
                }

            } while (!tempoFinito);
            secondi++;
        } catch (InterruptedException e) {
            interrupted = true;     // viene segnalato che il thread era stato interrotto (si ha quando l'utente indovina il nome dall'immagine)
        }                           // ma gestendo l'eccezione il thread non smette di funzionare
        indovinaImmagineGUI.setPunteggio(secondi);
        indovinaImmagineGUI.getLblPunteggio().setText("Punteggio: " + indovinaImmagineGUI.getPunteggio());
        nonHaiIndovinato();
    }

    // metodo run
    @Override
    public void run() {
        timer();
    }
}