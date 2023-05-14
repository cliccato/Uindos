package System.app.Windows_settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import System.Login.LoginFrame;
import utils.GestoreFrame;
import utils.RimuoviCartella;

public class ListenerEliminaUtente implements ActionListener {
            private ImpostazioniWindowsFrame impostazioniWindowsFrame;

            public ListenerEliminaUtente(ImpostazioniWindowsFrame impostazioniWindowsFrame){
                this.impostazioniWindowsFrame = impostazioniWindowsFrame;
            }

            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'utente?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String path = "src/System/Users/" + impostazioniWindowsFrame.getNomeUtente() + "/"; // Sostituisci con il percorso della cartella da rimuovere

                File directory = new File(path);

                if (directory.exists()) {
                    if (RimuoviCartella.rimuoviCartella(directory)) {
                        System.out.println("Cartella rimossa con successo.");
                    } else {
                        System.out.println("Impossibile rimuovere la cartella.");
                    }
                    JOptionPane.showMessageDialog(null, "L'utente è stato eliminato.", "Utente eliminato", JOptionPane.INFORMATION_MESSAGE);
                    GestoreFrame.chiudiTuttiFrame();
                    new LoginFrame();
                } else {
                    System.out.println("La cartella non esiste." + path);
                }
                } else if (confirm == JOptionPane.NO_OPTION) {
            // L'utente ha premuto il pulsante "No", non fare nulla
                }
                }
            }