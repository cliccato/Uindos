/*
AUTORE: NICOLO' MEZZANZANICA
DATA: 06/02/2023
LUOGO: CASA
OGGETTO: CLASSE LISTENER SCELTA FILE
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ListenerSceltaDifficolta implements ActionListener {

    //impiccato
    Impiccato impiccato;

    //metodo costruttore della classe ListenerSceltaFile
    public ListenerSceltaDifficolta(Impiccato impiccato){
        this.impiccato = impiccato;
    }

    //metodo per ottenere l'elenco delle parole da file
    private Vector<String> ottieniParoleDaFile(){
        Vector<String> paroleDaFile = new Vector<>();
        String line;
        try {
            FileReader fileReader = new FileReader(impiccato.file);
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

    //metodo che estrae casualmente una parola e la nasconde
    private void estraiParola(){
        Vector<String> paroleDaFile = ottieniParoleDaFile();
        impiccato.setParolaEstratta(paroleDaFile.get((int)(Math.random()*paroleDaFile.size() + 1)));
        char[] parolaNascosta = impiccato.getParolaEstratta().toCharArray();
        for(int i=1; i < impiccato.getParolaEstratta().length() - 1; i++){
            parolaNascosta[i] = '_';
        }
        impiccato.lblInformazioniParolaNascosta.setText("Parola da " + impiccato.getParolaEstratta().length() + " lettere ->");
        impiccato.lblParolaNascosta.setText(String.copyValueOf(parolaNascosta));
    }

    //metodo actionPerformed
    public void actionPerformed(ActionEvent e){
        impiccato.setTentativi(7);
        impiccato.setParoleGiaDette(new Vector<String>());
        impiccato.setLettereGiaDette(new Vector<Character>());
        impiccato.giocoFinito = false;
        impiccato.lblInformazioniParolaNascosta.setVisible(true);
        impiccato.lblImmagine.setIcon(new ImageIcon("images/inizio.png"));
        impiccato.lblIndovinato.setText("");
        impiccato.cslParolaUtente.setText("");
        impiccato.lblParolaUtente.setVisible(true);
        impiccato.cslParolaUtente.setEditable(true);
        impiccato.cslParolaUtente.setVisible(true);
        impiccato.lblImmagine.setVisible(true);
        impiccato.btnConferma.setVisible(true);
        impiccato.setFile(new File("file/" + e.getActionCommand() + ".txt"));
        estraiParola();
    }
}