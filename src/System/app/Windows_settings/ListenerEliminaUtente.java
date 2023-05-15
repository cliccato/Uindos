package System.app.Windows_settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import System.Desktop.DesktopFrame;
import System.Login.ListenerLogin;
import System.Login.LoginFrame;
import utils.RimuoviCartella;

public class ListenerEliminaUtente implements ActionListener {
            private ImpostazioniWindowsFrame impostazioniWindowsFrame;

            public ListenerEliminaUtente(ImpostazioniWindowsFrame impostazioniWindowsFrame){
                this.impostazioniWindowsFrame = impostazioniWindowsFrame;
            }

            // private void deleteUserCSV () {
            //     try {
            //         File inputFile = new File(ListenerLogin.USERS_FILE_PATH);
            //         BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            //         String line;
            //         StringBuilder updatedContent = new StringBuilder();
            //         int lineCount = 0;
            //         while ((line = reader.readLine()) != null) {
            //             StringTokenizer stringTokenizer = new StringTokenizer(line, ListenerLogin.FIELD_DELIMITATOR);
            //             String username = stringTokenizer.nextToken();
            //             String password = stringTokenizer.nextToken();
            //             System.out.println(username);
            //             System.out.println(impostazioniWindowsFrame.getNomeUtente());
            //             if (username.equals(impostazioniWindowsFrame.getNomeUtente())) {
            //                 // Modifica la password dell'utente
            //                 System.out.println(cambiaPasswordFrame.getNewPassword());
            //                 password = cambiaPasswordFrame.getNewPassword();
            //                 System.out.println(password);
            //             }
            //             updatedContent.append(String.join(ListenerLogin.FIELD_DELIMITATOR, new String[] {
            //                 username,
            //                 password
            //             }));
            //             lineCount++;
            //             if (lineCount < getFileLineCount(ListenerLogin.USERS_FILE_PATH)) {
            //                 // Aggiungi una nuova riga solo se non è l'ultima riga del file
            //                 updatedContent.append("\n");
            //             }
            //         }
            //         reader.close();

            //         FileWriter writer = new FileWriter(ListenerLogin.USERS_FILE_PATH);
            //         writer.write(updatedContent.toString());
            //         writer.close();
            //     }

            //     } catch (IOException e) {
            //         // Gestisci l'IOException
            //     }
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'utente?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String path = "src/System/Users/" + impostazioniWindowsFrame.getNomeUtente() + "/"; // Sostituisci con il percorso della cartella da rimuovere

                File directory = new File(path);

                if (directory.exists()) {
                    if (RimuoviCartella.rimuoviCartella(directory)) {
                        // deleteUserCSV();
                        System.out.println("Cartella rimossa con successo.");
                    } else {
                        System.out.println("Impossibile rimuovere la cartella.");
                    }

                    JOptionPane.showMessageDialog(null, "L'utente è stato eliminato.", "Utente eliminato", JOptionPane.INFORMATION_MESSAGE);
                    impostazioniWindowsFrame.chiudiFrame();
                    new LoginFrame();
                } else {
                    System.out.println("La cartella non esiste." + path);
                }
                } else if (confirm == JOptionPane.NO_OPTION) {
            // L'utente ha premuto il pulsante "No", non fare nulla
                }
                }
            }