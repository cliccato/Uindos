package app.indovina_immagini.src;
public class EstrazioneNumeriCasualiSenzaRipetizioni {

    private int min;        // minino
    private int max;        // massimo
    private boolean[] estratto;     // array di boolean

    // metodo per settare tutti gli elementi dell'array a false
    private void setEstrattoFalse() {
        for (int i = 0; i < max; i++) {
            estratto[i] = false;
        }
    }

    // costruttore
    public EstrazioneNumeriCasualiSenzaRipetizioni(int min, int max) {
        this.min = min;
        this.max = max;
        estratto = new boolean[max];
        setEstrattoFalse();
    }

    // metodo che restituisce true se rimangono ancora indici da estrarre, altrimenti restituisce false
    public boolean puoEstrarre(){
        boolean ok = true;
        for (int i = 0; i < max; i++) {
            ok = ok && estratto[i];
        }
        return !ok;
    }

    // metodo per estrarre un indice casuale senza ripetizioni
    public int estrai(){
        int x = -1;

        if (puoEstrarre()) {
            do {
                x = (int) (Math.random()*(max - min) + min);
            } while (estratto[x]);
            estratto[x] = true;
        }
        return x;
    }
}