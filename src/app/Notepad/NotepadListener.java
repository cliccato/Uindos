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
import utils.UindosPath;

public class NotepadListener implements ActionListener{
    private NotepadFrame NF;
    private boolean isFileSaved;
    private String name;

    public NotepadListener(NotepadFrame NF) {
        this.NF = NF;
        name = "";
        isFileSaved = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":

                NF.getTextArea().setText("");
                NF.getFrame().setTitle("Notepad - (Nuovo)");
                name = "";
                isFileSaved = false;
                break;
            case "Save":

                if(!isFileSaved) {
                    name = JOptionPane.showInputDialog("Inserisci nome file:");
                    name+= ".txt";
                }
        
                if (name != null && !name.isEmpty()) { // Verifica se il nome del file è stato inserito
                    try {
                        FileWriter fw;
                        fw = new FileWriter( UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/file di testo/" +  name);
                        fw.write(NF.getTextArea().getText()); // Salva 
                        isFileSaved = true;
                        fw.close();
                        JOptionPane.showMessageDialog(NF.getFrame(), "Il file è stato salvato correttamente");
                        NF.getFrame().setTitle("Notepad - " + name);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
               
                break;
            case "Open":

            NF.getTextArea().setText("");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername() + "/file di testo")); // Imposta la directory di lavoro come cartella iniziale
                int result = fileChooser.showOpenDialog(NF.getFrame());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    if (selectedFile != null) { 
                        NF.getFrame().setTitle("Notepad - " + selectedFile.getName()); 
                        name = selectedFile.getName();
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