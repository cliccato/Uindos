/**
 * ContaSecondi è una classe che implementa l'interfaccia Runnable e viene utilizzata per contare i secondi
 * durante l'esecuzione di un oggetto CustomStopWatch.
 * 
 * È responsabile di aggiornare il tempo visualizzato sull'etichetta del timer e di fermare l'aggiornamento
 * quando il timer viene interrotto.
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since [Inserisci la data di creazione]
 */

package app.UserClock;
public class ContaSecondi implements Runnable {
    private CustomStopWatch timer;

    /**
     * Crea un'istanza di ContaSecondi con l'oggetto CustomStopWatch specificato.
     * 
     * @param timer l'oggetto CustomStopWatch utilizzato per contare i secondi
     */
    public ContaSecondi(CustomStopWatch timer) {
        this.timer = timer;
    }

    /**
     * Esegue il conteggio dei secondi.
     * 
     * Durante l'esecuzione, aggiorna il tempo visualizzato sull'etichetta del timer e controlla
     * continuamente se il timer è in esecuzione. Quando il timer viene fermato, l'aggiornamento viene interrotto.
     */
    public void run() {
        try {
            while (timer.getIsRunning()) {
                timer.updateTempo();
                timer.getLblTempo().setText("Avvia: " + timer.getSecondi());
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e);
        }
    }
}