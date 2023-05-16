package System.app.Windows_settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import System.Login.ListenerLogin;
import System.Login.LoginFrame;
import utils.GestoreFrame;
import utils.RimuoviCartella;

public class ListenerEliminaUtente implements ActionListener {
            private ImpostazioniWindowsFrame impostazioniWindowsFrame;

            public ListenerEliminaUtente(ImpostazioniWindowsFrame impostazioniWindowsFrame){
                this.impostazioniWindowsFrame = impostazioniWindowsFrame;
            }

            public void deleteUserCSV() {
                try {
                    File inputFile = new File(ListenerLogin.USERS_FILE_PATH);
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
                    String line;
                    StringBuilder updatedContent = new StringBuilder();
                    int lineCount = 0;
                    int totalLines = getFileLineCount(ListenerLogin.USERS_FILE_PATH);
                    
                    while ((line = reader.readLine()) != null) {
                        StringTokenizer stringTokenizer = new StringTokenizer(line, ListenerLogin.FIELD_DELIMITATOR);
                        String username = stringTokenizer.nextToken();
                        String password = stringTokenizer.nextToken();
            
                        if (!username.equals(impostazioniWindowsFrame.getNomeUtente())) {
                            updatedContent.append(String.join(ListenerLogin.FIELD_DELIMITATOR, username, password))
                                          .append("\n");
                            lineCount++;
                        }
                    }
            
                    reader.close();
            
                    if (lineCount < totalLines) {
                        updatedContent.setLength(updatedContent.length() - 1);
                    }
            
                    FileWriter writer = new FileWriter(ListenerLogin.USERS_FILE_PATH);
                    writer.write(updatedContent.toString());
                    writer.close();
                } catch (IOException e) {
                    // Gestisci l'IOException
                }
            }
            
            private int getFileLineCount(String filePath) throws IOException {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                int lineCount = 0;
                while (reader.readLine() != null) {
                    lineCount++;
                }
                reader.close();
                return lineCount;
            }

            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'utente?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String path = "src/System/Users/" + impostazioniWindowsFrame.getNomeUtente() + "/"; // Sostituisci con il percorso della cartella da rimuovere

                File directory = new File(path);

                if (directory.exists()) {
                    if (RimuoviCartella.rimuoviCartella(directory)) {
                        // deleteUserCSV();
                        System.out.println("Cartella rimossa con successo.");
                        deleteUserCSV();
                        JOptionPane.showMessageDialog(null, "L'utente è stato eliminato.", "Utente eliminato", JOptionPane.INFORMATION_MESSAGE);
                        GestoreFrame.chiudiTuttiFrame();
                        new LoginFrame();
                    } else {
                        System.out.println("Impossibile rimuovere la cartella.");
                    }
                } else {
                    System.out.println("La cartella non esiste." + path);
                }
                } else if (confirm == JOptionPane.NO_OPTION) {
            // L'utente ha premuto il pulsante "No", non fare nulla
                }
                }
            }