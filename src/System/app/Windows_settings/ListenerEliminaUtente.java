package System.app.Windows_settings;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import System.Login.ListenerLogin;
import System.Login.LoginFrame;
import utils.GestoreFrame;
import utils.GestoreCartelle;
import utils.UindosPath;

public class ListenerEliminaUtente implements MouseListener {
    private ImpostazioniWindowsFrame impostazioniWindowsFrame;
    private JLabel lblEliminaUtente;

    public ListenerEliminaUtente(ImpostazioniWindowsFrame impostazioniWindowsFrame, JLabel lblEliminaUtente) {
        this.impostazioniWindowsFrame = impostazioniWindowsFrame;
        this.lblEliminaUtente = lblEliminaUtente;
    }

    public void deleteUserCSV() {
        try {
            File inputFile = new File(UindosPath.USERS_FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String line;
            StringBuilder updatedContent = new StringBuilder();
            int lineCount = 0;
            int totalLines = getFileLineCount(UindosPath.USERS_FILE_PATH);

            while ((line = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ListenerLogin.FIELD_DELIMITATOR);
                String username = stringTokenizer.nextToken();
                String password = stringTokenizer.nextToken();

                if (!username.equals(impostazioniWindowsFrame.getNomeUtente())) {
                    updatedContent.append(String.join(ListenerLogin.FIELD_DELIMITATOR, username, password)).append("\n");
                    lineCount++;
                }
            }

            reader.close();

            if (lineCount < totalLines) {
                updatedContent.setLength(updatedContent.length() - 1);
            }

            FileWriter writer = new FileWriter(UindosPath.USERS_FILE_PATH);
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

    public void mouseClicked(MouseEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'utente?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String password = JOptionPane.showInputDialog(null, "Inserisci la password per confermare l'eliminazione:", "Conferma password", JOptionPane.PLAIN_MESSAGE);

            // Verifica la password
            if (password == null) {

            } else if (verificaPassword(password)) {
                String path = UindosPath.USER_FOLDER_PATH + impostazioniWindowsFrame.getNomeUtente() + File.separator;
                File directory = new File(path);

                if (directory.exists()) {
                    if (GestoreCartelle.rimuoviCartella(directory)) {
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
            } else {
                JOptionPane.showMessageDialog(null, "Password non corretta. Eliminazione utente annullata.", "Password errata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean verificaPassword(String password) {
        if (password.equals(impostazioniWindowsFrame.getPasswordUtente())) {
            return true;
        }
        return false;
    }

    public void mouseEntered(MouseEvent e) {
        lblEliminaUtente.setForeground(new Color(128, 0, 0)); // Rosso scuro
    }

    public void mouseExited(MouseEvent e) {
        lblEliminaUtente.setForeground(Color.RED);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}