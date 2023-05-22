package System.app.AppBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import System.Desktop.DesktopFrame;
import app.Calendar.CalendarApp;
import app.Notepad.NotepadFrame;
import app.Paint.PaintApp;
import utils.UindosPath;

public class AppBarListener implements ActionListener {
    private String name;
    private String username;

    public AppBarListener(String name, String username) {
        this.name = name;
        this.username = username;
    }

    private static String getExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
    
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return null; // Non c'è estensione o il punto è l'ultimo carattere
        }
    
        return fileName.substring(dotIndex);
    }
    
    private static String removeExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
    
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return fileName; // Non c'è estensione o il punto è l'ultimo carattere
        }
    
        return fileName.substring(0, dotIndex);
    }
    
    private void explorer(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(UindosPath.USER_FOLDER_PATH + DesktopFrame.getUsername())); // Imposta la directory di lavoro come cartella iniziale
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    String extension = getExtension(fileName);
                    System.out.println(extension);
                    if (!extension.equals(".txt") && !extension.equals(".png")) { 
                        JOptionPane.showMessageDialog(null, "Impossibile aprire questo file.", "Errore", JOptionPane.ERROR_MESSAGE);   
                    } else {

                        if (extension.equals(".txt")) {
                            NotepadFrame NF = new NotepadFrame(DesktopFrame.getUsername());
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
                                } catch(IOException ioException){
                                    JOptionPane.showMessageDialog(NF.getFrame(), "Errore nell'apertura del file");
                                }
                        } else {
                            PaintApp paintApp = new PaintApp(DesktopFrame.getUsername());
                            paintApp.getFrame().setTitle("Peint - " + selectedFile.getName());
                            try {
                                BufferedImage newImage = ImageIO.read(selectedFile);
                                if (newImage != null) {
                                    paintApp.setName(removeExtension(fileName));
                                    paintApp.setImage(newImage);
                                    JPanel newCanvas = paintApp.getCanvas();
                                    newCanvas.setPreferredSize(new Dimension(paintApp.getImage().getWidth(), paintApp.getImage().getHeight()));
                                    newCanvas.revalidate();
                                    newCanvas.repaint();
                                } else {
                                    JOptionPane.showMessageDialog(paintApp.getFrame(), "Errore durante l'apertura dell'immagine.", "Errore", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(paintApp.getFrame(), "Errore durante l'apertura dell'immagine.", "Errore", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selezionare un file!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (name) {
            case "explorer":
                explorer();
                break;
            case "notepad":
                new NotepadFrame(username);
                break;
            case "paint":
                new PaintApp(username);
                break;
            case "calendar":
                new CalendarApp(username);
        }
    }
}