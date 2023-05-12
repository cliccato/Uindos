/**
 * AvviaTempo è una classe che gestisce gli eventi per avviare e fermare un timer.
 * 
 * Implementa l'interfaccia ActionListener per gestire gli eventi dei pulsanti.
 * 
 * La classe avvia un thread per eseguire il conteggio dei secondi del timer.
 * 
 * @author Giorgio Justin Fasullo
 * @version 1.0
 * @since 2023-05-12
 */

package app.UserClock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvviaTempo implements ActionListener {

    private CustomStopWatch timer;
    private Thread thread;

    /**
     * Costruttore della classe AvviaTempo.
     * 
     * @param timer l'oggetto CustomStopWatch che rappresenta il timer da avviare e fermare
     */
    public AvviaTempo(CustomStopWatch timer) {
        this.timer = timer;
        thread = new Thread(new ContaSecondi(timer));
    }

    /**
     * Gestisce gli eventi dei pulsanti per avviare e fermare il timer.
     * 
     * @param e l'evento generato dal pulsante
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Avvia":
                timer.run();
                if (!thread.isAlive()) {
                    thread = new Thread(new ContaSecondi(timer));
                    thread.start();
                    System.out.println("Tempo");
                }
                break;
            case "Stop":
                timer.stop();
                System.out.println("Stop");
                break;
        }
    }
}