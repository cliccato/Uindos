package app.Hanged;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ListenerChooseDifficult implements ActionListener {

    private Hanged hanged;

    public ListenerChooseDifficult(Hanged hanged){
        this.hanged = hanged;
    }

    private Vector<String> ottieniParoleDaFile(){
        Vector<String> paroleDaFile = new Vector<>();
        String line;
        try {
            FileReader fileReader = new FileReader(hanged.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            while (line != null){
                paroleDaFile.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println("Errore durante la lettura da file!");
        }

        return paroleDaFile;
    }

    private void estraiParola(){
        Vector<String> paroleDaFile = ottieniParoleDaFile();
        hanged.setParolaEstratta(paroleDaFile.get((int)(Math.random()*paroleDaFile.size() + 1)));
        char[] parolaNascosta = hanged.getParolaEstratta().toCharArray();
        for(int i=1; i < hanged.getParolaEstratta().length() - 1; i++){
            parolaNascosta[i] = '_';
        }
        hanged.getLblInformazioniParolaNascosta().setText("Parola da " + hanged.getParolaEstratta().length() + " lettere ->");
        hanged.getLblParolaNascosta().setText(String.copyValueOf(parolaNascosta));
    }

    public void actionPerformed(ActionEvent e){
        hanged.setTentativi(Hanged.NUM_TENTATIVI);
        hanged.setParoleGiaDette(new Vector<String>());
        hanged.setLettereGiaDette(new Vector<Character>());
        hanged.getLblInformazioniParolaNascosta().setVisible(true);
        hanged.getLblImmagine().setIcon(new ImageIcon("src/app/Hanged/images/inizio.png".replace("/", File.separator)));
        hanged.getLblIndovinato().setText("");
        hanged.getCslParolaUtente().setText("");
        hanged.iniziaGioco();
        hanged.getLblParolaUtente().setVisible(true);
        hanged.getCslParolaUtente().setEditable(true);
        hanged.getCslParolaUtente().setVisible(true);
        hanged.getLblImmagine().setVisible(true);
        hanged.getBtnConferma().setVisible(true);
        hanged.setFile(new File("src/app/Hanged/file/".replace("/", File.separator) + e.getActionCommand() + ".txt"));
        estraiParola();
    }
}