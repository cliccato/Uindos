package app.Notepad;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import System.Desktop.DesktopFrame;
import utils.UindosDirectoryName;
import utils.UindosPath;

public class NotepadListener implements ActionListener{
    private NotepadFrame NF;
    private boolean isFileSaved;

    public NotepadListener(NotepadFrame NF) {
        this.NF = NF;
        isFileSaved = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                NF.getTextArea().setText("");
                NF.getFrame().setTitle("Notepad - (Nuovo)");
                NF.setName("");
                isFileSaved = false;
                break;
            case "Save":
                             String title = NF.getFrame().getTitle();
                if (!isFileSaved && title.equals("Notepad - (Nuovo)")) {
                    String name = JOptionPane.showInputDialog("Inserisci nome file:");
                    if (name == null || name.trim().isEmpty()) {
                        // L'utente ha cliccato "Cancel" o ha inserito un nome vuoto
                        return;
                    }
                    name += ".txt";
                    NF.setName(name);
                }

                if (!NF.getName().equals(".txt")) { // Verifica se il nome del file è stato inserito
                    try {
                        FileWriter fw = new FileWriter(
                                UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + File.separator
                                        + UindosDirectoryName.DIRECTORY_FILE_DI_TESTO + NF.getName());
                        fw.write(NF.getTextArea().getText()); // Salva
                        isFileSaved = true;
                        fw.close();
                        if (isFileSaved) {
                            JOptionPane.showMessageDialog(NF.getFrame(), "Il file è stato salvato correttamente");
                        }
                        NF.getFrame().setTitle("Notepad - " + NF.getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(NF.getFrame(), "Inserisci un nome!");
                }
                break;
            case "Open":

            NF.getTextArea().setText("");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + File.separator + UindosDirectoryName.DIRECTORY_FILE_DI_TESTO)); // Imposta la directory di lavoro come cartella iniziale
                int result = fileChooser.showOpenDialog(NF.getFrame());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    if (selectedFile != null) { 
                        NF.getFrame().setTitle("Notepad - " + selectedFile.getName()); 
                        NF.setName(selectedFile.getName());
                        try {	
                            String line;
                            FileReader f=new FileReader(selectedFile);
                            BufferedReader fIN= new BufferedReader(f);
    
                            line=fIN.readLine();
                            while(line!=null){
                                NF.getTextArea().append(line + "\n");
                                line=fIN.readLine();
                            }
                            fIN.close();
                            JOptionPane.showMessageDialog(NF.getFrame(), "Il file aperto correttamente");
                            isFileSaved = true;
                        } catch(IOException ioException){
                            JOptionPane.showMessageDialog(NF.getFrame(), "Errore nell'apertura del file");
                        }
                    } else {
                        JOptionPane.showMessageDialog(NF.getFrame(), "Errore durante l'apertura del file.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Exit":
                NF.getFrame().dispose();
                break;
            default:
                break;
            }
    }
}